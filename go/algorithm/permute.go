package main

import "fmt"

// 46.全排列
//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯 👍 1752 👎 0
func main() {
	i := permute([]int{1, 2, 3})
	fmt.Println(i)
}

func permute(nums []int) [][]int {
	var ans [][]int
	deepFindPermute(nums, &ans, []int{})
	return ans
}

func deepFindPermute(nums []int, ans *[][]int, tmpArr []int) {

	if len(tmpArr) == len(nums) {
		slice := make([]int, len(tmpArr))
		copy(slice, tmpArr)
		*ans = append(*ans, slice)
		return
	}

	for index := 0; index < len(nums); index++ {
		if verifyNums(tmpArr, nums[index]) {
			continue
		}
		tmpArr = append(tmpArr, nums[index])
		deepFindPermute(nums, ans, tmpArr)
		// 回溯
		tmpArr = tmpArr[:len(tmpArr)-1]
	}
}

// 判断是否已经包含数字
func verifyNums(array []int, target int) bool {
	for i := 0; i < len(array); i++ {
		if array[i] == target {
			return true
		}
	}
	return false
}
