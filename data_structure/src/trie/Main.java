package trie;

// ref : https://www.baeldung.com/trie-java
public class Main {
	public static void main(String[] args) {
		String[] words = {"apple", "app", "boat"};

		Trie trie = new Trie();
		for(int i=0; i<words.length; i++){
			trie.insert(words[i]);
		}

		System.out.println("\"apple\" contains\" : " +trie.contains("apple"));
		System.out.println("\"app\" contains\" : " +trie.contains("app"));
		System.out.println("\"boat\" contains\" : " +trie.contains("boat"));

		System.out.println("---------");
		trie.delete("boat");
		System.out.println("\"boat\" contains : " +trie.contains("boat"));
	}
}
