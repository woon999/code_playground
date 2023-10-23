package main

import "fmt"

func Gps(s int, x []float64) int {
	var max float64
	for i := 0; i < len(x)-1; i++ {
		if x[i+1]-x[i] > max {
			max = x[i+1] - x[i]
		}
	}
	return int(max * 3600 / float64(s))
}

func main() {
	fmt.Println(Gps(15, []float64{15, 20, 25, 30, 45}) == 74)
}
