package main

import "fmt"

//整数数组 nums 按升序排列，数组中的值 互不相同 。
//
//在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
//
//给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
//
//
//
//示例 1：
//
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
//示例 2：
//
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1
//示例 3：
//
//输入：nums = [1], target = 0
//输出：-1
//
//
//提示：
//
//1 <= nums.length <= 5000
//-10^4 <= nums[i] <= 10^4
//nums 中的每个值都 独一无二
//题目数据保证 nums 在预先未知的某个下标上进行了旋转
//-10^4 <= target <= 10^4
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
func main() {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	i := search(nums, 0)
	fmt.Println(i)
}

// 本题的解法可以通过二分查找法来处理
// 可是有一个地方需要注意，因为涉及到有序数组的旋转，因此需要注意在二分时要判断一下当前的左右索引是否为递增序列上。
func search(nums []int, target int) int {
	length := len(nums)
	left := 0
	right := length - 1
	for left <= right {
		midIndex := (left + right) / 2

		// 判断是否已经找到了
		if nums[midIndex] == target {
			return midIndex
		}

		// 如果已经是正常的升序区间，直接通过二分处理就可以了
		if nums[left] <= nums[midIndex] {
			// 如果找不到，证明该移动了
			if nums[left] <= target && target < nums[midIndex] {
				right = midIndex - 1
			} else {
				left = midIndex + 1
			}
		} else {
			// 如果不是升序区间
			//
			if nums[midIndex] < target && target <= nums[right] {
				left = midIndex + 1
			} else {
				right = midIndex - 1
			}
		}
	}
	return -1
}
