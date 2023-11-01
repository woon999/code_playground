package main

import (
	"fmt"
	"strings"
)

func PartList(arr []string) string {
	var results []string
	for i := 1; i < len(arr); i++ {
		left := strings.Join(arr[:i], " ")
		right := strings.Join(arr[i:], " ")
		results = append(results, fmt.Sprintf("(%s, %s)", left, right))
	}
	return strings.Join(results, "")
}

func main() {
	fmt.Println(PartList([]string{"I", "wish", "I", "hadn't", "come"}) == "(I, wish I hadn't come)(I wish, I hadn't come)(I wish I, hadn't come)(I wish I hadn't, come)")
}
