package main

import "fmt"

func ReverseWords(str string) string {
	var result string
	var word string

	for _, v := range str {
		if v == ' ' {
			result = result + word + " "
			word = ""
		} else {
			word = string(v) + word
		}
	}

	return result + word
}

func main() {
	fmt.Println(ReverseWords("hello world!") == "olleh !dlrow")
	fmt.Println(ReverseWords("The quick brown fox jumps over the lazy dog.") == "ehT kciuq nworb xof spmuj revo eht yzal .god")
}
