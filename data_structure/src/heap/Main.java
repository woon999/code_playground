package heap;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        for(int i=0; i<15; i++){
            heap.add(new Random().nextInt(100));
        }

        System.out.println("--- 내부 배열 상태 ---");
        Object[] arr = heap.toArray();
        for (Object o : arr) {
            System.out.print(o +" ");
        }

        heap.printHeap();

        System.out.println("#### 원소 38,50 추가 ");
        heap.add(38);
        heap.printHeap();
        heap.add(50);
        heap.printHeap();

        System.out.println("#### 원소 10개 삭제 ");
        for(int i=0; i<10; i++)
            heap.remove();
        heap.printHeap();


        System.out.println("#### 힙 출력");
        while(!heap.isEmpty()){
            System.out.print(heap.remove()+" ");
        }
        System.out.println();
    }

}
