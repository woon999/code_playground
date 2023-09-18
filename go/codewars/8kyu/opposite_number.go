package main

func Opposite(value int) int {
	return -value
}

func main() {
	println(Opposite(1) == -1)
	println(Opposite(25) == -25)
}
