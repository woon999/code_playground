package main

import "fmt"

func Goals(laLigaGoals, copaDelReyGoals, championsLeagueGoals int) int {
	return laLigaGoals + copaDelReyGoals + championsLeagueGoals
}

func main() {
	fmt.Println(Goals(0, 0, 0) == 0)
	fmt.Println(Goals(43, 10, 5) == 58)
}
