package com.study.leetcode;

/**
 * @author yhhu
 * @date 2021/7/12
 * @description 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question11 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxArea(new int[]{2,1});
    }
    static class Solution {
        public int maxArea(int[] height) {
            int res = 0, i = 0, j = height.length - 1;
            while (i < j) {
                res = height[i] < height[j] ?
                        Math.max(res, (j - i) * (height[i++])) :
                        Math.max(res, (j - i) * (height[j--]));
            }
            return res;
        }
    }
}
