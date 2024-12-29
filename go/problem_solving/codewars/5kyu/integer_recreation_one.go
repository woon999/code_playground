package main

import (
	"fmt"
	"math"
)

func ListSquared(m, n int) [][]int {
	result := [][]int{}

	for i := m; i <= n; i++ {
		sum := sumOfSquaredDivisors(i)
		if isPerfectSquare(sum) {
			result = append(result, []int{i, sum})
		}
	}

	return result
}

func sumOfSquaredDivisors(num int) int {
	sum := 0
	for i := 1; i <= int(math.Sqrt(float64(num))); i++ {
		if num%i == 0 {
			if i == (num / i) {
				sum += i * i
			} else {
				sum += i*i + (num/i)*(num/i)
			}
		}
	}
	return sum
}

func isPerfectSquare(num int) bool {
	root := int(math.Sqrt(float64(num)))
	return root*root == num
}

func main() {
	fmt.Println(ListSquared(1, 250)) // [][]int{{1, 1}, {42, 2500}, {246, 84100}}
	fmt.Println(ListSquared(250, 500))
	fmt.Println(ListSquared(300, 600))
}
