package main

import (
	"fmt"
	"strconv"
	"strings"
)

func HighAndLow(in string) string {
	numStrs := strings.Split(in, " ")

	high, _ := strconv.Atoi(numStrs[0])
	low := high

	for _, numStr := range numStrs {
		num, _ := strconv.Atoi(numStr)
		if num > high {
			high = num
		}
		if num < low {
			low = num
		}
	}

	return fmt.Sprintf("%d %d", high, low)
}

func main() {
	fmt.Println(HighAndLow("1 2 3 4 5") == "5 1")
	fmt.Println(HighAndLow("1 2 -3 4 5") == "5 -3")
	fmt.Println(HighAndLow("1 9 3 4 -5") == "9 -5")
}
