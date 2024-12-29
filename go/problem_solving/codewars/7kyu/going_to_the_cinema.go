package main

import (
	"fmt"
	"math"
)

func Movie(card, ticket int, perc float64) int {
	var a, b float64
	reducedTicket := float64(ticket) * perc
	b += float64(card)
	for i := 1; ; i++ {
		b += reducedTicket
		a += float64(ticket)
		reducedTicket *= perc

		if a > math.Ceil(b) {
			return i
		}
	}

}

func main() {
	fmt.Println(Movie(500, 15, 0.9) == 43)
	fmt.Println(Movie(0, 10, 0.95) == 2)
	fmt.Println(Movie(2500, 20, 0.9) == 135)
	fmt.Println(Movie(905, 36, 0.74) == 29)
}
