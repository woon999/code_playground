package main

import "fmt"

func LongestConsec(strarr []string, k int) string {
	var max string
	for i := 0; i < len(strarr)-k+1; i++ {
		var s string
		for j := i; j < i+k; j++ {
			s += strarr[j]
		}
		if len(s) > len(max) {
			max = s
		}
	}
	return max
}

func main() {
	fmt.Println(LongestConsec([]string{"zone", "abigail", "theta", "form", "libe", "zas"}, 2) == "abigailtheta")
}
