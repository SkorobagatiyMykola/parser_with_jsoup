package com.skorobagatiy.draft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestList {

    private final static String[] arrayFactorialStr = new String[30]; //

    static {

        arrayFactorialStr[1] = "one";
        arrayFactorialStr[3] = "three";
        arrayFactorialStr[5] = "five";
    }

    public static void main(String[] args) {


        for (int i = 0; i < 22; i++) {
            System.out.println("arrayFactorialStr[" + i + "]=\"1\";" );
        }

        //Arrays.stream(arrayFactorialStr).forEach(System.out::println);
    }
}
