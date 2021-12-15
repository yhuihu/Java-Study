package main

import "fmt"

/**
206. 反转链表
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
*/

type ListNode struct {
	Val  int
	Next *ListNode
}

func main() {
	var nodeList *ListNode = new(ListNode)
	nodeList.Val = 1

	var nextNode *ListNode = new(ListNode)
	nextNode.Val = 2

	nodeList.Next = nextNode

	var nextNextNode *ListNode = new(ListNode)
	nextNextNode.Val = 3

	nextNode.Next = nextNextNode

	list := reverseList(nodeList)
	fmt.Println(list)
}

// 循环版本
func reverseList(head *ListNode) *ListNode {
	//1-》2-》3
	var preNode *ListNode
	// 索引
	current := head
	for current != nil {
		// 获取下一个节点
		next := current.Next
		// 前一个节点指向自己的前一个节点，头部为nil
		current.Next = preNode
		// 记录下一个节点对应的前一个节点
		preNode = current
		// 开始处理下一个节点
		current = next
	}
	// 最终preNode为最后一个节点3
	// 3的next为2
	return preNode
}

// 递归版本
func reverseListInner(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	// 返回后面的链
	newHead := reverseListInner(head.Next)
	// 后一个的下一个是上一节点
	newHead.Next = head
	// 等于重构链表
	head.Next = nil
	return newHead
}
