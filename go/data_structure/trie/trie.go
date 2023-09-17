package trie

import (
	"fmt"
	"strings"
)

type TrieNode struct {
	children map[rune]*TrieNode
	isEnd    bool
}

type Trie struct {
	root *TrieNode
}

func NewNode() *TrieNode {
	return &TrieNode{children: make(map[rune]*TrieNode)}
}

func NewTrie() *Trie {
	return &Trie{root: NewNode()}
}

func (t *Trie) Insert(word string) {
	node := t.root
	for _, ch := range word {
		if _, exists := node.children[ch]; !exists {
			node.children[ch] = NewNode()
		}
		node = node.children[ch]
	}
	node.isEnd = true
}

func (t *Trie) Search(word string) bool {
	node := t.root
	for _, ch := range word {
		if nextNode, exists := node.children[ch]; exists {
			node = nextNode
		} else {
			return false
		}
	}
	return node.isEnd
}

func (t *Trie) Print() {
	lines := make([]string, t.depth(t.root)+2) // +1 for the virtual root
	t.printVisual(t.root, 1, 0, &lines)

	for _, line := range lines {
		fmt.Println(line)
	}
}

func (t *Trie) depth(node *TrieNode) int {
	if node == nil {
		return 0
	}
	maxDepth := 0
	for _, child := range node.children {
		maxDepth = max(maxDepth, t.depth(child))
	}
	return maxDepth + 1
}

func (t *Trie) printVisual(node *TrieNode, level int, position int, lines *[]string) int {
	if node == nil {
		return position
	}

	// Print the node
	if (*lines)[level] == "" {
		(*lines)[level] = strings.Repeat(" ", position) + "|"
	} else {
		(*lines)[level] += strings.Repeat(" ", position-len((*lines)[level])) + "|"
	}

	position++

	// Print the children
	for ch, child := range node.children {
		chStr := string(ch)
		if child.isEnd {
			chStr += "*"
		}

		nextPos := t.printVisual(child, level+1, position, lines)
		(*lines)[level] += strings.Repeat("-", nextPos-position) + chStr
		position = nextPos + 1
	}

	return position
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

//func (t *Trie) Print() {
//	t.printVisual(t.root, 0, "")
//}
//
//func (t *Trie) printVisual(node *TrieNode, level int, prefix string) {
//	if node == nil {
//		return
//	}
//
//	// 현재 노드의 모든 자식을 출력합니다.
//	isFirstChild := true
//	for ch, child := range node.children {
//		// 첫 번째 자식이 아닌 경우 '\' 문자를 추가하고, 나머지 자식에 대해서는 '/' 문자를 추가합니다.
//		if isFirstChild {
//			fmt.Println(prefix + string(ch))
//			isFirstChild = false
//		} else {
//			fmt.Println(prefix[:len(prefix)-1] + "/" + string(ch))
//		}
//
//		// 다음 레벨의 프리픽스를 계산합니다.
//		nextPrefix := prefix + "|"
//		if len(node.children) > 1 && !isFirstChild {
//			nextPrefix = prefix + " "
//		}
//
//		t.printVisual(child, level+1, nextPrefix+" ")
//	}
//}

//func (t *Trie) String() string {
//	var result string
//	t.printTrie(t.root, "", &result)
//	return result
//}
//
//func (t *Trie) printTrie(node *TrieNode, current string, result *string) {
//	if node == nil {
//		return
//	}
//
//	if node.isEnd {
//		*result += current + "\n"
//	}
//
//	for ch, child := range node.children {
//		t.printTrie(child, current+string(ch), result)
//	}
//}
