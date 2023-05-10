package bst.treap;

import java.util.Random;

class Node {
	int key;  // 이 노드에 저장된 원소
	int priority; // 이 노드의 우선순위(proirity)
	int size; // 이 노드를 루트로 하는 서브트리의 크기
	Node left, right;

	// data, left, right 초기화 및 난수 우선순위 생성
	Node(int key) {
		this.key = key;
		this.priority = new Random().nextInt(100);
		this.size = 1;
		this.left = this.right = null;
	}

	void setLeft(Node left) {
		this.left = left;
		calcSize();
	}

	void setRight(Node right) {
		this.right = right;
		calcSize();
	}

	void calcSize() {
		size = 1;
		if (left != null) {
			size += left.size;
		}
		if (right != null) {
			size += right.size;
		}
	}
}
