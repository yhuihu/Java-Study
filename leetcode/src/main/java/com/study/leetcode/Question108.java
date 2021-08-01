package com.study.leetcode;

/**
 * @author yhhu
 * @date 2021/6/20
 * @description
 */
public class Question108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        int position = nums.length / 2;
        System.out.println(nums[position]);
        return new TreeNode();

    }

    public static void main(String[] args) {
        Question108 question108 = new Question108();
        question108.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

