package com.study.leetcode;

import java.util.Arrays;

/**
 * @author Tiger
 * @date 2020-05-19
 * @see com.study.leetcode
 **/
public class Question16 {
    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * <p>
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        flag:
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int temp = nums[i] + nums[l] + nums[r];
                if (Math.abs(temp - target) < Math.abs(ans - target)) {
                    ans = temp;
                }
                if (temp > target) {
                    r--;
                } else if (temp < target) {
                    l++;
                } else {
                    break flag;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0};
        System.out.println(threeSumClosest(nums, -100));
    }
}
