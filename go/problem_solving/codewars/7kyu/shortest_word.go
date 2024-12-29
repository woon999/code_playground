package main

import (
	"fmt"
	"strings"
)

func FindShort(s string) int {
	shortest := len(s)
	for _, word := range strings.Split(s, " ") {
		if len(word) < shortest {
			shortest = len(word)
		}
	}
	return shortest
}

func main() {
	fmt.Println(FindShort("bitcoin take over the world maybe who knows perhaps") == 3)
}
