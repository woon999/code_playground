package trie

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestTrieInsertAndSearch(t *testing.T) {
	trie := NewTrie()

	words := []string{"apple", "app", "banana", "ban", "bat", "batman"}

	for _, word := range words {
		trie.Insert(word)
	}

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

func TestTrieSearchOnEmptyTrie(t *testing.T) {
	trie := NewTrie()

	assert.False(t, trie.Search("apple"))
}

func TestTrieSearchForEmptyString(t *testing.T) {
	trie := NewTrie()

	assert.False(t, trie.Search(""), "Empty Trie should not have an empty string")
	trie.Insert("")
	assert.True(t, trie.Search(""), "Trie should contain the empty string after insertion")
}

func TestDisplay(t *testing.T) {
	tr := NewTrie()
	tr.Insert("the")
	tr.Insert("their")
	tr.Insert("ans")
	tr.Insert("answer")
	tr.Insert("bye")
	tr.Insert("apple")
	tr.Insert("app")
	tr.Insert("ape")

	tr.Print()
}

/*
|---------t---------------------a-----b
 |-------h |---------n---------p |---y
  |-----e*  |-------s*|-----p*-e* |-e*
   |---i     |-----w   |---l |     |
    |-r*      |---e     |-e*
     |         |-r*      |
                |
*/
