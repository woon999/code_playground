package leetcode;

// #24 - Swap Nodes in Pairs
public class SwapNodesinPairs_24 {
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode d = new ListNode(4);
		ListNode c = new ListNode(3, d);
		ListNode b = new ListNode(2, c);
		ListNode a = new ListNode(1, b);

		ListNode ans = swapPairs(a);
		while (ans != null){
			System.out.print(ans.val+" ");
			ans = ans.next;
		}
		System.out.println();
	}

	public static ListNode swapPairs(ListNode head) {
		ListNode prev = new ListNode(0);
		prev.next = head;
		ListNode newHead = prev;

		while(prev.next != null && prev.next.next != null){
			ListNode n1 = prev.next;
			ListNode n2 = n1.next;
			ListNode nxt = n2.next;

			prev.next = n2;
			n2.next = n1;
			n1.next = nxt;

			prev = n1;
		}

		return newHead.next;
	}
}
