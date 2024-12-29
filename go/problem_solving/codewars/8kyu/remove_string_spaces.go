package main

import (
	"fmt"
	"strings"
)

func NoSpace(word string) string {
	return strings.ReplaceAll(word, " ", "")
}

func main() {
	fmt.Println(NoSpace("8 j 8   mBliB8g  imjB8B8  jl  B") == "8j8mBliB8gimjB8B8jlB")
}
