package main

import ".."

type myTreeNode struct {
	*tree.Node // 内嵌
}

func (treeNode *myTreeNode) postOrder() {
	if treeNode == nil || treeNode.Node == nil {
		return
	}
	nodeLeft := myTreeNode{treeNode.Left}
	nodeLeft.postOrder()

	nodeRight := myTreeNode{treeNode.Right}
	nodeRight.postOrder()

	treeNode.PrintValue()

}

func main() {
	var root tree.Node
	//     3
	//  0    5
	//     2 4
	root = tree.Node{Value: 3}
	root.Left = &tree.Node{}
	root.Right = &tree.Node{Value: 5}

	root.Right.Left = new(tree.Node)

	root.Left.Right = tree.CreateNode(2)

	root.Right.Left.SetValue(4)

	// 中序遍历
	root.TraverseTraverse()

	// 后续遍历
	node := myTreeNode{&root}
	node.postOrder()

}
