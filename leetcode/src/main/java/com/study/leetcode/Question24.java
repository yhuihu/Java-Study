package com.study.leetcode;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * @date 2022/9/8 23:27
 */
public class Question24 {

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // 第二个结点
        ListNode next = head.next;

        // 第一个结点的下一个结点定义
        head.next = swapPairs(next.next);

        // 第二个结点的下一个结点指向上一个结点
        next.next = head;


        return next;
    }

}
