package main

import "fmt"

func CloseCompare(a, b, margin float64) int {
	if a == b || (a > b && a-b < margin) || (b > a && b-a < margin) {
		return 0
	} else if a > b {
		return 1
	} else {
		return -1
	}
}

func main() {
	fmt.Println(CloseCompare(4, 5, 0) == -1)
}
