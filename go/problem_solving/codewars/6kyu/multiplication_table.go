package main

import "fmt"

func MultiplicationTable(size int) [][]int {
	r := make([][]int, size)
	for i := 0; i < size; i++ {
		for j := 0; j < size; j++ {
			r[i] = append(r[i], (i+1)*(j+1))
		}
	}
	return r
}

func main() {
	fmt.Println(MultiplicationTable(3)) // [][]int{{1, 2, 3}, {2, 4, 6}, {3, 6, 9}
}
