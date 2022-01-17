package bst.treap;

import static bst.treap.Treap.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
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
	}

	@Test
	public void print(){
		for (int key : keys) {
			root = treap.insert(root, new Node(key));
			System.out.println("insert: " + key);
			printTreap(root, 0);
		}
	}

	@Test
	public void insert(){
		for (int key : keys) {
			root = treap.insert(root, new Node(key));
		}
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
		for (int key : keys) {
			root = treap.insert(root, new Node(key));
		}
		printTreap(root, 0);

		System.out.println("erase: 9");
		root = treap.erase(root, 9);
		printTreap(root, 0);

		StringBuilder sb = new StringBuilder();
		recursion(root, sb);
		assertEquals(sb.toString(), "1245810");


	}

}
