package main

import "fmt"

func ArrayDiff(a, b []int) []int {
	m := make(map[int]bool)
	for _, n := range b {
		m[n] = true
	}

	r := []int{}
	for _, n := range a {
		if !m[n] {
			r = append(r, n)
		}
	}

	return r

	//if len(a) == 0 {
	//	return []int{}
	//}
	//r := []int{}
	//for _, n := range a {
	//	if !contains(b, n) {
	//		r = append(r, n)
	//	}
	//}
	//return r
}

func contains(a []int, n int) bool {
	for _, m := range a {
		if m == n {
			return true
		}
	}
	return false
}

func main() {
	fmt.Println(ArrayDiff([]int{1, 2}, []int{1})) // == []int{2}
}
