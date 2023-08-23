package main

// https://www.codewars.com/kata/56bc28ad5bdaeb48760009b0/train/go

import "fmt"

func RemoveChar(word string) string {
	return word[1 : len(word)-1]
}

func main() {
	fmt.Println(RemoveChar("eloquent") == "loquen")
	fmt.Println(RemoveChar("country") == "ountr")
	fmt.Println(RemoveChar("person") == "erso")
	fmt.Println(RemoveChar("place") == "lac")
	fmt.Println(RemoveChar("ok") == "")
	fmt.Println(RemoveChar("ooopsss") == "oopss")
}
