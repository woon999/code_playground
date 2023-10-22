package main

import (
	"fmt"
	"math"
)

func NearestSq(n int) int {
	//var i int
	//for i = 1; i*i < n; i++ {
	//}
	//if (i*i - n) < (n - (i-1)*(i-1)) {
	//	return i * i
	//} else {
	//	return (i - 1) * (i - 1)
	//}

	n = int(math.Round(math.Sqrt(float64(n))))

	return n * n
}

func main() {
	fmt.Println(NearestSq(1) == 1)
	fmt.Println(NearestSq(2) == 1)
	fmt.Println(NearestSq(10) == 9)

}
