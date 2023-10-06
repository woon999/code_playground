package main

import "fmt"

func MultiTable(number int) string {
	var s string
	for i := 1; i <= 10; i++ {
		s += fmt.Sprintf("%d * %d = %d\n", i, number, i*number)
	}
	return s[:len(s)-1]
}

func main() {
	fmt.Println(MultiTable(5) == "1 * 5 = 5\n2 * 5 = 10\n3 * 5 = 15\n4 * 5 = 20\n5 * 5 = 25\n6 * 5 = 30\n7 * 5 = 35\n8 * 5 = 40\n9 * 5 = 45\n10 * 5 = 50")
}
