package main

import "fmt"

func countSheep(num int) string {
	var sheep string
	for i := 1; i <= num; i++ {
		sheep += fmt.Sprintf("%d sheep...", i)
	}
	return sheep

}

func main() {
	fmt.Println(countSheep(1) == "1 sheep...")
	fmt.Println(countSheep(2) == "1 sheep...2 sheep...")
}
