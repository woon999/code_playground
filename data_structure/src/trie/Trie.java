package trie;

import java.util.LinkedList;
import java.util.Queue;

public class Trie {
	static final int ALPHA_SIZE = 26;
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// 새로운 문자열 추가
	public void insert(String word) {
		TrieNode curNode = this.root;
		System.out.println("## insert ");
		for (int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);
			curNode = curNode.getChildNodes()
				.computeIfAbsent(key, c -> new TrieNode());
			curNode.key = key;
			System.out.print(key + " - ");

			if(i== word.length()-1){
				System.out.println("is output");
				curNode.setOutput(true);
			}
		}
	}

	// 특정 단어가 있는지 확인
	public boolean contains(String word) {
		TrieNode curNode = this.root;
		for (int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);
			TrieNode node = curNode.getChildNodes().get(key);

			if (node == null) {
				return false;
			}

			curNode = node;
		}
		return curNode.isOutput();
	}

	public void delete(String word) {
		System.out.println("## delete ");
		if (delete(root, word, 0)) {
			System.out.println(" delete success ");
		}

	}

	// 1. 해당 문자열은 자식 노드가 없어야 한다.
	// 2. 삭제 시작 첫 노드는 isOutput == true
	// 3. 삭제 진행 도중 isOutput == false
	private boolean delete(TrieNode curNode, String word, int idx) {
		if (idx == word.length()) {
			if (!curNode.isOutput()) { //문자열 자식 노드가 있으면 false;
				return false;
			}
			curNode.setOutput(false);
			return true;
		}

		char key = word.charAt(idx);
		TrieNode node = curNode.getChildNodes().get(key);
		if (node == null) {
			return false;
		}

		boolean flag = delete(node, word, idx + 1) && !node.isOutput();
		// 삭제 진행 중 (isOuput == false)
		if (flag) {
			System.out.print(key + " - ");
			curNode.getChildNodes().remove(key);
			return true;
		}
		return false;
	}

	/**
	 * Aho-Corasick 알고리즘 (다중 문자열 검색)
	 * 1. computeFailFunc(): 실패함수 계산 메서드 (BFS)
	 * 2. ahoCorasick(str): str 매칭 여부 확인 메서드 (KMP)
	 */
	public void computeFailFunc() {
		Queue<TrieNode> q = new LinkedList<>();
		this.root.fail = this.root;
		q.add(this.root);

		while (!q.isEmpty()) {
			TrieNode curNode = q.poll();

			for (int i = 0; i < ALPHA_SIZE; i++) {
				char key = (char)(i + 97);
				TrieNode childNode = curNode.getChildNodes().get(key);

				if (childNode == null) {
					continue;
				}

				if (curNode == this.root) { // level 1
					childNode.fail = this.root;
				} else {
					TrieNode failNode = curNode.fail; // 부모의 실패노드

					// 부모 실패노드의 자식노드가 key가 아닌 경우 실패노드 거슬러 탐색하기
					while (failNode != this.root && failNode.getChildNodes().get(key) == null) {
						failNode = failNode.fail;
					}

					// 부모 실패노드의 자식노드가 key인 경우 실패링크 연결
					if (failNode.getChildNodes().get(key) != null) {
						failNode = failNode.getChildNodes().get(key);
					}

					childNode.fail = failNode;
				}

				// 실패링크 엔드노드가 output == true 이면, 해당 노드도 output
				if (childNode.fail.isOutput()) {
					childNode.setOutput(true);
				}
				q.add(childNode);
			}
		}
	}

	// 매칭되는 문자열 갯수 탐색
	public int ahoCorasick(String word) {
		TrieNode curNode = this.root;
		int cnt = 0;
		for (int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);

			// 해당 노드가 루트가 아니고 해당 트리에는 더이상 일치하는 문자(key)가 없을 때 실패링크로 이동
			while (curNode != this.root && curNode.getChildNodes().get(key) == null) {
				curNode = curNode.fail;
			}

			// 해당되는 문자(key)가 트라이에 있다면 해당 노드로 이동
			if (curNode.getChildNodes().get(key) != null) {
				curNode = curNode.getChildNodes().get(key);
			}

			// 문자열 집합 중 해당되는 바늘 문자열이 있다면 count
			if (curNode.isOutput()) {
				cnt++;
			}
		}
		return cnt;
	}
}
