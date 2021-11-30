package trie;

public class Trie {
	private TrieNode root;

	public Trie(){
		root = new TrieNode();
	}

	// 새로운 문자열 추가
	public void insert(String word){
		TrieNode curNode = this.root;
		System.out.println("## insert ");
		for(int i=0; i<word.length(); i++) {
			char key = word.charAt(i);
			curNode = curNode.getChildNodes()
				.computeIfAbsent(key, c -> new TrieNode());
			curNode.setContent(key);
			System.out.print(key+" - ");
		}
		System.out.println("is output");
		curNode.setOutput(true);
	}

	// 특정 단어가 있는지 확인
	public boolean contains(String word){
		TrieNode curNode = this.root;
		for(int i=0; i<word.length(); i++){
			char key = word.charAt(i);
			TrieNode node = curNode.getChildNodes().get(key);

			if(node == null){
				return false;
			}

			curNode = node;
		}
		return curNode.isOutput();
	}

	public void delete(String word){
		System.out.println("## delete ");
		if(delete(root, word, 0)){
			System.out.println(" delete success ");
		}

	}

	// 1. 해당 문자열은 자식 노드가 없어야 한다.
	// 2. 삭제 시작 첫 노드는 isOutput == true
	// 3. 삭제 진행 도중 isOutput == false
	private boolean delete(TrieNode curNode, String word, int idx){
		if(idx == word.length()){
			if(!curNode.isOutput()){ //문자열 자식 노드가 있으면 false;
				return false;
			}
			curNode.setOutput(false);
			return true;
		}

		char key = word.charAt(idx);
		TrieNode node = curNode.getChildNodes().get(key);
		if(node == null){
			return false;
		}

		boolean flag = delete(node, word, idx+1) && !node.isOutput();
		// 삭제 진행 중 (isOuput == false)
		if(flag){
			System.out.print(key+" - ");
			curNode.getChildNodes().remove(key);
			return true;
		}
		return false;

	}
}
