package main

import "fmt"

func ToAlternatingCase(str string) string {
	var result string
	for _, char := range str {
		if char >= 65 && char <= 90 {
			result += string(char + 32)
		} else if char >= 97 && char <= 122 {
			result += string(char - 32)
		} else {
			result += string(char)
		}
	}
	return result
}

func main() {
	fmt.Println(ToAlternatingCase("hello world") == "HELLO WORLD")
	fmt.Println(ToAlternatingCase("HELLO WORLD") == "hello world")
}
