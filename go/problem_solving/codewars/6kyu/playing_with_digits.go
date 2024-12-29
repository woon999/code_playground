package main

import "math"

func DigPow(n, p int) int {
	N := n
	var digits []int

	for n > 0 {
		digits = append(digits, n%10)
		n = n / 10
	}

	sum := 0
	for i := len(digits) - 1; i >= 0; i-- {
		sum = sum + int(math.Pow(float64(digits[i]), float64(p)))
		p = p + 1
	}
	if sum%N == 0 {
		return sum / N
	}
	return -1
}

func main() {
	println(DigPow(89, 1))    // == 1
	println(DigPow(92, 1))    // == -1
	println(DigPow(46288, 3)) // == 51
}
