package main

import "fmt"

// 插入排序golang实现
func sort(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}
	// 从前往后走
	for i := 1; i < len(nums); i++ {
		// 取index为i的数字，作为基准值
		back := nums[i]
		// 遍历index为i之前的数字
		j := i - 1
		// 这一个循环用于遍历index之前的数组的值
		// 如果前面的值比当前Index的值大，那么就需要将前面的值往后移一下
		// 直到前面的数字比Index的值小
		for j >= 0 && nums[j] > back {
			nums[j+1] = nums[j]
			j--
		}
		nums[j+1] = back
	}
	return nums
}

func main() {
	var nums []int
	nums = append(nums, 4)
	nums = append(nums, 1)
	nums = append(nums, 3)
	nums = append(nums, 7)
	nums = append(nums, 2)
	nums = append(nums, 4)
	nums = append(nums, 3)
	fmt.Println(sort(nums))
}
