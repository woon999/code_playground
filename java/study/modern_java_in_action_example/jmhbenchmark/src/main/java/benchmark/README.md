# ParallelStreamBenchMark - 스트림 성능 측정
병렬화를 이용하면 순차나 반복 형식에 비해 성능이 더 좋아질 것이라 추측했다. 하지만 소프트웨어 공학에서 추측은 위험하다. 
특히 성능을 최적화할 때는 측정을 무조건 해야 한다.

따라서 자바 마이크로벤치마크 하니스(Java Microbenchmark Harness, JMH)라는 라이브러리를 이용해 작은 벤치마크를 구현해봤다.
사실 JVM으로 실행되는 프로그램을 벤치마크하는 작업은 쉽지 않다. Hotspot이 바이트코드를 최적화하는데 필요한 준비(warm-up) 시간, 가비지 컬렉터로 인한 오버헤드 등과 같은 여러 요소를 고려해야 하기 떄문이다.



## JMH 성능 측정 결과
숫자 N을 인수로 받아서 1부터 n까지의 모든 숫자의 합계를 반환하는 메서드를 구현하면 성능은 다음과 같다.

|Benchmark|Mode|Cnt|Score|Error|Units|
| :-----: |:---:|:---:|:---:|:---:|:---:|
|sequentialSum|avgt|20| 1413.063|± 320.313 | ms/op|
|iterativeSum|avgt|20| 52.051|± 3.398 | ms/op|
|parallelSum|avgt|20|4299.393  |   ± 470.019| ms/op|
|rangedSum|avgt|20|63.693 | ±   7.909 | ms/op|
|parallelRangedSum|avgt|20| 14.067| ± 1.530|ms/op |


<br>

###sequentialSum
숫자로 이루어진 무한 스트림을 생성 후 limit으로 제한한다 그리고 두 숫자를 더하는 BinaryOperator로 리듀싱 작업을 수행할 수 있다.
~~~
public long sequentialSum(){
    return Stream.iterate(1L, i -> i+1)
        .limit(N)
        .reduce(0L, Long::sum);
}
~~~

<br>

###iterativeSum
기존 반복문으로도 구현할 수 있다. 
- 전통적인 for 루프는 저수준으로 동작할 뿐 아닌라 기본값을 박싱, 언박싱하지 않아도 되므로 sequentialSum보다 더 빠르다 
~~~
public long iterativeSum(){
    long result =0;
    for(long i =1L; i<=N; i++){
        result += i;
    }
    return result;
}
~~~

<br>

###parallelSum
순차 스트림에 parallel 메서드를 호출하면 기존의 함수형 리듀싱 연산이 병렬로 처리된다.

이는 순차 버전(sequentialSum)에 비해 더 느린 속도로 실행된다. 다음 두 가지 문제가 주요 원인이다.
- 반복된 결과로 박싱된 객체가 만들어지므로 숫자를 덯려면 언박싱을 해야 한다.
- 반복 작업은 병렬로 수행할 수 있는 독립 단위로 나누기가 어렵다.
- 측정 결과 sequentialSum에 비해 거의 3배이상 느리다. 아마 수가 더 커질수록 성능차이는 더 크게 차이날 것이다.
~~~
public long parallelSum(){
    return Stream.iterate(1L, i -> i+1)
        .limit(N)
        .parallel()
        .reduce(0L, Long::sum);
}

~~~

<br>

## 특화된 메서드 사용
멀티코어 프로세스를 활용해서 효과적으로 합계 연산을 병렬로 실행하려면 어떻게 해야 할까?
- LongStream.rangeClosed를 사용해야 한다.

이 메서드는 iterate에 비해 다음과 같은 장점을 제공한다.
- LongStream.rangeClosed는 기본형 long을 직접 사용하므로 박싱과 언박싱 오버헤드가 사라진다.
- LongStream.rangeClosed는 쉽게 청크로 분할할 수 있는 숫자 범위를 생산한다. 
    - 예를 들어 1-20 범위의 숫자를 1-5, 6-10, 11-15, 16-20 범위의 숫자로 분할할 수 있다.

<br>

###rangedSum
언박싱과 관련한 오버헤드가 어느정도 되는지 rangedSum 스트림을 돌려보고나서 sequentialSum과 비교해보면 알 수 있다.
- 1413ms → 63ms로 줄어들었다,
- 특화되지 않은 스트림을 처리할 때는 오토박싱, 언박싱 등의 오버헤드를 수반하기 때문에 sequentialSum의 결과와 같은 성능 부하가 발생한다.
~~~
public long rangedSum(){
    return LongStream.rangeClosed(1, N)
        .reduce(0L, Long::sum);
}
~~~

<br>

###parallelRangedSum
이제 여기다 병렬 스트림을 적용하면 드디어 순차 실행(iterativeSum)보다 빠른 병렬 리듀싱이 만들어진다.
- 순차 실행은 52.051ms, 이 병렬 스트림은 14.067ms로 3배 이상 차이난다.
- 병렬 스트림도 올바른 자료구조를 사용해야 최적의 성능을 발휘함을 알 수 있다.
~~~
public long parallelRangedSum(){
    return LongStream.rangeClosed(1, N)
        .parallel()
        .reduce(0L, Long::sum);
}
~~~

<br>


## 정리
올바른 자료구조를 선택해야 병렬 실행도 최적의 성능을 발휘할 수 있다. parallelSum과 같이 사용하면 오히려 더 최악의 성능을 불러온다.

결국 함수형 프로그래밍을 올바로 사용하면 반복적으로 코드를 실행하는 방법에 비해 최신 멀티 코어 CPU가 제공하는 병렬 실행의 힘을 단순하게 직접적으로 얻을 수 있다.

하지만 병렬화가 완전 공짜라는 아닌 사실은 기억하자. 
- 병렬화를 이용하려면 스트림을 재귀적으로 분할해야 하고, 각 서브스트림을 서로 다른 스레드의 리듀싱 연산으로 할당하고, 이들 결과를 하나의 값으로 합쳐야 한다.
- 멀티코어 간의 데이터 이동은 생각보다 비싸다. 따라서 코어 간에 데이터 전송 시간보다 훨씬 오래 걸리는 작업만 병렬로 다른 코어에서 수행하는 것이 바람직하다.

또한 상황에 따라 쉽게 병렬화를 이용할 수 있거나 아니면 아예 병렬화를 이용할 수 없는 때도 있다.

그리고 스트림을 병렬화해서 코드 실행 속도를 빠르게 하고 싶으면 항상 병렬화를 올바르게 사용하고 있는지 확인해야 한다. 
