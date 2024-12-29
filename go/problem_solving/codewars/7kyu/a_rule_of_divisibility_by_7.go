package main

import "fmt"

func Seven(n int64) []int {
	var steps int
	for n > 99 {
		n = n/10 - n%10*2
		steps++
	}
	return []int{int(n), steps}
}

func main() {
	fmt.Println(Seven(1021)) // == []int{10, 2}
}
