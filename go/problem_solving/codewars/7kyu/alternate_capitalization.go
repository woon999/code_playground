package main

import (
	"fmt"
	"strings"
)

func Capitalize(st string) []string {
	//s1 := ""
	//s2 := ""
	//for i, v := range st {
	//	if i%2 == 0 {
	//		s1 += string(v - 32)
	//		s2 += string(v)
	//	} else {
	//		s1 += string(v)
	//		s2 += string(v - 32)
	//	}
	//}
	//return []string{s1, s2}

	s1 := ""
	s2 := ""
	for i, c := range st {
		if i%2 == 0 {
			s1 += strings.ToUpper(string(c))
			s2 += strings.ToLower(string(c))
		} else {
			s1 += strings.ToLower(string(c))
			s2 += strings.ToUpper(string(c))
		}
	}

	return []string{s1, s2}

}

func main() {
	fmt.Println(Capitalize("abcdef"))   // == []string{"AbCdEf", "aBcDeF"}
	fmt.Println(Capitalize("codewars")) // == []string{"CoDeWaRs", "cOdEwArS"}
}
