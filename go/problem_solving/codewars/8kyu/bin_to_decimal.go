package main

import "fmt"

func BinToDec(bin string) int {
	var dec int
	for _, v := range bin {
		dec = dec*2 + int(v-'0')
	}
	return dec
}

func main() {
	fmt.Println(BinToDec("1001001") == 73)
}
