package leetcode;

// #19 - Remove Nth Node From End of List
public class RemoveNthNodeFromEndofList_19 {
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode e = new ListNode(5);
		ListNode d = new ListNode(4, e);
		ListNode c = new ListNode(3, d);
		ListNode b = new ListNode(2, c);
		ListNode a = new ListNode(1, b);

		System.out.println("removeNthFromEnd(a, 2) = " + removeNthFromEnd(a, 1).val);
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode block = head;
		ListNode pointer = head;

		while(n > 0){
			block = block.next;
			if(block == null) {
				return head.next;
			}
			n--;
		}

		while(block.next != null){
			block = block.next;
			pointer = pointer.next;
		}

		// pointer.next 삭제
		pointer.next = pointer.next.next;

		return head;
	}
}
