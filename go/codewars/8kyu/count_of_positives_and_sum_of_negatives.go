package main

import "fmt"

func CountPositivesSumNegatives(numbers []int) []int {
	res := []int{0, 0}
	for _, v := range numbers {
		if v > 0 {
			res[0] += 1
		} else {
			res[1] += v
		}
	}
	return res
}

func main() {
	fmt.Println(CountPositivesSumNegatives([]int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15})) // [10 -65]
}
