package main

import "fmt"

func Past(h, m, s int) int {
	return h*3600000 + m*60000 + s*1000
}

func main() {
	fmt.Println(Past(0, 1, 1) == 61000)
}
