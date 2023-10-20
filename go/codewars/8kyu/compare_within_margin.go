package main

import (
	"fmt"
	"math"
)

func CloseCompare(a, b, margin float64) int {
	if math.Abs(a-b) <= margin {
		return 0
	} else if a < b {
		return -1
	} else {
		return 1
	}
}

func main() {
	fmt.Println(CloseCompare(4, 5, 0) == -1)
	fmt.Println(CloseCompare(4, 5, 3) == 0)
}
