package com.study.leetcode;

/**
 * @author Tiger
 * @date 2020-05-19
 * @see com.study.leetcode
 **/
public class Question88 {
    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * <p>
     *  
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *  
     * <p>
     * 示例:
     * <p>
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * 输出: [1,2,2,3,5,6]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[k] = nums1[m--];
            } else {
                nums1[k] = nums2[n--];
            }
            k--;
        }
        while (n >= 0) {
            nums1[k--] = nums2[n--];
        }
        while (m >= 0) {
            nums1[k--] = nums1[m--];
        }
    }
}
