package main

import "fmt"

func FakeBin(x string) string {
	var bin string
	for _, v := range x {
		if v < '5' {
			bin += "0"
		} else {
			bin += "1"
		}
	}
	return bin
}

func main() {
	fmt.Println(FakeBin("45385593107843568") == "01011110001100111")
}
