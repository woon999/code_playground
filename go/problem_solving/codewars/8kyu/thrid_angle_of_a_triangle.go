package main

// https://www.codewars.com/kata/5a023c426975981341000014/train/go

import "fmt"

func OtherAngle(a int, b int) int {
	return 180 - (a + b)
}

func main() {
	fmt.Println(OtherAngle(30, 60) == 90)
	fmt.Println(OtherAngle(60, 60) == 60)
	fmt.Println(OtherAngle(43, 78) == 59)
	fmt.Println(OtherAngle(10, 20) == 150)
}
