package main

import "fmt"

func Feast(beast string, dish string) bool {
	return beast[0] == dish[0] && beast[len(beast)-1] == dish[len(dish)-1]
}

func main() {
	fmt.Println(Feast("great blue heron", "garlic naan") == true)
}
