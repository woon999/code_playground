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
