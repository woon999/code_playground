package main

import "fmt"

func Digitize(n int) []int {
	var digits []int
	for {
		digits = append(digits, n%10)
		n /= 10
		if n == 0 {
			return digits
		}
	}
}

func main() {
	fmt.Println(Digitize(35231))    // [1,3,2,5,3]
	fmt.Println(Digitize(23582357)) // [7,5,3,2,8,5,3,2]
	fmt.Println(Digitize(0))        // [8,3,7,4,6,7,4,8,9]
}
