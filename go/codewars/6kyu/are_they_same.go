package main

// https://www.codewars.com/kata/550498447451fbbd7600041c/train/go

import (
	"fmt"
	"reflect"
	"sort"
)

//func Comp(array1 []int, array2 []int) bool {
//	if array1 == nil || array2 == nil {
//		return false
//	}
//
//	if len(array1) != len(array2) {
//		return false
//	}
//
//	m := make(map[int]int)
//	for _, n := range array2 {
//		m[n]++
//	}
//
//	for _, n := range array1 {
//		if m[n*n] == 0 {
//			return false
//		}
//		m[n*n]--
//	}
//
//	return true
//}

func Comp(a []int, b []int) bool {
	if a == nil || b == nil {
		return false
	}

	c, d := a[:], b[:]
	for i, n := range a {
		c[i] = n * n
	}

	sort.Ints(c)
	sort.Ints(d)
	return reflect.DeepEqual(c, d)
}

func main() {
	fmt.Println(Comp([]int{121, 144, 19, 161, 19, 144, 19, 11}, []int{121, 14641, 20736, 361, 25921, 361, 20736, 361}) == true)
}
