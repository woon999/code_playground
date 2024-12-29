package main

import "fmt"

func FindUniq(arr []float32) float32 {
	counts := make(map[float32]int)

	for _, num := range arr {
		counts[num]++
	}

	for num, count := range counts {
		if count == 1 {
			return num
		}
	}

	return 0
}

func main() {
	fmt.Println(FindUniq([]float32{1, 1, 1, 2, 1, 1}) == 2)
}
