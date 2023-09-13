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
	var p *int

	i := 42
	p = &i
	fmt.Println(*p) // 42

	*p = 21
	fmt.Println(i) // 21

	doSomething(p)
	fmt.Println(i) // 441

	pointer_basic()

	person := &Person{Name: "Lee", Age: 10}
	fmt.Println(person.Name)
	fmt.Println(person.Age)

	person.setName("Kim")
	fmt.Println(person.Name)

	a := []int{1, 2, 3}
	add(&a)
	fmt.Println(a)

}
