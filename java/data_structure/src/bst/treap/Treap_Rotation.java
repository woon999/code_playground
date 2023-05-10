package bst.treap;

/*
    ref : https://www.techiedelight.com/implementation-treap-data-structure-cpp-java-insert-search-delete/
*/

public class Treap_Rotation {

	public static Node insertNode(Node root, int key) {
		if (root == null) {
			return new Node(key);
		}

		// root가 key 미만이면 오른쪽 서브트리를 쪼갠다.
		if (key < root.key) {
			root.left = insertNode(root.left, key);
			if (root.left != null && root.left.priority > root.priority) {
				root = rotateRight(root);
			}
		} else { // root가 key 이상이면 왼쪽 서브트리를 쪼갠다.
			root.right = insertNode(root.right, key);
			if (root.right != null && root.right.priority > root.priority) {
				root = rotateLeft(root);
			}
		}
		return root;
	}

	/* Function to left-rotate a given BST.treap

			 r                         R
			/ \      Left Rotate      / \
		   L   R        ———>         r   Y
			  / \                   / \
			 X   Y                 L   X
   */
	private static Node rotateLeft(Node root) {
		Node R = root.right;
		Node X = root.right.left;
		// rotate
		R.left = root;
		root.right = X;
		return R;
	}

	/* Function to right-rotate a given BST.treap

			   r                        L
			  / \     Right Rotate     / \
			 L   R        ———>        X   r
			/ \                          / \
		   X   Y                        Y   R
   */
	private static Node rotateRight(Node root) {
		Node L = root.left;
		Node Y = root.left.right;
		// rotate
		L.right = root;
		root.left = Y;
		return L;
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

	public static void main(String[] args) {
		int[] keys = {5, 2, 1, 4, 9, 8, 10};

		Node root = null;
		for (int key : keys) {
			root = insertNode(root, key);
			System.out.println("-------------");
			printTreap(root, 0);
		}
	}
}
