package com.example.study.livestudy.week4;

/**
 *  배열로 Queue 구현하기
 *  FIFO
 */
public class ArrayQueue {

    int[] elements;
    private int front = 0;
    private int rear = 0;
    private int size = 2;
    private int exSize =0;

    public ArrayQueue(){
        elements = new int[size];
    }
    /**
     * 데이터 삽입
     */
    public void offer(int data){
        if(rear == elements.length){
            System.out.println("[사이즈 확장] Size : " + size +" -> " + (rear+5));
            exSize = rear +5;
            int[] extendElements = new int[exSize];
            // migrate
            for(int i=0; i<rear; i++){
                extendElements[i] = elements[i];
            }
            elements = extendElements;
        }

        elements[rear++] = data;
        System.out.println( "데이터 추가 : "+data + " -> front : " + front +" , rear : "+ rear);

    }


    /**
     * 맨 앞의 데이터 출력
     */
    public int peek(){
        if(front == rear) {
            System.out.println("데이터가 존재하지 않습니다");
            return -1;
        }
        return elements[front];
    }

    /**
     * 데이터 출력후 삭제
     */
    public int poll(){
        if(front == rear) {
            System.out.println("데이터가 존재하지 않습니다");
            return -1;
        }
        int data = elements[front];
        elements[front++] = 0;
        return data;

    }

    /**
     * 맨앞의 데이터 삭제
     */
    public void remove(){
        if(front == rear) {
            System.out.println("데이터가 존재하지 않습니다");
        }
        System.out.println("데이터 삭제 : " + elements[front]);
        elements[front] = 0;
        front++;
    }

    /**
     * 모든 데이터 삭제
     */
    public void clear(){
        elements = new int[size];
        front=0;
        rear=0;
        System.out.println("모든 데이터 삭제 완료");
    }

    /**
     * 테스트 코드
     */
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue();

        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);

        System.out.println();
        arrayQueue.remove();
        System.out.println("맨 앞의 데이터 출력 peek(): "+ arrayQueue.peek());
        System.out.println();

//        arrayQueue.clear();
        System.out.println("[FIFO] 데이터 출력 후 제거 poll() : "+ arrayQueue.poll());
        System.out.println();

        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);
        System.out.println("[FIFO] 데이터 출력 후 제거 poll() : "+ arrayQueue.poll());
        System.out.println("[FIFO] 데이터 출력 후 제거 poll() : "+ arrayQueue.poll());
        System.out.println("[FIFO] 데이터 출력 후 제거 poll() : "+ arrayQueue.poll());
        System.out.println("[FIFO] 데이터 출력 후 제거 poll() : "+ arrayQueue.poll());
    }
}
