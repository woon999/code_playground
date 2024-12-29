package main

import (
	"fmt"
	"golang.org/x/text/cases"
	"golang.org/x/text/language"
)

func ToJadenCase(str string) string {
	// return strings.Title(str)
	// instead use golang.org/x/text/cases
	return cases.Title(language.English).String(str)

	//var result string
	//for i, v := range str {
	//	if i == 0 || str[i-1] == ' ' {
	//		if string(v) >= "a" && string(v) <= "z" {
	//			v -= 32
	//		}
	//		result += string(v)
	//	} else {
	//		result += string(v)
	//	}
	//}
	//return result
}

func main() {
	fmt.Println(ToJadenCase("most trees are blue") == "Most Trees Are Blue")
	fmt.Println(ToJadenCase("how can mirrors be real if our eyes aren't real") == "How Can Mirrors Be Real If Our Eyes Aren't Real")
	fmt.Println(ToJadenCase("hello") == "Hello")
	fmt.Println(ToJadenCase("All the letters in this string are lowercase") == "All The Letters In This String Are Lowercase")
}
