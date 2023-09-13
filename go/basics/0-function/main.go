package main

import "fmt"

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

func main() {
	fmt.Println(sum(1, 2))     // 3
	fmt.Println(sum2(1, 2, 3)) // 6

	fmt.Println(apply(sum, 5, 6))

	count := counter()
	fmt.Println(count()) // 1
	fmt.Println(count()) // 2
}
