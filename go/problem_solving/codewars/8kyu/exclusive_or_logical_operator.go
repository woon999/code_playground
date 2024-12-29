package main

import "fmt"

func Xor(a, b bool) bool {
	return a != b
}

func main() {
	fmt.Println(Xor(true, true) == false)
	fmt.Println(Xor(false, true) == true)
	fmt.Println(Xor(false, false) == false)
	fmt.Println(Xor(true, false) == true)
	fmt.Println(Xor(Xor(false, false), Xor(false, false)) == false)
	fmt.Println(Xor(Xor(false, false), Xor(false, true)) == true)
}
