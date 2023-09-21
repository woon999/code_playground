package patricia_trie

import (
	"fmt"
	"strings"
)

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
//  1. 공통 접두어 찾기: 현재 노드의 문자열(prefix)와 입력된 단어의 공통 접두어를 찾는다.
//     1-1. 완전 일치: 현재 노드의 isEnd를 true로 설정하고 종료한다.
//     1-2. 부분 일치: 현재 노드의 문자열(prefix)을 공통 접두어로 설정하고, 입력된 단어에서 공통 접두어를 제거한다.
//     1-3. 노드 분할: 입력된 단어와 현재 노드의 문자열이 부분적으로 일치하면 노드를 분할한다.
//
// (1-2, 1-3) 2. 자식 노드 추가: 공통 접두어가 없는 경우, 해당 문자를 키로 가지는 자식 노드를 추가하고 종료한다.
func (pt *PatriciaTrie) Insert(word string) {
	node := pt.root
	prev := node
	var index int

	for node != nil {
		// 1. 공통 접두어 찾기
		index = commonPrefix(node.prefix, word)

		// 1-1. 완벽히 일치하면 해당 노드에서 종료 (ex. node.prefix = "abc", word = "abc")
		if index == len(node.prefix) && index == len(word) {
			//fmt.Println("1-1: 완벽히 일치하면 해당 노드에서 종료 prefix=" + node.prefix + ", word=" + word)
			return
		}

		// 1-2. node의 prefix가 word의 접두어일 경우 (ex. node.prefix = "abc", word = "abcd")
		if index == len(node.prefix) {
			//fmt.Println("1-2: node의 prefix가 word의 접두어일 경우 prefix=" + node.prefix + ", word=" + word)
			word = word[index:]
			child, exists := node.children[rune(word[0])]
			if !exists {
				node.children[rune(word[0])] = NewPatriciaNode(word) // 2. 자식 노드 추가
				node.children[rune(word[0])].isEnd = true
				return
			}
			prev = node
			node = child
		} else if index > 0 { // 1-3. 부분 겹치는 경우 노드 분할 (ex. node.prefix = "apple", word = "appl")
			//fmt.Println("1-3: 노드 분할 prefix=" + node.prefix + ", word=" + word)
			splitNode := NewPatriciaNode(node.prefix[:index]) // "a" 노드 생성

			// 기존 노드 prefix 수정
			node.prefix = node.prefix[index:]
			splitNode.children[rune(node.prefix[0])] = node
			splitNode.children[rune(node.prefix[0])].isEnd = true

			if len(splitNode.prefix) < len(word) { // ex. prefix = "an",  word ="at" -> "t" 노드도 생성
				fmt.Println("word=" + word[index:])
				splitNode.children[rune(word[index])] = NewPatriciaNode(word[index:])
				splitNode.children[rune(word[index])].isEnd = true
			} else if len(splitNode.prefix) == len(word) {
				splitNode.isEnd = true
			}

			// 기존 노드의 자식 노드를 node -> splitNode로 변경
			prev.children[rune(splitNode.prefix[0])] = splitNode
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

// Search
//  1. 공통 접두어 찾기: 현재 노드의 문자열(prefix)와 입력된 단어의 공통 접두어를 찾는다.
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

func (pt *PatriciaTrie) Print() {
	fmt.Println(pt.printNode(pt.root, "", 0))
}

func (pt *PatriciaTrie) printNode(node *PatriciaNode, prefix string, depth int) string {
	if node == nil {
		return "empty"
	}

	output := "depth=" + fmt.Sprint(depth) + " "
	newPrefix := prefix + "-" + node.prefix

	if node.isEnd {
		output += newPrefix + "*\n" // *는 단어의 끝을 나타냄
	} else {
		output += newPrefix + "\n"
	}

	for _, child := range node.children {
		output += pt.printNode(child, newPrefix, depth+1)
	}

	return output
}
