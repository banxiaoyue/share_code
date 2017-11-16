package com.mine.util;

/**
 * Created by haining on 2017/9/28.
 */
public class TestStruggle {
    {
        System.out.println("first block");
        add("in");
    }


    static {
        System.out.println("static first block");
    }


    TestStruggle(){
        System.out.println("secoend block");
    }

    private void add(String str){
        System.out.println(str + "secoend 2 block");
    }

}
