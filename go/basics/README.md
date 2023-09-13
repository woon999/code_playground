# Go Basics
## Function
1. 함수는 리턴값을 여러개 가질 수 있음
2. 함수는 클로저로 사용될 수 있음
3. 함수를 값으로 사용할 수 있음 (일급 객체)
4. 함수는 익명으로 사용할 수 있음
``` go
func sum(a int, b int) int {
	return a + b
}

func sum2(nums ...int) int {
	total := 0
	for _, num := range nums {
		total += num
	}
	return total
}

// 일급 함수 - 값처럼 취급
func apply(fn func(int, int) int, a int, b int) int {
	return fn(a, b)
}

// 익명 함수, 클로저
func counter() func() int {
	count := 0
	return func() int {
		count++
		return count
	}
}
```

<br>

## Pointer 
go 에서는 포인터를 지원하지만 C와는 다르다. 그리고 메모리 관리는 GC가 수행한다.
1. 메모리 안정성을 위해 포인터 연산 불가능 
2. call by value 만 허용. 함수 인자로 변수 전달시 변수 복사본 사용
3. nil 포인터 지원하지만 역참조(*)시 패닉 발생 
4. 포인터 매개변수 전달시, 포인터 자체 복사본 사용
5. array, struct도 포인터로 전달 가능
``` go
package main

import "fmt"

// 포인터 매개변수
func doSomething(p *int) {
	*p *= 21
}

func pointer_basic() {
	var counter byte = 100
	P := &counter
	V := *P

	fmt.Printf("counter : %-16d address: %-16p\n", counter, &counter)
	fmt.Printf("P       : %-16p address: %-16p *P : %-16d\n", P, &P, *P)
	fmt.Printf("V       : %-16d address: %-16p\n", V, &V)

	// call by value 이기 때문에 V값만 바뀜
	V = 200
	fmt.Println("V = 200")
	fmt.Printf("counter : %-16d address: %-16p\n", counter, &counter)
	fmt.Printf("P       : %-16p address: %-16p *P : %-16d\n", P, &P, *P)
	fmt.Printf("V       : %-16d address: %-16p\n", V, &V)

	V = counter // reset V
	counter++   // 값 복사이기 때문에 V값은 바뀌지 않음
	fmt.Println("V = counter++")
	fmt.Printf("counter : %-16d address: %-16p\n", counter, &counter)
	fmt.Printf("P       : %-16p address: %-16p *P : %-16d\n", P, &P, *P)
	fmt.Printf("V       : %-16d address: %-16p\n", V, &V)

	// P는 counter 주소값을 가지고 있기 때문에 counter 값이 바뀜
	*P = 25
	fmt.Println("*P = 25")
	fmt.Printf("counter : %-16d address: %-16p\n", counter, &counter)
	fmt.Printf("P       : %-16p address: %-16p *P : %-16d\n", P, &P, *P)
	fmt.Printf("V       : %-16d address: %-16p\n", V, &V)

}

type Person struct {
	Name string
	Age  int
}

// struct pointer receiver
func (p *Person) setName(name string) {
	p.Name = name
}

// array pointer parameter
func add(a *[]int) {
	*a = append(*a, 1)
}

func main() {
	person := &Person{Name: "Lee", Age: 10}
	fmt.Println(person.Name)
	fmt.Println(person.Age)

	person.setName("Kim")
	fmt.Println(person.Name)

	a := []int{1, 2, 3}
	add(&a)
	fmt.Println(a)
}
```

<br>

## 동시성 Concurrency
아주 빠르게 작업 처리륻 하는 동시성을 위해 경량 쓰레드(Goroutine) 지원한다. 이는 OS 스레드에 비해 메모리 소비, 생성, 스케줄링 비용이 적다.

### 특징 
- 코어의 개수와 쓰레드의 개수를 동일하게 하여 context switching 비용 최소화
- 쓰레드는 OS가 관리하지 않고 Go 런타임이 관리한다


### Go Scheduler
- G(Goroutine): Goroutine는 말그대로 고루틴 의미하며, 고루틴을 구성하는 논리적 구조체의 구현체를 말한다
- M(Machine): Machine는 OS 쓰레드를 의미하며, 실제 OS 쓰레드가 아닌 논리적 구현체로 표준 POSIX 쓰레드를 따른다고 한다.
- P(Processor): Processor는 프로세서를 의미하며, 실제 물리적 프로세서를 말하는게 아니라 논리적인 프로세서로 정확히는 스케줄링과 관련된 Context 정보를 가지고 있다
- LRQ(LocalRunQueue): P에 종속되어 있는 Run Queue, 이 LRQ에 실행 가능한 고루틴들이 적재된다
- GRQ(GlobalRunQueue): LRQ에 할당되지 못한 고루틴을 관리하는 Run Queue, LRQ 적재되지 못한 고루틴들이 이 GRQ에 들어가 관리된다고 보면 된다

![image](https://github.com/loosie/code_playground/assets/54282927/9195fe32-46e7-4baa-aeab-7508784f767b)


### 작동 원리
- 하나의 M이 하나의 고루틴(작업)을 실행
- 이미 실행중이던 고루틴이 작업을 마치거나 syscall을 했을 경우 Go 스케줄러는 LRQ에서 대기중인 고루틴을 꺼내 다음 작업을 실행
- 새로 추가되는 작업(이미 돌아가고 있는 작업 혹은 새로운 고루틴)을 LRQ에 추가한다.
- 또한 스케줄러는 성능 향상을 위해서 아래와 같은 상황에서 스케줄링을 통해 최적화하는 작업이 있다.

![image](https://github.com/loosie/code_playground/assets/54282927/7bc3bc16-e537-4f6c-ba82-aef939760a16)

### 사용법
- 동시적으로 실행되는 작업 `go` 키워드를 사용한다.
- sync.WaitGroup으로 실행 순서 제어가 가능하다.
  - Add(): goroutine 추가
  - Done(): goroutine 종료
  - Wait(): 모든 goroutine이 종료될 때까지 대기
- channel을 사용하여 goroutine간 통신이 가능하다.
  - channel은 goroutine간 데이터를 주고 받는 통로이다.
  - go 내장 데이터 타입 (thread-safe queue)
  - <img width="350" alt="스크린샷 2023-09-13 오후 6 27 14" src="https://github.com/loosie/code_playground/assets/54282927/a85c3c51-17a0-49eb-a36a-b58d5ce78cd6">

```go
func s1(ch chan string) {
	time.Sleep(2 * time.Second)
	ch <- "from server1"
}

func s2(ch chan string) {
	time.Sleep(1 * time.Second)
	ch <- "from server2"
}

func main() {
	output1 := make(chan string)
	output2 := make(chan string)

	go s1(output1)
	go s2(output2)

	select {
	case s1 := <-output1:
		fmt.Println(s1)
	case s2 := <-output2:
		fmt.Println(s2)
	}
}
```

