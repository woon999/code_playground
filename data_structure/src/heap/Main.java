package heap;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import static heap.Fruit.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("#### 기본 힙 구현 테스트 ####");
        basicHeapTest();
        System.out.println();

        System.out.println("#### 최대힙, 최소힙 구현 테스트 ####");
        minHeapAndMaxHeapTest();
        System.out.println();

        System.out.println("#### 참조타입 힙 구현 테스트 ####");
        refTypeHeapTest();
        System.out.println();
    }

    /**
     * 참조 타입 힙 구현 테스트
     */
    static void refTypeHeapTest(){
        Heap<Fruit> h1 = new Heap<>(nameAsc);
        Heap<Fruit> h2 = new Heap<>(priceDesc);
        Stream<Fruit> stream = getFruitStream();
        stream.forEach(h1::add);
        System.out.println("--- 이름 오름차순 ---");
        while(!h1.isEmpty()){
            System.out.println(h1.remove());
        }

        stream = getFruitStream();
        stream.forEach(h2::add);
        System.out.println("--- 가격 내림차순 ---");
        while(!h2.isEmpty()){
            System.out.println(h2.remove());
        }
    }

    /**
     * 최대힙, 최소힙 구현 테스트
     */
    static void minHeapAndMaxHeapTest(){
        Heap<Integer> maxHeap = new Heap<>((o1,o2) -> (o2-o1));
        Heap<Integer> minHeap = new Heap<>();
        for(int i=1; i<=10; i++){
            maxHeap.add(i);
            minHeap.add(i);
        }
        maxHeap.printHeap();
        System.out.print("출력 : ");
        while(!maxHeap.isEmpty()){
            System.out.print(maxHeap.remove()+" ");
        }
        System.out.println();
        minHeap.printHeap();
        System.out.print("출력 : ");
        while(!minHeap.isEmpty()){
            System.out.print(minHeap.remove()+" ");
        }
        System.out.println();
    }

    /**
     * 기본 힙 구현 테스트
     */
    static void basicHeapTest(){
        Heap<Integer> heap = new Heap<>();
        for(int i=0; i<15; i++){
            heap.add(new Random().nextInt(100));
        }

        System.out.println("--- 내부 배열 상태 ---");
        Object[] arr = heap.toArray();
        for (Object o : arr) {
            System.out.print(o +" ");
        }
        System.out.println();

        heap.printHeap();

        System.out.println("#### 원소 38,50 추가 ");
        heap.add(38);
        heap.add(50);
        heap.printHeap();
        System.out.println();

        System.out.println("#### 원소 10개 삭제 ");
        for(int i=0; i<10; i++)
            heap.remove();
        heap.printHeap();
        System.out.println();

        System.out.println("#### 힙 출력");
        while(!heap.isEmpty()){
            System.out.print(heap.remove()+" ");
        }
        System.out.println();
    }
}

class Fruit{
    String name;
    int price;

    public Fruit(String name, int price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static Stream<Fruit> getFruitStream() {
        return Stream.of(
                new Fruit("pear",400),
                new Fruit("banana",200),
                new Fruit("kiwi",500),
                new Fruit("apple",300),
                new Fruit("cherry",100)
        );
    }
    static Comparator<Fruit> nameAsc = new Comparator<Fruit>() {
        @Override
        public int compare(Fruit o1, Fruit o2) {
            if(o1.name==o2.name){
                return o1.price-o2.price;
            }else return o1.name.compareTo(o2.name);
        }
    };
    static Comparator<Fruit> priceDesc = new Comparator<Fruit>() {
        @Override
        public int compare(Fruit o1, Fruit o2) {
            if(o1.price==o2.price){
                return o1.name.compareTo(o2.name);
            }else return o2.price-o1.price;
        }
    };
}