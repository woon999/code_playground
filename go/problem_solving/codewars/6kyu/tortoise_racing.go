package main

import "fmt"

func Race(v1, v2, g int) [3]int {
	if v1 >= v2 {
		return [3]int{-1, -1, -1}
	}
	t := float64(g) / float64(v2-v1) * 3600
	return [3]int{int(t / 3600), int(t/60) % 60, int(t) % 60}
}

func main() {
	fmt.Println(Race(720, 850, 70)) // [3]int{0, 32, 18}
}
