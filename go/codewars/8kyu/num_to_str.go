package main

import "fmt"

func NumberToString(n int) string {
	return fmt.Sprintf("%d", n)
}

func main() {
	fmt.Println(NumberToString(67) == "67")
}
