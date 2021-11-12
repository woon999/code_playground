package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {

    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10; // 기본 크기

    private int size;
    private Object[] array;

    // 초기 공간 할당 X
    public Heap()   {
        this(null);
    }

    // 초기 공간 할당 O
    public Heap(int capacity)   {
        this(capacity,null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
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
            newArray[i] = array[i];
        }

        /**
         * 현재 배열은 GC처리를 위해 null로 변환한 뒤
         * 새 배열 연결
         */
        this.array = null;
        this.array = newArray;
    }

    public void add(E value){
        if(size == array.length-1){
            resize(array.length*2);
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
            Object pv = array[pa];

            // 타겟 >= 부모 - 종료
            if(comparator.compare(target, (E)pv)>=0) break;

            // 타겟 < 부모 - 부모와 자식 교체
            array[idx] = pv;
            idx = pa;
        }
        // 최종위치에 타겟 노드 값 저장
        array[idx] = target;
    }

    private void shiftUpComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>)target;

        while(idx>1){
            int pa = getParent(idx);
            Object pv = array[pa];

            if(comp.compareTo((E)pv)>=0) break;

            array[idx] = pv;
            idx = pa;
        }
        array[idx] = comp;
    }

    public E remove(){
        if(array[1] ==null) throw new NoSuchElementException();

        E result = (E) array[1];
        E target = (E) array[size];
        array[size] = null;

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

        array[idx] = null;
        size--;

        int pa = idx;
        int child;

        while((child = getLeftChild(pa)) <= size) {

            int right = getRightChild(pa);

            Object cv = array[child];

            if(right <= size && ((Comparable<? super E>)cv).compareTo((E)array[right]) > 0) {
                child = right;
                cv = array[child];
            }

            if(comp.compareTo((E) cv) <= 0){
                break;
            }
            array[pa] = cv;
            pa = child;

        }
        array[pa] = comp;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    private void shiftDownComparator(int idx, E target, Comparator<? super E> comp) {
        array[idx] = null;
        size--;
        int pa = idx;
        int child;

        while((child=getLeftChild(pa)) <= size){
            int right = getRightChild(pa);
            Object cv = array[child];

            if(right <= size && comp.compare((E)cv, (E)array[right])>0){
                child = right;
                cv = array[child];
            }

            if(comp.compare(target, (E)cv)>=0) break;

            array[pa] = cv;
            pa = child;
        }

        array[pa] = target;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }


    public int size() {
        return this.size;
    }

    public E peek() {
        if(array[1] == null) {
            throw new NoSuchElementException();
        }
        return (E)array[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size + 1);
    }

    public void printHeap(){
        System.out.println("--- 힙 높이별로 출력 --- " + size);
        int h = (int)Math.ceil(Math.log(size+1)/Math.log(2));
        out:for(int i=0; i<h; i++){
            int len = (int)(Math.pow(2,i));
            for(int j=0; j<len; j++){
                int idx =len+j;
                if(idx>=size+1) break out;
                System.out.print(array[idx]+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
