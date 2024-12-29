package main

import "fmt"

func IsValidWalk(walk []rune) bool {
	if len(walk) != 10 {
		return false
	}
	m := make(map[rune]int)
	for _, v := range walk {
		m[v]++
	}
	return m['n'] == m['s'] && m['e'] == m['w']
}

func main() {
	fmt.Println(IsValidWalk([]rune{'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's'}) == true)
}
