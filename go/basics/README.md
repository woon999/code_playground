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