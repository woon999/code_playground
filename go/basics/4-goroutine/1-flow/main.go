package main

import (
	"fmt"
	"sync"
)

var wg sync.WaitGroup

func printNumbers() {
	defer wg.Done()
	for i := 0; i < 10; i++ {
		fmt.Println(i)
	}
}

func printLetters() {
	defer wg.Done()
	for i := 'a'; i < 'a'+10; i++ {
		fmt.Printf("%c\n", i)
	}
}

func main() {
	list := []func(){printNumbers, printLetters}
	for _, it := range list {
		wg.Add(1)
		go it()
	}

	wg.Wait()
	fmt.Println("done")
}
