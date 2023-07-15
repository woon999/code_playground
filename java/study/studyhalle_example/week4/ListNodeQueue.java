package com.example.study.livestudy.week4;

public class ListNodeQueue {

    private ListNode head;
    private int front=0;
    private int rear=0;

    public ListNodeQueue(){}

    /**
     * 데이터 삽입
     */
    public void offer(int data){
        if(head==null){
            head = new ListNode(data);
        }
        System.out.println( "데이터 추가 : "+data + " -> front : " + front +" , rear : "+ rear);
        head.add(head, new ListNode(data), rear++);
    }


    /**
     * 맨 앞의 데이터 출력
     */
    public int peek(){
        if(front == rear) {
            System.out.println("데이터가 존재하지 않습니다");
            return -1;
        }
        return head.data;
    }

    /**
     * 데이터 출력후 삭제
     */
    public int poll(){
        if(front == rear) {
            System.out.println("데이터가 존재하지 않습니다");
            return -1;
        }
        rear--;
        ListNode delNode = head;
        int data = delNode.data;
        head = delNode.remove(head,0);
        return data;
    }

    /**
     * 맨앞의 데이터 삭제
     */
    public void remove(){
        if(front == rear) {
            System.out.println("데이터가 존재하지 않습니다");
            return;
        }
        rear--;
        ListNode delNode = head;
        head = delNode.remove(head,0);
    }

    /**
     * 모든 데이터 삭제
     */
    public void clear(){

        while(front <= rear--){
            head= head.remove(head,rear);

            if(front == rear ) break;
        }
        System.out.println("모든 데이터 삭제 완료");
    }


    /**
     * 테스트 코드
     */
    public static void main(String[] args) {
        ListNodeQueue listNodeQueue = new ListNodeQueue();

        listNodeQueue.offer(1);
        listNodeQueue.offer(2);
        listNodeQueue.offer(3);
        System.out.println(listNodeQueue.head);System.out.println();


        System.out.println("====== 데이터 삭제 & 출력  =======");
        System.out.println("데이터 출력 후 제거 poll() : "+ listNodeQueue.poll());
        System.out.println("데이터 출력 peek(): "+ listNodeQueue.peek());
        System.out.println(listNodeQueue.head);
        System.out.println();

        System.out.println("====== 데이터 추가 =======");
        listNodeQueue.offer(4);
        listNodeQueue.offer(5);
        System.out.println(listNodeQueue.head);
        System.out.println();

        System.out.println("====== 데이터 삭제 & 출력  =======");
        System.out.println("데이터 출력 후 제거 poll() : "+ listNodeQueue.poll());
        System.out.println("데이터 출력 후 제거 poll() : "+ listNodeQueue.poll());
        System.out.println("데이터 제거 remove() : " + listNodeQueue.peek()); listNodeQueue.remove();;
        System.out.println(listNodeQueue.head);
        System.out.println();

        System.out.println("====== 오류 테스트  =======");
        listNodeQueue.clear();
        System.out.println("데이터 출력 후 제거 poll() : "+ listNodeQueue.poll());


    }

}
