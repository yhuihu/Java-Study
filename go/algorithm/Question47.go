package main

import (
	"fmt"
	"reflect"
)

// //给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
// //
// //
// //
// // 示例 1：
// //
// //
// //输入：nums = [1,1,2]
// //输出：
// //[[1,1,2],
// // [1,2,1],
// // [2,1,1]]
// //
// //
// // 示例 2：
// //
// //
// //输入：nums = [1,2,3]
// //输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// //
// //
// //
// //
// // 提示：
// //
// //
// // 1 <= nums.length <= 8
// // -10 <= nums[i] <= 10
// //
// // Related Topics 数组 回溯 👍 1060 👎 0
func main() {
	nums := []int{1, 1, 2}
	unique := permuteUnique(nums)
	fmt.Println(unique)
}

func permuteUnique(nums []int) [][]int {
	var answer [][]int
	// 用于记录是否走过这个位置
	visited := make([]bool, len(nums))
	permuteUniqueDfs(&answer, nums, &visited, &[]int{}, 0)
	return answer
}

func permuteUniqueDfs(answer *[][]int, nums []int, visited *[]bool, res *[]int, length int) {
	if length == len(nums) {
		slice := make([]int, length)
		// 深克隆一份，因为res使用的是指针，避免后续流程的影响
		copy(slice, *res)
		tmpFlag := false
		for i := 0; i < len(*answer); i++ {
			if reflect.DeepEqual(slice, (*answer)[i]) {
				tmpFlag = true
				break
			}
		}
		if !tmpFlag {
			*answer = append(*answer, slice)
		}
		return
	}
	for i := 0; i < len(nums); i++ {
		if !(*visited)[i] {
			// 添加到答案里
			*res = append(*res, nums[i])
			// 标记为已经走过了
			(*visited)[i] = true
			permuteUniqueDfs(answer, nums, visited, res, length+1)
			*res = (*res)[:(len(*res) - 1)]
			// 回溯
			(*visited)[i] = false
		}
	}
}
