package main

import "fmt"

// RowSumOddNumbers returns the sum of odd numbers in a row
//
//	    		  1
//			   3     5
//			7     9    11
//		13    15    17    19
//	  21    23    25    27    29
//
// n =1 -> 1
// n =2 -> 3 + 5 = 8
func RowSumOddNumbers(n int) int {
	return n * n * n
}

func main() {
	fmt.Println(RowSumOddNumbers(1) == 1)
	fmt.Println(RowSumOddNumbers(2) == 8)
	fmt.Println(RowSumOddNumbers(3) == 27)
	fmt.Println(RowSumOddNumbers(4) == 64)
	fmt.Println(RowSumOddNumbers(5) == 125)
}
