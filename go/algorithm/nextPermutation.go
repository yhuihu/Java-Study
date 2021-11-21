package main

/**
leetcode 31题 下一个排列
实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-permutation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
//
//  nextPermutation
//  @Description: 下一个排列
//  @param nums 数组
//
func nextPermutation(nums []int) {
	n := len(nums)

	// 1.从后往前遍历，找到第一个n-1比n大的位置
	i := n - 2
	for i >= 0 && nums[i] >= nums[i+1] {
		i--
	}

	// 2.找到第一个顺序对后，从后往前找到第一个比nums[i]小的数字，交换这两个数字
	if i >= 0 {
		j := n - 1
		for j >= 0 && nums[i] >= nums[j] {
			j--
		}
		nums[i], nums[j] = nums[j], nums[i]
	}

	// 由于第一步中从后往前找了一遍升序序列，因此从nums[i]开始，肯定是降序的，因此，要将nums[i]后的顺序全部倒过来
	reverse(nums[i+1:])
}

func reverse(a []int) {
	for i, n := 0, len(a); i < n/2; i++ {
		a[i], a[n-1-i] = a[n-1-i], a[i]
	}
}
