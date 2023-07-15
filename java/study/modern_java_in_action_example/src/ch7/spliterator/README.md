# Spliterator 인터페이스
Spliterator는 분할할 수 있는 반복자라는 의미다. iterator처럼 소스의 요소 탐색 기능을 제공한다는 점은 같지만 병렬 작업에 특화되어 있다.

~~~
public interface Spliterator<T> {
    boolean tryAdvance(Consumer<? super T> action);
    Spliterator<T> trySplit();
    long estimateSize();
    int characteristics();
}
~~~

tryAdvance 메서드
-  Spliterator의 요소를 하나씩 순차적으로 소비하면서 탐색해야 할 요소가 남아있으면 참을 반환한다.

trySplit 메서드
- 반면 trySplit 메서드는 Spliterator의 일부 요소를 분할해서 두 번째 Spliterator를 생성하는 메서드다.

estimateSize 메서드
- estimateSize 메서드로 탐색해야 할 요소 수 정보를 제공한다.

characteristics 메서드
-  Spliterator 자체의 특성 집합을 포함하는 int를 반환한다.
- ORDERED, DISTINCT, SORTED, SIZED, NONNULL, IMMUTABLE, CONCURRENT, SUBSIZED

<br>
 
## 분할 과정
Step1. 첫 번째 Spliterator에 trySplit를 호출하면 두 번째 Spliterator가 생성된다.

Step2. 두 개의 Spliterator에 다시 trySplit를 호출하면 네 개의 Spliterator가 생성된다.

Step3. trySplit의 결과가 null이 될 때까지 반복한다.

Step4. 재귀 분할 과정이 종료된다.

<img width="659" alt="스크린샷 2022-01-10 오후 6 13 11" src="https://user-images.githubusercontent.com/54282927/148741730-957c221b-6643-4fcf-a4bd-f1603489db51.png">
