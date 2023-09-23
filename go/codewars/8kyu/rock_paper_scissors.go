package main

import "fmt"

var m = map[string]string{"rock": "paper", "paper": "scissors", "scissors": "rock"}

func Rps(a, b string) string {
	if a == b {
		return "Draw!"
	}
	if m[a] == b {
		return "Player 2 won!"
	}
	return "Player 1 won!"
}

func main() {
	fmt.Println(Rps("rock", "scissors") == "Player 1 won!")
	fmt.Println(Rps("scissors", "paper") == "Player 1 won!")
	fmt.Println(Rps("paper", "rock") == "Player 1 won!")
	fmt.Println(Rps("scissors", "rock") == "Player 2 won!")
	fmt.Println(Rps("paper", "scissors") == "Player 2 won!")
	fmt.Println(Rps("rock", "paper") == "Player 2 won!")
	fmt.Println(Rps("rock", "rock") == "Draw!")
}
