package main

import "fmt"

func SequenceSum(start, end, step int) int {
	var sum int
	for i := start; i <= end; i += step {
		sum += i
	}
	return sum
}

func main() {
	fmt.Println(SequenceSum(2, 6, 2) == 12)
}
