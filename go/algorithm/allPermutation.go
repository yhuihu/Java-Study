package main

// 剑指 Offer II 083. 没有重复元素集合的全排列
// 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
// 输入：nums = [1,2,3]
// 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
func main() {
	nums := []int{1, 0}
	permute(nums)
}

func permute(nums []int) [][]int {
	var answer [][]int
	visited := make([]bool, len(nums))
	dfs(&answer, nums, &visited, &[]int{}, 0)
	return answer
}

func dfs(answer *[][]int, nums []int, visited *[]bool, res *[]int, length int) {
	if length == len(nums) {
		slice := make([]int, length)
		// 深克隆一份，因为res使用的是指针，避免后续流程的影响
		copy(slice, *res)
		*answer = append(*answer, slice)
		return
	}
	for i := 0; i < len(nums); i++ {
		if !(*visited)[i] {
			// 添加到答案里
			*res = append(*res, nums[i])
			// 标记为已经走过了
			(*visited)[i] = true
			dfs(answer, nums, visited, res, length+1)
			*res = (*res)[:(len(*res) - 1)]
			// 回溯
			(*visited)[i] = false
		}
	}
}
