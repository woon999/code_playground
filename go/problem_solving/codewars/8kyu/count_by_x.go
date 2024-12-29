package main

func CountBy(x, n int) []int {
	r := make([]int, n)
	for i := 0; i < n; i++ {
		r[i] = (i + 1) * x
	}
	return r
}

func main() {
	println(CountBy(1, 5))
}
