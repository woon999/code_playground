package main

import (
	"fmt"
	"sort"
)

func QueueTime(customers []int, n int) int {
	result := 0
	if n == 1 {
		for _, v := range customers {
			result += v
		}
		return result
	}

	queues := make([]int, n)
	for _, v := range customers {
		queues[0] += v
		sort.Ints(queues)
	}
	result = queues[n-1]
	return result
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
