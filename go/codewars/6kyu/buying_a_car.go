package main

import (
	"fmt"
	"math"
)

func NbMonths(startPriceOld, startPriceNew, savingperMonth int, percentLossByMonth float64) [2]int {
	var months int
	var savings float64
	var old, new float64 = float64(startPriceOld), float64(startPriceNew)

	for ; old+savings < new; months++ {
		if months%2 == 1 {
			percentLossByMonth += 0.5
		}

		old *= 1 - percentLossByMonth/100
		new *= 1 - percentLossByMonth/100
		savings += float64(savingperMonth)
	}

	return [2]int{months, int(math.Round(old + savings - new))}
}

func main() {
	fmt.Println(NbMonths(2000, 8000, 1000, 1.5))  // == [2]int{6, 766}
	fmt.Println(NbMonths(12000, 8000, 1000, 1.5)) // == [2]int{0, 4000}
	fmt.Println(NbMonths(7500, 32000, 300, 1.55)) // == [2]int{25, 122}
}
