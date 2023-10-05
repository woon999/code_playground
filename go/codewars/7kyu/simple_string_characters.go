package main

import "fmt"

func Solve(s string) []int {
	var upper, lower, digit, special int
	for _, v := range s {
		switch {
		case v >= 'A' && v <= 'Z':
			upper++
		case v >= 'a' && v <= 'z':
			lower++
		case v >= '0' && v <= '9':
			digit++
		default:
			special++
		}
	}
	return []int{upper, lower, digit, special}
}

func main() {
	fmt.Println(Solve("Codewars@codewars123.com")) // [1,18,3,2]
}
