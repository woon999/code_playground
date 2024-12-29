package main

import "fmt"

func PositiveSum(numbers []int) int {
	var sum int
	for _, v := range numbers {
		if v > 0 {
			sum += v
		}
	}
	return sum
}

func main() {
	fmt.Println(PositiveSum([]int{1, 2, 3, 4, 5}) == 15)
}
