package main

import "fmt"

func combat(health, damage float64) float64 {
	if health-damage < 0 {
		return 0
	} else {
		return health - damage
	}
}

func main() {
	fmt.Println(combat(100, 5) == 95)
}
