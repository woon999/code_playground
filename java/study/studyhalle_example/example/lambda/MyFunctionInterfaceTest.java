package com.example.study.livestudy.example.lambda;

public class MyFunctionInterfaceTest {
    static String outS = "---";
    public static void main(String[] args) {

        final String s = "Hello : ";
        MyFunctionInterface mfi = (text) -> {
            System.out.println(s+ text + outS);
        };

        outS = "where r u from? ";
        mfi.execute("wasssup!!");


    }
}
