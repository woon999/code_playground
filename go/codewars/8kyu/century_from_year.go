package main

import "fmt"

func century(year int) int {
	return (year-1)/100 + 1
}

func main() {
	fmt.Println(century(1705) == 18)
}
