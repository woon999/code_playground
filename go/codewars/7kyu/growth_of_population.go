package main

import "fmt"

func NbYear(p0 int, percent float64, aug int, p int) int {
	var y int
	for ; p0 < p; y++ {
		p0 = int(float64(p0)*(1+percent/100) + float64(aug))
	}
	return y
}

func main() {
	fmt.Println(NbYear(1500, 5, 100, 5000) == 15)
	fmt.Println(NbYear(1500000, 2.5, 10000, 2000000) == 10)
	fmt.Println(NbYear(1500000, 0.25, 1000, 2000000) == 94)
}
