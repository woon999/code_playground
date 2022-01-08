package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

// ref: https://st-lab.tistory.com/205#%EC%A0%84%EC%B2%B4
public class Heap<E> {

    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10; // 기본 크기

    private int size;
    private Object[] arr;

    // 초기 공간 할당 X
    public Heap()   {
        this(null);
    }

    // 초기 공간 할당 O
    public Heap(int capacity)   {
        this(capacity,null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.arr = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    // 부모 인덱스 반환
    private int getParent(int idx){
        return idx/2;
    }

    // 왼쪽 자식 인덱스 반환
    private int getLeftChild(int idx){
        return idx*2;
    }
    // 오른쪽 자식 인덱스 반환
    private int getRightChild(int idx){
        return idx*2+1;
    }

    /**
     * @param newCapacity 새로운 용적 크기
     */
    private void resize(int newCapacity){
        Object[] newArray = new Object[newCapacity];

        for(int i=1; i<=size; i++){
            newArray[i] = arr[i];
        }

        /**
         * 현재 배열은 GC처리를 위해 null로 변환한 뒤
         * 새 배열 연결
         */
        this.arr = null;
        this.arr = newArray;
    }

    public void add(E value){
        if(size == arr.length-1){
            resize(arr.length*2);
        }

        shiftUp(size+1, value);
        size++;
    }

    private void shiftUp(int idx, E target){
        if (comparator != null) {
            shiftUpComparator(idx, target, comparator);
        }else{
            shiftUpComparable(idx, target);
        }
    }

    private void shiftUpComparator(int idx, E target, Comparator<? super E> comparator) {
        while(idx>1){
            int pa = getParent(idx);
            Object pv = arr[pa];

            // 타겟 >= 부모 - 종료
            if(comparator.compare(target, (E)pv)>=0) break;

            // 타겟 < 부모 - 부모와 자식 교체
            arr[idx] = pv;
            idx = pa;
        }
        // 최종위치에 타겟 노드 값 저장
        arr[idx] = target;
    }

    private void shiftUpComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>)target;

        while(idx>1){
            int pa = getParent(idx);
            Object pv = arr[pa];

            if(comp.compareTo((E)pv)>=0) break;

            arr[idx] = pv;
            idx = pa;
        }
        arr[idx] = comp;
    }

    public E remove(){
        if(arr[1] ==null) throw new NoSuchElementException();

        E result = (E) arr[1];
        E target = (E) arr[size];
        arr[size] = null;

        shiftDown(1, target);
        return result;
    }

    private void shiftDown(int idx, E target) {
        if (comparator != null) {
            shiftDownComparator(idx, target, comparator);
        }else{
            shiftDownComparable(idx, target);
        }
    }

    private void shiftDownComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;

        arr[idx] = null;
        size--;

        int pa = idx;
        int child;

        while((child = getLeftChild(pa)) <= size) {

            int right = getRightChild(pa);

            Object cv = arr[child];

            if(right <= size && ((Comparable<? super E>)cv).compareTo((E)arr[right]) > 0) {
                child = right;
                cv = arr[child];
            }

            if(comp.compareTo((E) cv) <= 0){
                break;
            }
            arr[pa] = cv;
            pa = child;

        }
        arr[pa] = comp;

        if(arr.length > DEFAULT_CAPACITY && size < arr.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }
    }

    private void shiftDownComparator(int idx, E target, Comparator<? super E> comp) {
        arr[idx] = null;
        size--;
        int pa = idx;
        int child;

        while((child=getLeftChild(pa)) <= size){
            int right = getRightChild(pa);
            Object cv = arr[child];

            if(right <= size && comp.compare((E)cv, (E)arr[right])>0){
                child = right;
                cv = arr[child];
            }

            if(comp.compare(target, (E)cv)<=0) break;

            arr[pa] = cv;
            pa = child;
        }

        arr[pa] = target;

        if(arr.length > DEFAULT_CAPACITY && size < arr.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }
    }


    public int size() {
        return this.size;
    }

    public E peek() {
        if(arr[1] == null) {
            throw new NoSuchElementException();
        }
        return (E)arr[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(arr, size + 1);
    }

    public void printHeap(){
        System.out.println("--- 힙 높이별로 출력 --- " + size);
        int h = (int)Math.ceil(Math.log(size+1)/Math.log(2));
        out:for(int i=0; i<h; i++){
            int len = (int)(Math.pow(2,i));
            for(int j=0; j<len; j++){
                int idx =len+j;
                if(idx>=size+1) break out;
                System.out.print(arr[idx]+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
