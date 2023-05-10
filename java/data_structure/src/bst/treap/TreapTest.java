package bst.treap;

import static bst.treap.Treap.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreapTest {
	int[] keys = {5, 2, 1, 4, 9, 8, 10};
	Node root;
	Treap treap;
	@BeforeEach
	public void setUp(){
		root = null;
		treap = new Treap();
		for (int key : keys) {
			root = treap.insert(root, new Node(key));
		}
	}

	@Test
	public void print(){
		printTreap(root, 0);
	}

	@Test
	public void insert(){
		StringBuilder sb = new StringBuilder();
		recursion(root, sb);
		System.out.println(sb.toString());
		assertEquals(sb.toString(), "12458910");
	}

	private void recursion(Node node, StringBuilder sb){
		if(node == null) return;
		recursion(node.left, sb);
		sb.append(node.key);
		recursion(node.right, sb);
	}


	@Test
	public void erase(){
		printTreap(root, 0);

		System.out.println("erase: 9");
		root = treap.erase(root, 9);
		printTreap(root, 0);

		StringBuilder sb = new StringBuilder();
		recursion(root, sb);
		assertEquals(sb.toString(), "1245810");
	}

	@Test
	public void getKthNumber(){
		printTreap(root, 0);
		Node kth = treap.kth(root, 3);
		assertEquals(4, kth.key);
	}

	@Test
	public void countLessThanX(){
		// 1, 2, 4, 5, 8, 9, 10
		printTreap(root, 0);
		assertEquals(treap.countLessThan(root, 5), 3);
		assertEquals(treap.countLessThan(root, 10), 6);
	}

}
