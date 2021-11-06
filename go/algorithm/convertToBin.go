package main

import (
	"fmt"
	"strconv"
)

func convertToBin(number int) string {
	str := ""
	if number == 0 {
		return "0"
	}
	for ; number > 0; number /= 2 {
		lsb := number % 2
		str = strconv.Itoa(lsb) + str
	}
	return str
}

func main() {
	fmt.Println(convertToBin(2))
	fmt.Println(convertToBin(3))
	fmt.Println(convertToBin(4))
	fmt.Println(convertToBin(5))
	fmt.Println(convertToBin(6))
	fmt.Println(convertToBin(7))
	fmt.Println(convertToBin(8))
}
