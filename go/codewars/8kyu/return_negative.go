package main

// https://www.codewars.com/kata/55685cd7ad70877c23000102/train/go

import "fmt"

func MakeNegative(x int) int {
	if x > 0 {
		return -x
	}
	return x
}

func main() {
	fmt.Println(MakeNegative(1) == -1)
}
