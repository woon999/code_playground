package main

import "fmt"

func HowMuchILoveYou(i int) string {
	var love string
	switch i % 6 {
	case 1:
		love = "I love you"
	case 2:
		love = "a little"
	case 3:
		love = "a lot"
	case 4:
		love = "passionately"
	case 5:
		love = "madly"
	case 0:
		love = "not at all"
	}
	return love
}

func main() {
	fmt.Println(HowMuchILoveYou(1) == "I love you")
	fmt.Println(HowMuchILoveYou(2) == "a little")
	fmt.Println(HowMuchILoveYou(3) == "a lot")
	fmt.Println(HowMuchILoveYou(4) == "passionately")
	fmt.Println(HowMuchILoveYou(5) == "madly")
	fmt.Println(HowMuchILoveYou(6) == "not at all")
	fmt.Println(HowMuchILoveYou(7) == "I love you")
}
