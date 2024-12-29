package main

import "fmt"

func GetSum(a, b int) int {
	//var sum int
	//if a > b {
	//	a, b = b, a
	//}
	//for i := a; i <= b; i++ {
	//	sum += i
	//}
	//return sum

	if a > b {
		a, b = b, a
	}
	return (a + b) * (b - a + 1) / 2
}

func main() {
	fmt.Println(GetSum(1, 0) == 1)
}
