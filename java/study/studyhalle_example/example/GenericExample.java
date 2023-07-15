package com.example.study.livestudy.example;

class Foo{
    int fo;
    public void foo(){
        System.out.println("wasssup");
    }
}

class Boo extends Foo{
    void boo(){
        System.out.println("boooo");
    }
}


public class GenericExample {

    public static  <E> boolean containsElement(E [] elements, E element){
        for (E e : elements){
            if(e.equals(element)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {


    }

}
