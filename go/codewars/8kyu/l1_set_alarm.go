package main

import "fmt"

func SetAlarm(employed, vacation bool) bool {
	return employed && !vacation
}

func main() {
	fmt.Println(SetAlarm(true, true) == false)
}
