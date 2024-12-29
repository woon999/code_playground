package main

import (
	"fmt"
	"strings"
)

// https://www.codewars.com/kata/55c45be3b2079eccff00010f/train/go

func Order(sentence string) string {
	words := strings.Split(sentence, " ")
	wordsWithNumbers := make([]string, len(words))
	for _, word := range words {
		for _, char := range word {
			if char >= 49 && char <= 57 {
				wordsWithNumbers[char-49] = word
			}
		}
	}
	return strings.Join(wordsWithNumbers, " ")
}

func main() {
	fmt.Println(Order("is2 Thi1s T4est 3a") == "Thi1s is2 3a T4est")
}
