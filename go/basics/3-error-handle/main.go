package main

import "fmt"

func process() {
	defer func() {
		if err := recover(); err != nil {
			fmt.Println("Error:", err)
		}
	}()

	fmt.Println("processing...")
	panic("panic!!!!!")
	fmt.Println("NOT CALLED!")
}

func main() {
	fmt.Println("start program")
	process()
	fmt.Println("end program")
}
