package main

//24. 两两交换链表中的节点
//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//示例 2：
//
//输入：head = []
//输出：[]
//示例 3：
//
//输入：head = [1]
//输出：[1]

type ListNode struct {
	Val  int
	Next *ListNode
}

func swapPairs(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	tmpNode := head
	for tmpNode != nil {
		// 结点2
		next := tmpNode.Next
		// 结点1的下一个结点指向结点2的下一个结点
		head.Next = next.Next
		// 结点2的下一个结点为结点1
		next.Next = head
	}
	return head
}

func main() {

}
