package main

import "fmt"

func QueueTime(customers []int, n int) int {
	if len(customers) == 0 {
		return 0
	}
	if n == 1 {
		var sum int
		for _, v := range customers {
			sum += v
		}
		return sum
	}
	if n >= len(customers) {
		var max int
		for _, v := range customers {
			if v > max {
				max = v
			}
		}
		return max
	}
	if n == len(customers) {
		return customers[0]
	}

	var tills []int
	for i := 0; i < n; i++ {
		tills = append(tills, customers[i])
	}

	for i := n; i < len(customers); i++ {
		var min int
		for _, v := range tills {
			if v < min || min == 0 {
				min = v
			}
		}
		for j, v := range tills {
			if v == min {
				tills[j] += customers[i]
				break
			}
		}
	}

	var max int
	for _, v := range tills {
		if v > max {
			max = v
		}
	}
	return max
}

func main() {
	fmt.Println(QueueTime([]int{5, 3, 4}, 1) == 12)
	fmt.Println(QueueTime([]int{10, 2, 3, 3}, 2) == 10)
	fmt.Println(QueueTime([]int{2, 3, 10}, 2) == 12)
	fmt.Println(QueueTime([]int{2, 3, 10}, 3) == 10)
	fmt.Println(QueueTime([]int{2, 3, 10}, 10) == 10)
	fmt.Println(QueueTime([]int{2, 3, 10}, 1) == 15)
	fmt.Println(QueueTime([]int{1, 2, 3, 4, 5}, 1) == 15)
	fmt.Println(QueueTime([]int{1, 2, 3, 4, 5}, 100) == 5)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 2) == 9)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 3) == 7)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 4) == 5)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 5) == 4)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 6) == 3)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 7) == 3)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 8) == 3)
	fmt.Println(QueueTime([]int{2, 2, 3, 3, 4, 4}, 9) == 3)
}
