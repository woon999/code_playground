package main

import "fmt"

func TwoSum(numbers []int, target int) [2]int {
	for i, n := range numbers {
		for j, m := range numbers {
			if i != j && n+m == target {
				return [2]int{i, j}
			}
		}
	}
	return [2]int{}
}

func main() {
	fmt.Println(TwoSum([]int{1, 2, 3}, 4) == [2]int{0, 2})
}
