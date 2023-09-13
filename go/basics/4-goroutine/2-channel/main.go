package main

import (
	"fmt"
	"time"
)

func s1(ch chan string) {
	time.Sleep(2 * time.Second)
	ch <- "from server1"
}

func s2(ch chan string) {
	time.Sleep(1 * time.Second)
	ch <- "from server2"
}

func main() {
	output1 := make(chan string)
	output2 := make(chan string)

	go s1(output1)
	go s2(output2)

	select {
	case s1 := <-output1:
		fmt.Println(s1)
	case s2 := <-output2:
		fmt.Println(s2)
	}
}
