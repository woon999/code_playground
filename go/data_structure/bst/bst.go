package bst

import (
	. "data_structure/node"
)

// Lesser 인터페이스:
//
//	이 인터페이스는 단일 메서드 Less(other Lesser) bool로 정의
//	이 인터페이스의 목적은 이진 검색 트리에서 값을 비교하는 방법 제공
type Lesser interface {
	Less(other Lesser) bool
}

// Tree 트리 - root
type Tree struct {
	Root *TreeNode
}

// TreeNode 노드 - value, left, right
type TreeNode struct {
	Value Lesser

	Left  *TreeNode
	Right *TreeNode
}

func (t *TreeNode) GetChilds() []Node {
	var childs []Node
	if t.Left == nil {
		childs = append(childs, nil)
	} else {
		childs = append(childs, t.Left)
	}

	if t.Right == nil {
		childs = append(childs, nil)
	} else {
		childs = append(childs, t.Right)
	}
	return childs
}

func (t *TreeNode) GetValue() any {
	return t.Value
}

// 노드 추가하기
func (t *Tree) Add(value Lesser) *TreeNode {
	if t.Root == nil {
		t.Root = &TreeNode{
			Value: value,
		}
		return t.Root
	}

	return t.Root.add(value)
}

// t.Value.Less(value): t.Value가 value보다 작은 경우 value 오른쪽, 아니면 왼쪽
func (t *TreeNode) add(value Lesser) *TreeNode {
	if t.Value.Less(value) {
		if t.Right == nil {
			t.Right = &TreeNode{
				Value: value,
			}
			return t.Right
		}
		return t.Right.add(value)
	} else {
		if t.Left == nil {
			t.Left = &TreeNode{
				Value: value,
			}
			return t.Left
		}
		return t.Left.add(value)
	}
}

// 포함된 노드 찾기
func (t *Tree) Contains(value Lesser) bool {
	if t.Root == nil {
		return false
	}

	return t.Root.contains(value)
}

func (t *TreeNode) contains(value Lesser) bool {
	if equal(t.Value, value) {
		return true
	}
	if t.Value.Less(value) {
		// my < value
		if t.Right == nil {
			return false
		}
		return t.Right.contains(value)
	} else {
		// my >= value
		if value.Less(t.Value) {
			// value < my
			// my > value
			if t.Left == nil {
				return false
			}
			return t.Left.contains(value)
		} else {
			// my == value
			return true
		}
	}
}

func equal(a, b Lesser) bool {
	if a.Less(b) {
		// a < b
		return false
	}
	if b.Less(a) {
		// b < a
		return false
	}
	return true
}

// 노드 삭제하기
func (t *Tree) Remove(value Lesser) bool {
	if t.Root == nil {
		return false
	}

	var removed bool
	t.Root, removed = t.Root.remove(value)
	return removed
}

func (t *TreeNode) remove(value Lesser) (*TreeNode, bool) {
	if equal(t.Value, value) {
		if t.Left == nil && t.Right == nil {
			return nil, true
		}

		if t.Left == nil {
			return t.Right, true
		}
		if t.Right == nil {
			return t.Left, true
		}

		// Find left-most node
		leftMostNode := t.findAndRemoveLeftMostNode()
		leftMostNode.Right = t.Right
		return leftMostNode, true
	}

	var removed bool
	if t.Value.Less(value) {
		// value > t.Value
		if t.Right == nil {
			return t, false
		}
		t.Right, removed = t.Right.remove(value)
		return t, removed
	} else {
		// value < t.Value
		if t.Left == nil {
			return t, false
		}
		t.Left, removed = t.Left.remove(value)
		return t, removed
	}
}

func (t *TreeNode) findAndRemoveLeftMostNode() *TreeNode {
	if t.Left == nil {
		return nil
	}

	rightMost := t.Left
	parent := t

	for rightMost.Right != nil {
		parent = rightMost
		rightMost = rightMost.Right
	}

	if parent.Left == rightMost {
		parent.Left = nil
	} else {
		parent.Right = nil
	}
	return rightMost
}
