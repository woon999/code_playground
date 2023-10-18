package main

import "fmt"

func BouncingBall(h, bounce, window float64) int {
	if h <= 0 || bounce <= 0 || bounce >= 1 || window >= h {
		return -1
	}

	var count int
	for h > window {
		h *= bounce
		count += 2
	}
	return count - 1
}

func main() {
	fmt.Println(BouncingBall(3, 0.66, 1.5) == 3)
}
