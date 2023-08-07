package main

import "fmt"

func DoubleInteger(i int) int {
	return i * 2
}

func main() {
	fmt.Println(DoubleInteger(1) == 2)
	fmt.Println(DoubleInteger(2) == 4)
}
