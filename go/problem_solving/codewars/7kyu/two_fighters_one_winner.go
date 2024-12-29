package main

import "fmt"

type Fighter struct {
	Name                    string
	Health, DamagePerAttack int
}

func DeclareWinner(fighter1 Fighter, fighter2 Fighter, firstAttacker string) string {
	var first, second Fighter
	if fighter1.Name == firstAttacker {
		first, second = fighter1, fighter2
	} else {
		first, second = fighter2, fighter1
	}
	for {
		second.Health -= first.DamagePerAttack
		if second.Health <= 0 {
			return first.Name
		}
		first.Health -= second.DamagePerAttack
		if first.Health <= 0 {
			return second.Name
		}
	}
}

func main() {
	fmt.Println(DeclareWinner(Fighter{"Lew", 10, 2}, Fighter{"Harry", 5, 4}, "Lew") == "Lew")
}
