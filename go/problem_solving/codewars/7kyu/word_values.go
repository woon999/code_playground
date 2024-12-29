package main

import "fmt"

func NameValue(my_list []string) []int {
	var result []int
	for i, v := range my_list {
		var sum int
		for _, c := range v {
			if c != ' ' {
				sum += int(c-'a') + 1
			}
		}
		result = append(result, sum*(i+1))
	}
	return result
}

func main() {
	fmt.Println(NameValue([]string{"abc", "abc abc"})) //  == []int{6,24}
}
