package main

// https://www.codewars.com/kata/5556282156230d0e5e000089/train/go

import (
	"fmt"
	"strings"
)

func DNAtoRNA(dna string) string {
	return strings.ReplaceAll(dna, "T", "U")
}

func main() {
	fmt.Println(DNAtoRNA("TTTT") == "UUUU")
	fmt.Println(DNAtoRNA("GCAT") == "GCAU")
	fmt.Println(DNAtoRNA("GACCGCCGCC") == "GACCGCCGCC")
}
