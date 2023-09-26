package main

import "fmt"

func EvenOrOdd(number int) string {
	if number%2 == 0 {
		return "Even"
	}
	return "Odd"
}

func main() {
	fmt.Println(EvenOrOdd(2) == "Even")
	fmt.Println(EvenOrOdd(0) == "Even")
}
