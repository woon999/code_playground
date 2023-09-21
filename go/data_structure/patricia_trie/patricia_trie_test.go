package patricia_trie

import "strings"

type PatriciaNode struct {
	prefix   string
	children map[rune]*PatriciaNode
	isEnd    bool
}

type PatriciaTrie struct {
	root *PatriciaNode
}

func NewPatriciaNode(prefix string) *PatriciaNode {
	return &PatriciaNode{
		prefix:   prefix,
		children: make(map[rune]*PatriciaNode),
	}
}

func NewPatriciaTrie() *PatriciaTrie {
	return &PatriciaTrie{root: NewPatriciaNode("")}
}

// Insert
//	1. 공통 접두어 찾기: 현재 노드의 문자열(prefix)와 입력된 단어의 공통 접두어를 찾는다.
// 	1-1. 완전 일치: 현재 노드의 isEnd를 true로 설정하고 종료한다.
// 	1-2. 부분 일치: 현재 노드의 문자열(prefix)을 공통 접두어로 설정하고, 입력된 단어에서 공통 접두어를 제거한다.
// 	1-3. 노드 분할: 입력된 단어와 현재 노드의 문자열이 부분적으로 일치하면 노드를 분할한다.
// (1-2, 1-3) 2. 자식 노드 추가: 공통 접두어가 없는 경우, 해당 문자를 키로 가지는 자식 노드를 추가한다.
func (pt *PatriciaTrie) Insert(word string) {
	node := pt.root
	var index int
	for node != nil {
		// 1. 공통 접두어 찾기
		index = commonPrefix(node.prefix, word)

		// 1-1. 완벽히 일치하면 해당 노드에서 종료 (ex. node.prefix = "abc", word = "abc")
		if index == len(node.prefix) && index == len(word) {
			node.isEnd = true
			return
		}

		// 1-2. node의 prefix가 word의 접두어일 경우 (ex. node.prefix = "abc", word = "abcd")
		if index == len(node.prefix) {
			word = word[index:]
			child, exists := node.children[rune(word[0])]
			if !exists {
				node.children[rune(word[0])] = NewPatriciaNode(word) // 2. 자식 노드 추가
				return
			}
			node = child
		} else { // 1-3. 노드 분할
			newNode := NewPatriciaNode(word[:index])
			newNode.children[rune(node.prefix[index])] = node
			node.prefix = node.prefix[index:]
			if pt.root == node {
				pt.root = newNode
			} else {
				node = newNode
			}
			node.children[rune(word[index])] = NewPatriciaNode(word[index:]) // 2. 자식 노드 추가
			return
		}
	}
}

func commonPrefix(a, b string) int {
	i := 0
	for i < len(a) && i < len(b) && a[i] == b[i] {
		i++
	}
	return i
}

func (pt *PatriciaTrie) Search(word string) bool {
	node := pt.root
	for len(word) > 0 {
		if len(node.prefix) > 0 && !strings.HasPrefix(word, node.prefix) {
			return false
		}

		word = word[len(node.prefix):]

		if len(word) == 0 {
			return node.isEnd
		}

		node = node.children[rune(word[0])]
		if node == nil {
			return false
		}
	}
	return node.isEnd
}

// Rest of the functions (Print etc.) can be adapted from your Trie code.
