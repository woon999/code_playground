package trie;

// Trie ref : https://www.baeldung.com/trie-java
public class Main {
	public static void main(String[] args) {
		/**
		 * Trie Test
		 */
		System.out.println("------------  Trie Test ------------");
		String[] words = {"apple", "app", "boat"};

		Trie trie = new Trie();
		for (int i = 0; i < words.length; i++) {
			trie.insert(words[i]);
		}

		System.out.println("\"apple\" contains\" : " + trie.contains("apple"));
		System.out.println("\"app\" contains\" : " + trie.contains("app"));
		System.out.println("\"boat\" contains\" : " + trie.contains("boat"));

		System.out.println("----");
		trie.delete("boat");
		System.out.println("\"boat\" contains : " + trie.contains("boat"));

		/**
		 * Aho-Corasick Test
		 */
		System.out.println("------------ Aho-Corasick Test ------------");
		String parent = "cacachefcachy";
		String[] fruits = {"cache", "chef", "achy", "fca"};
		Trie ahoTrie = new Trie();

		for (int i = 0; i < fruits.length; i++) {
			ahoTrie.insert(fruits[i]);
		}

		System.out.println("\"cache\" contains\" : " + ahoTrie.contains("cache"));
		System.out.println("\"chef\" contains\" : " + ahoTrie.contains("chef"));
		System.out.println("\"fca\" contains\" : " + ahoTrie.contains("fca"));

		System.out.println("----");
		ahoTrie.delete("fca");
		System.out.println("\"fca\" contains\" : " +ahoTrie.contains("fca"));

		ahoTrie.computeFailFunc();
		int result = ahoTrie.ahoCorasick(parent);
		System.out.println("aho-Corasick matching count : " + result);

	}
}
