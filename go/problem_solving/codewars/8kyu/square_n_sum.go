package main

import "fmt"

func SquareSum(numbers []int) int {
	sum := 0
	for _, n := range numbers {
		sum += n * n
	}
	return sum
}

func main() {
	fmt.Println(SquareSum([]int{1, 2}) == 5)
}
