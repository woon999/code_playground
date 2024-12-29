package main

import (
	"fmt"
	"strings"
)

func IsPalindrome(str string) bool {
	s := strings.ToLower(str)

	for i := 0; i < len(s)/2; i++ {
		if s[i] != s[len(s)-1-i] {
			return false
		}
	}
	return true
}

func main() {
	fmt.Println(IsPalindrome("") == false)
	fmt.Println(IsPalindrome("a") == true)
	fmt.Println(IsPalindrome("Anna") == true)
	fmt.Println(IsPalindrome("walter") == false)
}
