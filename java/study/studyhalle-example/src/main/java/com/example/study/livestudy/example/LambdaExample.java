package com.example.study.livestudy.example;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaExample {

    public static void main(String[] args) {
//        DoubleUnaryOperator oper;
//
//        // lambda
//        oper = (n) -> Math.abs(n);
//        System.out.println(oper.applyAsDouble(-5));
//
//        //method reference
//        oper = Math::abs;
//        System.out.println(oper.applyAsDouble(-5));


        Function<String, Account> function1 = Account::new;
        Account account = function1.apply("아이디");

        BiFunction<String, String, Account> function2 = Account::new;
        Account account2 = function2.apply("아이디", "패스워드");

        System.out.println(account);
        System.out.println(account2);
    }
}

class Account {
    private String id;
    private String password;

    public Account(String id) {
        this.id = id;
    }

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
