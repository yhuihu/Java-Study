package main

import (
	"fmt"
	"strconv"
)

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 哈希表 字符串 回溯 👍 1686 👎 0

var strs = []string{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}

func main() {
	combinations := letterCombinations("23")
	fmt.Println(combinations)
}

//leetcode submit region begin(Prohibit modification and deletion)
func letterCombinations(digits string) []string {
	results := []string{}
	if len(digits) == 0 {
		return results
	}
	deepFind(digits, 0, &results, "")
	return results
}

func deepFind(digits string, index int, results *[]string, tmpStr string) {
	if len(tmpStr) == len(digits) {
		*results = append(*results, tmpStr)
		return
	}
	tmpIndexValue, _ := strconv.Atoi(string(digits[index]))
	englishValues := strs[tmpIndexValue]
	for _, indexValue := range englishValues {
		deepFind(digits, index+1, results, tmpStr+string(indexValue))
	}
}

//leetcode submit region end(Prohibit modification and deletion)
