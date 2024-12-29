package main

import "fmt"

func Incrementer(n []int) []int {
	for i := range n {
		n[i] = (n[i] + i + 1) % 10
	}
	return n
}

func main() {
	fmt.Println(Incrementer([]int{1, 2, 3}))       // == []int{2, 4, 6}
	fmt.Println(Incrementer([]int{4, 6, 7, 1, 3})) // == []int{5, 8, 0, 5, 8}
}
