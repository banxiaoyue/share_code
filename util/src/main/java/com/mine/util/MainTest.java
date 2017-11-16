package com.mine.util;


/**
 * Created by haining on 16/9/18.
 */
public class MainTest {
    public static void main(String[] args) {
        HttpConnectUtil httpConnectUtil = HttpConnectUtil.getInstance();
        System.out.print(httpConnectUtil.doHTTPsGet("http://www.baidu.com","UTF-8"));

    }
}
