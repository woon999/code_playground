package main

import (
	"fmt"
	"math"
)

func RoundToNext5(n int) int {
	if n%5 == 0 {
		return n
	}

	return 5 * int(math.Ceil(float64(n)/5.0))
}

func main() {
	fmt.Println(RoundToNext5(-1) == 0)
	fmt.Println(RoundToNext5(-5))
	fmt.Println(RoundToNext5(0) == 0)
	fmt.Println(RoundToNext5(2) == 5)
	fmt.Println(RoundToNext5(3) == 5)
	fmt.Println(RoundToNext5(12) == 15)
}
