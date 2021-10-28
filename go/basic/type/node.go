package main

import "fmt"

type treeNode struct {
	value int
	left  *treeNode
	right *treeNode
}

func (node treeNode) printValue() {
	fmt.Println(node.value)
}

func (node *treeNode) setValue(value int) {
	node.value = value
}

func main() {
	var root treeNode
	root = treeNode{value: 3}
	fmt.Println(root)
	root.value = 4
	fmt.Println(root)
	root.left = new(treeNode)
	root.right = &treeNode{5, nil, nil}
	fmt.Println(root, root.left, root.right)

	root.setValue(100)
	root.printValue()
}
