package main

import (
	"fmt"
	"strings"
)

func Contamination(text, char string) string {
	return strings.Repeat(char, len(text))
}

func main() {
	fmt.Println(Contamination("abc", "z") == "zzz")
	fmt.Println(Contamination("", "z") == "")
}
