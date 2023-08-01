package main

// https://www.codewars.com/kata/55a2d7ebe362935a210000b2/train/go

import "fmt"

func SmallestIntegerFinder(numbers []int) int {
	min := numbers[0]
	for _, v := range numbers {
		if v < min {
			min = v
		}
	}
	return min
}

func main() {
	fmt.Println(SmallestIntegerFinder([]int{34, 15, 88, 2}) == 2)
	fmt.Println(SmallestIntegerFinder([]int{34, -345, -1, 100}) == -345)
}
