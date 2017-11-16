package com.mine.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

public class HttpConnectUtil {
    /**
     * Singleton instance of ConnectionUtil.
     */
    private static HttpConnectUtil CONNECTIONUTIL = new HttpConnectUtil();

    private HttpMethodRetryHandler retryhandler;
    /**
     * the client of Http connection.
     */
    private static HttpClient CLIENT;


    private HttpConnectUtil() {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        CLIENT = new HttpClient(connectionManager);
        // the timeout for waiting for establishing a connection
        CLIENT.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 30);
        // the timeout for waiting for data
        CLIENT.getHttpConnectionManager().getParams().setSoTimeout(1000 * 300);
        retryhandler = new HttpMethodRetryHandler() {
            public boolean retryMethod(
                    final HttpMethod method,
                    final IOException exception,
                    int executionCount) {
                if (executionCount >= 3) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    return true;
                }
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                if (!method.isRequestSent()) {
                    return true;
                }
                // otherwise do not retry
                return false;
            }
        };
    }

    /**
     * return the singleton instance
     *
     * @return ConnectionUtil instance: CONNECTIONUTIL
     */
    public final static HttpConnectUtil getInstance() {
        return CONNECTIONUTIL;
    }

    /**
     * do Https Post and get the response of the post
     *
     * @param connectionURL a url for the connection(not null)
     * @param request       the request(not null)
     * @return null or response
     * //	 * @exception PreGWTransactionException throw when some exception occurred including UnknownHostException,NoHttpResponseException,InterruptedIOException and so on.
     */
    public String doHTTPsPOST(String connectionURL, String request) {
        String response = null;
        PostMethod post = null;

        try {
            // url = fully qualified url of the server to which you are posting
            post = new PostMethod(connectionURL);
            HttpMethodParams params = new HttpMethodParams();
            params.setVersion(HttpVersion.HTTP_1_0);
            params.setParameter(HttpMethodParams.RETRY_HANDLER, retryhandler);
            RequestEntity body = new StringRequestEntity(request, "application/x-www-form-urlencoded", "UTF-8");
            post.setRequestEntity(body);
            post.setHttp11(false);
        } catch (Exception e) {
            if (null != post)
                post.releaseConnection();
            throw new RuntimeException("连接错误", e);
        }

        try {
            CLIENT.executeMethod(post);
            response = post.getResponseBodyAsString();
        } catch (UnknownHostException e) {
            throw new RuntimeException("域名无法解析", e);
        } catch (NoHttpResponseException e) {
            throw new RuntimeException("HTTP没有响应", e);
        } catch (InterruptedIOException e) {
            throw new RuntimeException("IO中断异常", e);
        } catch (Exception e) {
            throw new RuntimeException("发送或读取数据异常", e);
        } finally {
            post.releaseConnection();
        }

        return response == null ? "" : response;
    }

    public String doHTTPsPOST(String connectionURL, Map<String, String> requestMap) {
        String response;
        PostMethod post = this.getPoseMethod(connectionURL, requestMap);
        try {
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            CLIENT.executeMethod(post);
            response = post.getResponseBodyAsString();
        } catch (UnknownHostException e) {
            throw new RuntimeException("域名无法解析", e);
        } catch (NoHttpResponseException e) {
            throw new RuntimeException("HTTP没有响应", e);
        } catch (InterruptedIOException e) {
            throw new RuntimeException("IO中断异常", e);
        } catch (Exception e) {
            throw new RuntimeException("发送或读取数据异常", e);
        } finally {
            post.releaseConnection();
        }

        return response == null ? "" : response;
    }


    public String doHTTPsGet(String uri) {
        GetMethod getMethod = null;
        try {
            getMethod = new GetMethod(uri);
            HttpMethodParams params = new HttpMethodParams();
            params.setVersion(HttpVersion.HTTP_1_0);
            params.setParameter(HttpMethodParams.RETRY_HANDLER, retryhandler);
            getMethod.setParams(params);
            CLIENT.executeMethod(getMethod);
            return getMethod.getResponseBodyAsString();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
    }

    public String doHTTPsGet(String uri, String charSetName) {
        GetMethod getMethod = null;
        try {
            getMethod = new GetMethod(uri);
            HttpMethodParams params = new HttpMethodParams();
            params.setVersion(HttpVersion.HTTP_1_0);
            params.setParameter(HttpMethodParams.RETRY_HANDLER, retryhandler);
            getMethod.setParams(params);
            CLIENT.executeMethod(getMethod);
            return new String(getMethod.getResponseBody(), charSetName);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
    }

    /**
     * 组装PostMethod
     *
     * @param connectionURL
     * @param requestMap
     * @return
     */
    private PostMethod getPoseMethod(String connectionURL, Map<String, String> requestMap) {
        PostMethod post = new PostMethod(connectionURL);
        try {
            if (requestMap != null && requestMap.size() > 0) {
                Iterator<String> it = requestMap.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = requestMap.get(key);
                    post.addParameter(key, value);
                }
            }
        } catch (Exception e) {
            if (null != post)
                post.releaseConnection();
            throw new RuntimeException("连接错误", e);
        }
        return post;
    }
}
