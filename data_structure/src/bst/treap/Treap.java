package bst.treap;

public class Treap {
	public Node insert(Node root, Node node) {
		if (root == null) {
			return node;
		}

		// node가 root를 대체헤야 한다. 해당 서브트리를 반으로 잘라 각각 자손으로 한다.
		if (root.priority < node.priority) {
			Node[] splitted = split(root, node.key);
			node.setLeft(splitted[0]);
			node.setRight(splitted[1]);
			return node;
		} else if (root.key > node.key) {
			root.setLeft(insert(root.left, node));
		} else {
			root.setRight(insert(root.right, node));
		}
		return root;
	}

	// split 두 개의 트립으로 분리한다.
	private Node[] split(Node root, int key) {
		Node[] pair = new Node[2];
		if (root == null) {
			return pair;
		}

		// 루트가 key 미만이면 오른쪽 서브트리를 쪼갠다.
		if (root.key < key) {
			Node[] rs = split(root.right, key);
			root.setRight(rs[0]);
			return new Node[] {root, rs[1]};
		} else { // root가 key 이상이면 왼쪽 서브트리를 쪼갠다.
			Node[] ls = split(root.left, key);
			root.setLeft(ls[1]);
			return new Node[] {ls[0], root};
		}
	}

	// root를 루트로 하는 트립에서 key를 지우고 결과 트립의 루트를 반환한다.
	public Node erase(Node root, int key) {
		if (root == null)
			return root;
		// root를 지우고 양 서브트리를 합친 뒤 반환한다.
		if (root.key == key) {
			Node ret = merge(root.left, root.right);
			root = null;
			return ret;
		}

		if (root.key > key) {
			root.setLeft(erase(root.left, key));
		} else {
			root.setRight(erase(root.right, key));
		}
		return root;
	}

	// a와 b가 두 개의 트립이고, max(a) < min(b)일 때 이 둘을 합친다.
	private Node merge(Node a, Node b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (a.priority < b.priority) {
			b.setLeft(merge(a, b.left));
			return b;
		}
		a.setRight(merge(a.right, b));
		return a;
	}

	// root를 루트로 하는 트리 중에서 k번째 원소를 반환한다.
	public Node kth(Node root, int k) {
		// 왼쪽 서브트리의 크기를 우선 계산한다.
		int leftSize = 0;
		if (root.left != null) {
			leftSize = root.left.size;
		}
		if (k <= leftSize)
			return kth(root.left, k);
		if (k == leftSize + 1)
			return root;
		return kth(root.right, k - leftSize - 1);
	}

	// key보다 작은 키를 갖는 노드의 수를 반환한다.
	public int countLessThan(Node root, int key) {
		if (root == null) {
			return 0;
		}

		if (root.key >= key) {
			return countLessThan(root.left, key);
		}

		// key가 현재 노드보다 클 경우
		// 왼쪽 서브트리 크기 + 현재 노드 + 오른쪽에서 key보다 작은 노드 수
		int ls = root.left == null ? 0 : root.left.size;
		return ls + 1 + countLessThan(root.right, key);
	}

	public static void printTreap(Node root, int space) {
		final int h = 10;
		if (root == null)
			return;

		space += h;
		printTreap(root.right, space);
		System.lineSeparator();
		for (int i = h; i < space; i++) {
			System.out.print(' ');
		}

		System.out.println(root.key + "(" + root.priority + ")");

		System.lineSeparator();
		printTreap(root.left, space);
	}

}
