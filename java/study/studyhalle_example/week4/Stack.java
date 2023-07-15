package com.example.study.livestudy.week4;

public class Stack {

    private int[] elements;
    private int top = 0;
    private int size = 2;

    /**
     * Stack 생성
     * default size =16
     */
    public Stack(){
        elements = new int[size];
    }

    public Stack(int data){
        elements = new int[size];
//        System.out.println("top:"+top);
        System.out.println("데이터 추가 : "+data);
        elements[top++] = data;
    }

    /**
     * 데이터 삽입
     */
    public void push(int data){
        // 용량이 초과할 경우 크기 확장 후 기존 데이터 migration
        if(top == elements.length){
            System.out.println("[사이즈 확장] Size : " + size +" -> " + (top+5));
            size = top +5;
            int[] extendElements = new int[size];
            // migrate
            for(int i=0; i<top; i++){
                extendElements[i] = elements[i];
            }
            elements = extendElements;
        }
//        System.out.println("top:"+top);
        System.out.println("데이터 추가 : "+data);
        elements[top++] = data;

    }

    /**
     * 데이터 삭제
     */
    public int pop(){
        if(top == 0) {
            System.out.println("데이터가 존재하지 않습니다.");
            return -1;
        }
        else {
            int num = elements[--top];
            elements[top] = 0;
            return num;
        }
    }

    /**
     * 스택 데이터 출력
     */
    public void print(){
        for(int i=0; i<top; i++){
            System.out.print(elements[i]+" ");
        }
        System.out.print( "<- top");
        System.out.println();
    }


    /**
     * 테스트 코드
     */
    public static void main(String[] args) {
        Stack myStack = new Stack();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.print();

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

        System.out.println("================================");
        Stack myStack2 = new Stack(20); // 1
        myStack2.push(10); // 2
        myStack2.push(40); // 3
        myStack2.print();

        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());

    }
}
