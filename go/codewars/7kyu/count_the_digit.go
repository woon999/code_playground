package main

import "fmt"

func NbDig(n int, d int) int {
	var count int
	for i := 0; i <= n; i++ {
		for _, v := range fmt.Sprintf("%d", i*i) {
			if int(v-'0') == d {
				count++
			}
		}
	}
	return count
}

func main() {
	fmt.Println(NbDig(5750, 0) == 4700)
}
