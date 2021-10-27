package main

import "fmt"

// 希尔排序golang实现
func sellSort(nums []int) []int {
	length := len(nums)
	tmpIndex := len(nums) / 2
	markValue := "="
	for tmpIndex > 0 {
		// 从1/2位置开始往后走，那么每个区间的值就是i-tmpIndex
		for i := tmpIndex; i < length; i++ {
			preIndex := i - tmpIndex
			fmt.Printf("%s,%v\n", markValue, nums)
			markValue += "="
			// 这里注意一定要有一个临时变量保存临时值，如果下面代码直接用nums[i]，到最后赋值时因为内存已经修改，会造成前面的值全部被覆盖
			tmpValue := nums[i]
			for preIndex >= 0 && tmpValue < nums[preIndex] {
				nums[preIndex+tmpIndex] = nums[preIndex]
				preIndex -= tmpIndex
			}
			nums[preIndex+tmpIndex] = tmpValue
		}
		tmpIndex = tmpIndex / 2
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
	fmt.Println(sellSort(nums))
}
