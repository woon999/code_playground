package com.example.study.livestudy.example;

import java.util.*;

public class ListExample {

    public static void main(String[] args) {

        List<String> l1 = new ArrayList<>();
        List<String> l2  = Arrays.asList("one", "two");
        List<String> l3  = List.of("three", "four");

        // add data to List
        l1.addAll(l2);
        l1.addAll(1, l3);
        l1.add("five");

        System.out.println(l1);

        LinkedList<String> list = new LinkedList<>();
        list.addAll(l2);
        list.addAll(1, l3);
        list.add("five");

        System.out.println(l1);

        l1.set(0, "z");
        System.out.println(l1);

        Collections.sort(l1);
        System.out.println(l1);
        l1.add("one");

        l1.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println(l1);

        List<String> s1 = Arrays.asList("zero", "two", "three", "u");
        List<String> s2 = Arrays.asList("나리", "가니", "다지");

        Collections.sort(s1);
        Collections.sort(s2);


        System.out.println(s1);
        System.out.println(s2);

    }
}
