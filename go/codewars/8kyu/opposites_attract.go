package main

import "fmt"

// https://www.codewars.com/kata/555086d53eac039a2a000083/train/go

func LoveFunc(flower1, flower2 int) bool {
	return (flower1+flower2)%2 == 1
}

func main() {
	fmt.Println(LoveFunc(1, 4) == true)
	fmt.Println(LoveFunc(2, 2) == false)
	fmt.Println(LoveFunc(0, 1) == true)
	fmt.Println(LoveFunc(0, 0) == false)
}
