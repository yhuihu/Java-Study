package main

//34. 在排序数组中查找元素的第一个和最后一个位置
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
//如果数组中不存在目标值 target，返回 [-1, -1]。
//
//进阶：
//
//你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
var min = -1
var max = -1

func searchRange(nums []int, target int) []int {
	if len(nums) == 0 {
		return []int{min, max}
	}
	return []int{min, max}
}

// 中序遍历
func sliceFind(first int, end int, nums []int, target int, lower bool) int {
	for first <= end {
		midIndex := (first + end) / 2
		if (nums[midIndex] > target) || (lower && nums[midIndex] >= target) {

		}
	}
	return 0
}
