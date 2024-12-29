package main

import (
	"fmt"
	"strings"
)

func StringToArray(str string) []string {
	//var result []string
	//var word string
	//for _, v := range str {
	//	if v == ' ' {
	//		result = append(result, word)
	//		word = ""
	//	} else {
	//		word += string(v)
	//	}
	//}
	//result = append(result, word)
	//return result
	return strings.Fields(str)
}

func main() {
	fmt.Println(StringToArray("Robin Singh")) // []string{"Robin", "Singh"}
}
