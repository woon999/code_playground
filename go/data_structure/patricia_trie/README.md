# Patricia Trie (or Radix Trie)
- Patricia "Practical Algorithm to Retrieve Information Coded in Alphanumeric"의 약자
- Patricia Trie는 기본 Trie 구조의 확장 버전으로, 공간 효율성을 개선하기 위한 트리다.

기본적인 Trie에서는 각 노드가 문자열의 한 문자를 저장하지만, Patricia Trie에서는 노드가 문자열의 일부분 또는 전체를 저장할 수 있다. 
이로 인해 중간 노드의 수가 크게 감소하며, 메모리 사용량도 줄어든다.

## Patricia Trie의 노드 구조 
기본 Trie 구조는 다음과 같다.
- insert 구현이 쉽다. 하지만 메모리 효율성이 떨어진다.
```go
type TrieNode struct {
	children map[rune]*TrieNode
	isEnd    bool
}

type Trie struct {
	root *TrieNode
}
```

Patricia Trie는 한 노드에 문자열을 저장할 수 있으므로, 다음과 같이 구현할 수 있다.
- insert 구현이 까다롭지만 메모리 효율성이 높다
```go
type PatriciaNode struct {
    prefix   string
    children map[rune]*PatriciaNode
    isEnd    bool
}

type PatriciaTrie struct {
    root *PatriciaNode
}
```