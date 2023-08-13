package main

import "fmt"

func BoolToWord(word bool) string {
	if word {
		return "Yes"
	}
	return "No"
}

func main() {
	fmt.Println(BoolToWord(true) == "Yes")
	fmt.Println(BoolToWord(false) == "No")
}
