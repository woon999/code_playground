package main

import "fmt"

func CountSheeps(numbers []bool) (cnt int) {
	for _, v := range numbers {
		if v {
			cnt++
		}
	}
	return cnt
}

func main() {
	arr1 := []bool{
		true, true, true, false,
		true, true, true, true,
		true, false, true, false,
		true, false, false, true,
		true, true, true, true,
		false, false, true, true,
	}
	fmt.Print(CountSheeps(arr1) == 17)
}
