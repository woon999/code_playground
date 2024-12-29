package main

import (
	"fmt"
	"sort"
	"strings"
)

func TwoToOne(s1 string, s2 string) string {
	chars := strings.Split(s1+s2, "")
	sort.Strings(chars)
	result := ""
	for _, s := range chars {
		chr := string(s)
		if !strings.Contains(result, chr) {
			result = result + chr
		}
	}
	return result
}

func main() {
	fmt.Println(TwoToOne("aretheyhere", "yestheyarehere") == "aehrsty")
	fmt.Println(TwoToOne("loopingisfunbutdangerous", "lessdangerousthancoding") == "abcdefghilnoprstu")
}
