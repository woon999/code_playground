package leetcode;

// #141 - Linked List Cycle

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle_141 {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(0);
		ListNode node3 = new ListNode(-4);

		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node1;

		System.out.println("hasCycle(head) = " + hasCycle(head));
	}

	public static boolean hasCycle(ListNode head) {
		Set<Integer> listNodes = new HashSet<>();
		while(head != null){
			if(!listNodes.add(head.hashCode())) return true;
			head = head.next;
		}

		return false;
	}

}
