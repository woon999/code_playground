package main

import (
	"fmt"
	"strings"
)

func duplicate_count(s1 string) (c int) {
	m := make(map[rune]int)
	for _, v := range strings.ToLower(s1) {
		m[v]++
		if m[v] == 2 {
			c++
		}
	}
	return
}

func main() {
	fmt.Println(duplicate_count("aA") == 1)
}
