package com.mine.lintcode;
//import org.junit.Test;
import net.sf.json.JSONObject;
/**
 * Created by haining on 2017/1/20.
 */
public class Base {
    /**
     * 写出一个函数 anagram(s, t) 判断两个字符串是否可以通过改变字母的顺序变成一样的字符串。
     *
     * 样例:
     * 给出 s = "abcd"，t="dcab"，返回 true.
     * 给出 s = "ab", t = "ab", 返回 true.
     * 给出 s = "ab", t = "ac", 返回 false.
     *
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagramTest(String s, String b) {
        // write your code here
        byte[] a = s.getBytes();
        for (byte i : a) {
            System.out.print(i+"\n");
        }
        return true;
    }
//    @Test
    public void Tests(){
        anagramTest("aab98数据","ddd");
    }

    public static void main(String[] args){
//        String UPCOMINGPROJECT_FIRST = "尊敬的投资人，预期年化收益率为%s的投资项目，即将开始！\n";
//        System.out.print(String.format(UPCOMINGPROJECT_FIRST,"%"));
//        System.out.print("".matches("^[0-9]*[1-9][0-9]*$"));
//        Integer a = 1;
//        boolean bindedWeChat = a == null || a < 1;
//    System.out.print(bindedWeChat);
        String params = "{'resCode':'0000','resMsg':'成功'}";
        JSONObject jsonParam = JSONObject.fromObject(params);
        String ss = jsonParam.optString("resCode1");
//        String ssnull = null;
//        System.out.print("*****\n");
//        System.out.print(ss);
//        System.out.print("\n****\n");
//        System.out.print(ssnull);
//        System.out.print(jsonParam.optString("resCode1").length() + "\n");
//        System.out.print(jsonParam.containsKey("resCode") + "\n");
//        System.out.print(jsonParam.optString("resCode1").length() + "\n");
        System.out.print(jsonParam.optBoolean("opened") + "\n");
    }

}
