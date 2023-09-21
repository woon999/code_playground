package patricia_trie

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestTrieInsertAndSearch(t *testing.T) {
	trie := NewPatriciaTrie()

	words := []string{"apple", "appl", "app", "app", "appled", "b", "banana", "ban", "bat"}

	for _, word := range words {
		trie.Insert(word)
	}

	// trie.Print()
	// depth=0 -
	//	 depth=1 --app*
	//		depth=2 --app-l*
	//			depth=3 --app-l-e*
	//				depth=4 --app-l-e-d*
	//	 depth=1 --b*
	//		depth=2 --b-a
	//			depth=3 --b-a-n*
	//				depth=4 --b-a-n-ana*
	//			depth=3 --b-a-t*

	for _, word := range words {
		assert.True(t, trie.Search(word), "Expected to find word: %s", word)
	}

	// Search for words that were not inserted
	assert.False(t, trie.Search("batwoman"))
	assert.False(t, trie.Search("appetite"))
	assert.False(t, trie.Search("band"))

	// Test for partial words
	assert.True(t, trie.Search("app"))
	assert.True(t, trie.Search("ban"))
	assert.False(t, trie.Search("batma"))
}

func TestPatriciaTrieSearchOnEmptyTrie(t *testing.T) {
	trie := NewPatriciaTrie()

	assert.False(t, trie.Search("apple"))
}

func TestPatriciaTrieSearchForEmptyString(t *testing.T) {
	trie := NewPatriciaTrie()

	assert.False(t, trie.Search(""), "Empty Trie should not have an empty string")
	trie.Insert("")
	assert.True(t, trie.Search(""), "Trie should contain the empty string after insertion")
}
