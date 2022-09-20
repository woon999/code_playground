package leetcode;

// #206 - Reverse LinkedList
public class ReverseLinkedList_206 {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode tmp = head;
		for(int i=2; i<5; i++){
			tmp.next = new ListNode(i);
			tmp = tmp.next;
		}

		ListNode l1 = reverseList(head);
		System.out.println("------------");
		while(l1 != null){
			System.out.println("l1 = " + l1.val);
			l1 = l1.next;
		}
	}

	public static ListNode reverseList(ListNode head) {
		if(head == null) return head;
		// 1 -> 2 -> 3 -> 4
		ListNode t1 = head, t2 = null; // t1: 1 -> null
		head = head.next; // 2 -> 3 -> 4
		t1.next = null;
		while(head != null){
			t2 = head.next; // t2: 3 -> 4
			head.next = t1; // head.next = 1 -> null
			t1 = head; // t1 = 2 -> 1 -> null
			head = t2; // head: 3 -> 4
		}
		return t1;
	}
}
