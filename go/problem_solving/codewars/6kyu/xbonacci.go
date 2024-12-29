package main

import "fmt"

// ./solution.go:10:9: cannot use result (variable of type []int64) as []int value in return statement
func Xbonacci(signature []int, n int) []int {
	if n <= 0 {
		return []int{}
	}
	if n <= len(signature) {
		return signature[:n]
	}

	result := make([]int, n)
	copy(result, signature)

	for i := len(signature); i < n; i++ {
		sum := 0
		for j := 1; j <= len(signature); j++ {
			sum += result[i-j]
		}
		result[i] = sum
	}

	return result
}

func main() {
	fmt.Println(Xbonacci([]int{0, 1}, 10)) // == []int{0, 1, 1, 2, 3, 5, 8, 13, 21, 34}
	fmt.Println(Xbonacci([]int{1, 1}, 10)) // == []int{1, 1, 2, 3, 5, 8, 13, 21, 34, 55}
}
