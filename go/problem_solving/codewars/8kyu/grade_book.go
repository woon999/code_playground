package main

import "fmt"

func GetGrade(a, b, c int) rune {
	switch (a + b + c) / 30 {
	case 10, 9:
		return 'A'
	case 8:
		return 'B'
	case 7:
		return 'C'
	case 6:
		return 'D'
	default:
		return 'F'
	}
}

func main() {
	fmt.Println(GetGrade(95, 90, 93) == 'A')
	fmt.Println(GetGrade(100, 85, 96) == 'A')
	fmt.Println(GetGrade(92, 93, 94) == 'A')
	fmt.Println(GetGrade(70, 70, 100) == 'B')
}
