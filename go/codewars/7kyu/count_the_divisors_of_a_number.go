package main

import "fmt"

func Divisors(n int) int {
	count := 0
	for i := 1; i <= n; i++ {
		if n%i == 0 {
			count++
		}
	}
	return count
}

func main() {
	fmt.Println(Divisors(1) == 1)
	fmt.Println(Divisors(10) == 4)
	fmt.Println(Divisors(11) == 2)
}
