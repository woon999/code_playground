package leetcode;

// #83 - Remove Duplicates From Sorted List

public class RemoveDuplicatesFromSortedList_83 {
	static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		ListNode l3 = new ListNode(2);
		ListNode l2 = new ListNode(1, l3);
		ListNode head = new ListNode(1, l2);

		ListNode ln = deleteDuplicates(head);
		while (ln != null) {
			System.out.println(ln.val);
			ln = ln.next;
		}
	}

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode cur = head;
		ListNode prev = null;
		while (cur != null) {
			if (prev != null && prev.val == cur.val) {
				prev.next = cur.next;
			} else {
				prev = cur;
			}
			cur = cur.next;
		}
		return head;
	}
}
