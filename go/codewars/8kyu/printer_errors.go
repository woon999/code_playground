package main

import "fmt"

func PrinterError(s string) string {
	var errors int
	for _, v := range s {
		if v > 'm' {
			errors++
		}
	}
	return fmt.Sprintf("%d/%d", errors, len(s))
}

func main() {
	fmt.Println(PrinterError("aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbmmmmmmmmmmmmmmmmmmmxyz") == "3/56")
	fmt.Println(PrinterError("kkkwwwaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbmmmmmmmmmmmmmmmmmmmxyz") == "6/60")
}
