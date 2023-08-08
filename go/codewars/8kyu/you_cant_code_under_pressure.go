package main

// https://www.codewars.com/kata/53ee5429ba190077850011d4/solutions/go

import "fmt"

func DoubleInteger(i int) int {
	return i * 2
}

func main() {
	fmt.Println(DoubleInteger(1) == 2)
	fmt.Println(DoubleInteger(2) == 4)
}
