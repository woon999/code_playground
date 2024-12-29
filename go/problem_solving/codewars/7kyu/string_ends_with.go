package main

import "fmt"

func solution(str, ending string) bool {
	if len(ending) > len(str) {
		return false
	}
	return str[len(str)-len(ending):] == ending
}

func main() {
	fmt.Println(solution("abc", "bc") == true)
}
