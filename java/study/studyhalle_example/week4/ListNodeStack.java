package com.example.study.livestudy.week4;

/**
 * ListNode로 Stack 만들기
 */
public class ListNodeStack {

    ListNode head;
    private int top=0;

    public ListNodeStack(){
    }

    public ListNodeStack(int data){
//        System.out.println("현재 pos : "+ top + ", data : "+ data);
        if(head == null){
            head = new ListNode(data);
            top++;
        }else{
            head.add(head, new ListNode(data), top++);
        }

        System.out.println(head);
    }

    /**
     * 데이터 삽입
     */
    public void push(int data){
        // public ListNode add(ListNode head, ListNode nodeToAdd, int position){
//        System.out.println("현재 pos : "+ top + ", data : "+ data);
        if(head == null){
            head = new ListNode(data);
            top++;
        }else{
            head.add(head, new ListNode(data), top++);
        }
        System.out.println(head);

    }

    /**
     * 데이터 삭제
     */
    public int pop(){
        if(top == 0) {
            System.out.println("데이터가 존재하지 않습니다.");
            return -1;
        }else{
            ListNode node =head;
            for(int i=0; i<top-1; i++){
                node = node.next;
            }
            int data = node.data;
//          public ListNode remove(ListNode head, int positionToRemove){
            node.remove(head, --top);
            return data;

        }

    }


    /**
     * 테스트 코드
     */
    public static void main(String[] args) {
        System.out.println("============= 데이터 삽입 ===============");
        ListNodeStack listNodeStack1 = new ListNodeStack(5);
        listNodeStack1.push(10);
        listNodeStack1.push(20);
        listNodeStack1.push(30);
        System.out.println();

        System.out.println("============= 데이터 삭제 및 출력 =============");
        System.out.println("데이터 삭제 : " +listNodeStack1.pop());
        System.out.println("데이터 삭제 : " +listNodeStack1.pop());
        System.out.println("데이터 삭제 : " +listNodeStack1.pop());
        System.out.println("데이터 삭제 : " +listNodeStack1.pop());
        System.out.println("데이터 삭제 : " +listNodeStack1.pop());


    }
}
