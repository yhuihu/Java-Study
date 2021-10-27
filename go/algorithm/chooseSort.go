package main

import "fmt"

// 选择排序golang实现
func chooseSort(nums []int) []int {
	for i := 0; i < len(nums)-1; i++ {
		for j := i + 1; j < len(nums); j++ {
			// 如果前面的数字比后面的数字大，那么调整一下顺序
			if nums[i] > nums[j] {
				// 进行数值交换，通过异或运算避免重新开启一个空间保存临时值
				nums[j] = nums[i] ^ nums[j]
				nums[i] = nums[i] ^ nums[j]
				nums[j] = nums[i] ^ nums[j]
			}
		}
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
	fmt.Println(chooseSort(nums))
}
