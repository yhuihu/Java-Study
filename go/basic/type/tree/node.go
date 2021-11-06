package tree

import "fmt"

type Node struct {
	Value int
	Left  *Node
	Right *Node
}

func (node Node) PrintValue() {
	fmt.Println(node.Value)
}

func (node *Node) SetValue(value int) {
	node.Value = value
}

func CreateNode(value int) *Node {
	return &Node{Value: value}
}

func (node Node) Print() {
	fmt.Print(node.Value, " ")
}

func (node *Node) TraverseTraverse() {
	node.TraverseFunc(func(n *Node) {
		n.Print()
	})
	fmt.Println()
}

func (node *Node) TraverseFunc(f func(node *Node)) {
	if node == nil {
		return
	}

	node.Left.TraverseFunc(f)
	f(node)
	node.Right.TraverseFunc(f)
}

//func main() {
//	var root TreeNode
//	root = TreeNode{Value: 3}
//	fmt.Println(root)
//	root.Value = 4
//	fmt.Println(root)
//	root.Left = new(TreeNode)
//	root.Right = &TreeNode{5, nil, nil}
//	fmt.Println(root, root.Left, root.Right)
//
//	root.SetValue(100)
//	root.PrintValue()
//}
