package main

import "fmt"

// ListNode 19.
//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1], n = 1
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1,2], n = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中结点的数目为 sz
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
//
//
//
// 进阶：你能尝试使用一趟扫描实现吗？
// Related Topics 链表 双指针 👍 1772 👎 0
type ListNode struct {
	Val  int
	Next *ListNode
}

func main() {
	head := &ListNode{}
	head.Val = 1
	sec := &ListNode{}
	sec.Val = 2
	head.Next = sec
	third := &ListNode{}
	third.Val = 3
	head.Next.Next = third

	four := &ListNode{}
	four.Val = 4
	head.Next.Next.Next = four

	five := &ListNode{}
	five.Val = 5
	head.Next.Next.Next.Next = five

	end := removeNthFromEnd(head, 1)
	fmt.Println(end)
}
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	fast := head
	slow := head
	// 双指针法，让快指针多走n步
	for ; n > 0; n-- {
		fast = fast.Next
	}
	// 走到头了的情况，例如[1],1
	if fast == nil {
		return head.Next
	}
	for fast != nil && fast.Next != nil {
		slow = slow.Next
		fast = fast.Next
	}
	slow.Next = slow.Next.Next
	return head
}
