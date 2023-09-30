package main

import "fmt"

func Gimme(array [3]int) int {
	var min, max int
	for i, v := range array {
		if v < array[min] {
			min = i
		}
		if v > array[max] {
			max = i
		}
	}
	for i, v := range array {
		if v > array[min] && v < array[max] {
			return i
		}
	}
	return 0
}

func main() {
	fmt.Println(Gimme([3]int{2, 3, 1}) == 0)
	fmt.Println(Gimme([3]int{5, 10, 14}) == 1)
	fmt.Println(Gimme([3]int{1, 3, 4}) == 1)
	fmt.Println(Gimme([3]int{15, 10, 14}) == 2)
}
