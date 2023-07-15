package com.example.study.livestudy.week4;


public class ListNode {
    int data;  // Data Field
    ListNode next; // Link Field

    public ListNode(){

    }
    /**
     * 정수를 저장하는 ListNode 생성
     */
    public ListNode(int data){
        this.data = data;
        this.next = null;
    }

    /**
     * 데이터 추가
     */
    public ListNode add(ListNode head, ListNode nodeToAdd, int position){

        ListNode node = head;

        // 맨 앞에 삽입할 경우
        if(position ==0) {
            // 삽입 노드가 첫번째 노드인 경우
            if(head == null) {
                return nodeToAdd;
            }
            ListNode insertNode = nodeToAdd;
            insertNode.next = head;
            head = insertNode;
            return head;
        }
        // position 바로 앞까지 순차 탐색
        for(int i=0; i<position-1; i++){
            node = node.next;
        }
        nodeToAdd.next = node.next;
        node.next = nodeToAdd;
        return head;

    }

    /**
     * 데이터 삭제
     */
    public ListNode remove(ListNode head, int positionToRemove){

        ListNode node = head;

        // 삭제 노드가 헤드일 경우
        if(positionToRemove==0){
            // 1번 인덱스 노드를 헤드로 지정
            head = head.next;
        }else{
            // 순차 탐색
            for(int i=0; i<positionToRemove-1; i++){
                node = node.next;
            }

            ListNode removeNode = node.next;
            node.next = removeNode.next;
        }
        return head;

    }


    /**
     * 데이터 포함 여부 확인
     */
    public boolean contains(ListNode head, ListNode nodeToCheck){

        while(head != null){
            // 찾는 데이터가 head면 true 리턴
            if(head.data == nodeToCheck.data) return true;

            // checkNode 찾을 때까지 순차 탐색
            head = head.next;
        }
        // 탐색 후 없으면 false 반환
        return false;
    }


    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }


    /**
     * ListNode 테스트
     */
    public static void main(String[] args) {


        ListNode node = new ListNode();
        ListNode head = node;

        /**
         * head :
         * 1~4 노드 순서대로 삽입 (FIFO)
         */
        System.out.println("-----순서대로 삽입------");
        for(int i=1; i<5; i++){
            head = node.add(head, new ListNode(i),i-1);
            System.out.println(head.toString());
        }
        System.out.println();

        /**
         *  노드 중간 삽입
         *  head-1-2-3-4-Null
         *  >> head-1-2-10-3-4-Null
         */
        System.out.println("-----중간 삽입------");
        head = node.add(head, new ListNode(10),2);
        System.out.println(head.toString());
        System.out.println();


        /**
         * 노드 삭제
         */
        System.out.println("-----노드 삭제------");

        // head 삭제
        // head-2-10-3-4-Null
        head = node.remove(head,0);
        System.out.println(head.toString());

        // index :2 삭제
        // head-2-10-4-Null
        head = node.remove(head,2);
        System.out.println(head.toString());
        System.out.println();


        /**
         * 노드 포함여부 확인
         * head-2-10-4-Null
         */
        System.out.println("1 포함여부 :"+ node.contains(head, new ListNode(1))); // false
        System.out.println("10 포함여부 :" + node.contains(head, new ListNode(10))); // true

    }
}

