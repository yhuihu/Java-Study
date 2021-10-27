package main

import "fmt"

func checkLongestStr(str string) int {
	// 用于记录某个字母最后出现的位置
	tmpMap := make(map[rune]int)
	// 记录开始计算的位置
	start := 0
	maxLength := 0
	for i, tmpValue := range str {
		// 如果某一个单词在index之后才出现，那么他就是重复出现了
		if lastIndex, ok := tmpMap[tmpValue]; ok && lastIndex >= start {
			start = lastIndex + 1
		}
		if i-start+1 > maxLength {
			maxLength = i - start + 1
		}
		tmpMap[tmpValue] = i
	}
	return maxLength
}

func main() {
	fmt.Println(checkLongestStr(""))
	fmt.Println(checkLongestStr("a"))
	fmt.Println(checkLongestStr("aaa"))
	fmt.Println(checkLongestStr("aba"))
	fmt.Println(checkLongestStr("abc"))
	fmt.Println(checkLongestStr("abccc"))
}
