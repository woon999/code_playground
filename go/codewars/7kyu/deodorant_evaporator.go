package main

import "fmt"

func Evaporator(content float64, evapPerDay int, threshold int) int {
	var days int
	thresholdContent := content * float64(threshold) / 100
	for content > thresholdContent {
		content -= content * float64(evapPerDay) / 100
		days++
	}
	return days
}

func main() {
	fmt.Println(Evaporator(10, 10, 10) == 22)
	fmt.Println(Evaporator(10, 10, 5) == 29)
}
