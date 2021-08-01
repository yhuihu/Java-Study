package com.study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yhhu
 * @date 2021/6/21
 * @description 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * eg:
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[]
 */
public class Question18 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        // -2 -1 0 1 2
        System.out.println(solution.fourSum(nums, 0));
    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        // 1.先将数组进行排序
        Arrays.sort(nums);
        // 2.四个数字相加
        for (int i = 0; i < nums.length - 3; i++) {
            // 这里定义三个数
            int leftIndex = i + 1;
            int midIndex = leftIndex + 1;
            int rightIndex = midIndex + 1;
            int total;
            total = nums[i] + nums[leftIndex] + nums[midIndex] + nums[rightIndex];
            // 已经排过序了，如果最小都比target大，直接跳过
            if (total > target) {
                break;
            }
            // 如果是相同数字，直接跳过，减少重复循环次数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (leftIndex < midIndex) {
                if (leftIndex > nums.length - 2) {
                    break;
                }
                if (leftIndex > 1 && nums[leftIndex] == nums[leftIndex - 1]) {
                    leftIndex++;
                    continue;
                }
                midIndex = leftIndex + 1;
                rightIndex = midIndex + 1;
                while (midIndex < rightIndex) {
                    if (midIndex > 2 && nums[midIndex] == nums[midIndex - 1]) {
                        midIndex++;
                        continue;
                    }
                    rightIndex = midIndex + 1;
                    while (rightIndex < nums.length) {
                        if (rightIndex > 3 && nums[rightIndex] == nums[rightIndex - 1]) {
                            rightIndex++;
                            continue;
                        }
                        total = nums[i] + nums[leftIndex] + nums[midIndex] + nums[rightIndex];
                        if (total > target) {
                            break;
                        }
                        if (total == target) {
                            List<Integer> tmp = Arrays.asList(nums[i], nums[leftIndex], nums[midIndex], nums[rightIndex]);
                            if (!answer.contains(tmp))
                                answer.add(tmp);
                        }
                        rightIndex++;
                    }
                    midIndex++;
                }
                leftIndex++;
            }
        }
        return answer;
    }
}
