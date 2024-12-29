package main

import "fmt"

type MyString string

func (s MyString) IsUpperCase() bool {
	for _, v := range s {
		if v >= 'a' && v <= 'z' {
			return false
		}
	}
	return true
}

func main() {
	fmt.Println(MyString("c").IsUpperCase() == false)
}
