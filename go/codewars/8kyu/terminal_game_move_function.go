package main

import "fmt"

func Move(position int, roll int) int {
	return position + roll*2
}

func main() {
	fmt.Println(Move(0, 4) == 8)
}
