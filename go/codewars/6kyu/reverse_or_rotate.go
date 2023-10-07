package main

import "fmt"

func Revrot(s string, n int) string {
	if len(s) < n || n == 0 {
		return ""
	}
	var result string
	for i := 0; i < len(s)-n+1; i += n {
		var sum int
		for _, v := range s[i : i+n] {
			sum += int(v-'0') * int(v-'0') * int(v-'0')
		}
		if sum%2 == 0 {
			result += reverse(s[i : i+n])
		} else {
			result += rotate(s[i : i+n])
		}
	}
	return result
}

func reverse(s string) string {
	var result string
	for _, v := range s {
		result = string(v) + result
	}
	return result
}

func rotate(s string) string {
	return s[1:] + s[:1]
}

func main() {
	fmt.Println(Revrot("123456987654", 6) == "234561876549")
}
