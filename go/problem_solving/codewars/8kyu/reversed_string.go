package main

import "fmt"

func Solution(word string) string {
	var reversed string
	for _, v := range word {
		reversed = string(v) + reversed
	}
	return reversed
}

func main() {
	fmt.Println(Solution("world") == "dlrow")
	fmt.Println(Solution("hello") == "olleh")
	fmt.Println(Solution("") == "")
	fmt.Println(Solution("h") == "h")
}
