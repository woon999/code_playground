package main

import "fmt"

func PowersOfTwo(n int) []uint64 {
	var result []uint64
	for i := 0; i <= n; i++ {
		result = append(result, uint64(1<<i))
	}
	return result
}

func main() {
	fmt.Println(PowersOfTwo(1))
}
