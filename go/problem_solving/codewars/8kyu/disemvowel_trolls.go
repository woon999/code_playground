package main

import (
	"fmt"
	"regexp"
)

func Disemvowel(comment string) string {
	//var disemvoweled string
	//for _, v := range comment {
	//	switch v {
	//	case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U':
	//		continue
	//	default:
	//		disemvoweled += string(v)
	//	}
	//}
	//return disemvoweled

	return regexp.MustCompile(`(?i)[aeiou]`).ReplaceAllString(comment, "")
}

func main() {
	fmt.Println(Disemvowel("This website is for losers LOL!") == "Ths wbst s fr lsrs LL!")
}
