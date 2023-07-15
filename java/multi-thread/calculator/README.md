# 자바 멀티 쓰레딩을 활용한 계산 성능 측정 
멀티스레딩은 하나의 애플리케이션 내에서 여러 개의 스레드를 동시에 실행하는 기법을 의미한다. 
각 스레드는 독립적인 작업을 수행하고 동시에 수행되므로 병렬처리가 가능하게 된다. 이로 인해 프로그램의 성능이 향상될 수 있다.


자바 멀티 쓰레딩 기법을 분석하기 위해 1부터 20억까지의 숫자를 모두 합하는 로직을 멀티 쓰레딩으로 구현하고 성능을 측정해보고자 한다.

<br>


# 단일 쓰레드
## code
```java
public class Main {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long sum = 0;
		for(int i = 1; i <= 2_000_000_000; i++) {
			sum += i;
		}
		System.out.println("sum = " + sum);
		long end = System.currentTimeMillis();
		System.out.println("time = " + (end - start) + "ms");
	}
}

```

## result
```
sum = 2000000001000000000
time = 914ms
```

<br>

# 멀티 쓰레드
20억개에 달하는 숫자를 쓰레드의 수만큼 나눠서 각 쓰레드에 할당하고 각 쓰레드가 할당받은 숫자들을 합한 값을 다시 합하는 방식으로 멀티 쓰레딩 기법을 사용해보자.

## code
```java
public class MyThread extends Thread {
	public static long sum;
	private int start;
	private int end;

	public MyThread(int start, int end) {
		System.out.println("Thread " + start + " to " + end + " created.");
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		long temp = 0;
		for(int i = start; i <= end; i++) {
			temp += i;
		}

		sum += temp;
	}
}
```

## 2개 쓰레드
755ms -> 642ms 로 약 100ms 정도 성능이 향상되었다.

```
Thread 1 to 1000000000 created.
Thread 1000000001 to 2000000000 created.

sum = 2000000001000000000
time = 771ms
```

## 4개 쓰레드
```
Thread 1 to 500000000 created.
Thread 500000001 to 1000000000 created.
Thread 1000000001 to 1500000000 created.
Thread 1500000001 to 2000000000 created.

sum = 2000000001000000000
time = 482ms
```
755ms -> 386ms 로 약 2배 성능이 향상되었다.


## 8개 쓰레드
```
Thread 1 to 250000000 created.
Thread 250000001 to 500000000 created.
Thread 500000001 to 750000000 created.
Thread 750000001 to 1000000000 created.
Thread 1000000001 to 1250000000 created.
Thread 1250000001 to 1500000000 created.
Thread 1500000001 to 1750000000 created.
Thread 1750000001 to 2000000000 created.

sum = 2000000001000000000
time = 376ms
```
755ms -> 376ms 로 약 2배 성능이 향상되었다.

## 16개 쓰레드
```
Thread 1 to 125000000 created.
Thread 125000001 to 250000000 created.
Thread 250000001 to 375000000 created.
Thread 375000001 to 500000000 created.

...

Thread 1625000001 to 1750000000 created.
Thread 1750000001 to 1875000000 created.
Thread 1875000001 to 2000000000 created.

sum = 2000000001000000000
time = 311ms
```
755ms -> 311ms 로 약 2.5배 성능이 향상되었다.

## 32개 쓰레드 
```
Thread 1 to 62500000 created.
Thread 62500001 to 125000000 created.
Thread 125000001 to 187500000 created.
Thread 187500001 to 250000000 created.

...

Thread 1812500001 to 1875000000 created.
Thread 1875000001 to 1937500000 created.
Thread 1937500001 to 2000000000 created.

sum = 2000000001000000000
time = 305ms
```
755ms -> 305ms 로 약 2.5배 성능이 향상되었다.

<br>

# 결론
1부터 20억까지의 합을 구하는 문제를 여러 개의 스레드로 나눠서 처리해보았다. 
스레드의 수를 늘릴수록 작업을 더욱 세분화하여 처리하므로 전체 계산 시간이 단축되는 것을 확인할 수 있다.

| Threads | Time (ms) |
|---------|-----------|
| 1       | 914       |
| 2       | 771       |
| 4       | 482       |
| 8       | 376       |
| 16      | 311       |
| 32      | 305       |

하지만, 스레드의 수를 무조건 늘리는 것이 성능 향상에 늘 긍정적이지만은 않다. 
스레드를 생성하고 관리하는 데도 비용이 들며, 과도한 스레드 생성은 시스템의 오버헤드를 증가시키고 성능 저하를 초래할 수 있다. 
실제로, 16개에서 32개의 스레드로 증가시킨 경우에는 성능 향상이 미미하게 발생한 것을 볼 수 있다. 
이는 스레드의 수가 증가함에 따라 스레드 관리에 소요되는 오버헤드가 증가하여 성능 향상이 둔화된 것으로 해석될 수 있다.


따라서 멀티스레딩을 활용할 때는 작업의 특성과 시스템의 상황을 고려하여 적절한 수의 스레드를 사용하는 것이 중요하다. 
특히 공유 자원에 대한 동기화 문제를 잘 고려해야 하며, 스레드의 수와 작업의 분할 방법을 적절하게 조정하여 최적의 성능을 달성해야 한다.





