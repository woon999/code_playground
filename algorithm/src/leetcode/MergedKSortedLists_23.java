package leetcode;

// 23 -  Merged K Sorted ListsMerged K Sorted Lists

public class MergedKSortedLists_23 {
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		ListNode a3 = new ListNode(5);
		ListNode a2 = new ListNode(4, a3);
		ListNode a = new ListNode(1, a2);

		ListNode b3 = new ListNode(4);
		ListNode b2 = new ListNode(3, b3);
		ListNode b = new ListNode(1, b2);

		ListNode c2 = new ListNode(6);
		ListNode c = new ListNode(2, c2);

		ListNode[] listNodes = {a, b, c};

		System.out.println("mergeKLists(listNodes) = " + mergeKLists(listNodes));
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		return mergeKLists(lists, 0, lists.length - 1);
	}

	private static ListNode mergeKLists(ListNode[] lists, int s, int e) {
		if (s == e) {
			return lists[s];
		}

		int mid = (s + e) / 2;
		ListNode l = mergeKLists(lists, s, mid);
		ListNode r = mergeKLists(lists, mid + 1, e);

		return merge(l, r);
	}

	private static ListNode merge(ListNode l, ListNode r) {
		ListNode tmp = new ListNode(-1);
		ListNode head = tmp;

		while (l != null && r != null) {
			if (l.val < r.val) {
				tmp.next = l;
				l = l.next;
			} else {
				tmp.next = r;
				r = r.next;
			}
			tmp = tmp.next;
		}
		if (l != null) {
			tmp.next = l;
		} else {
			tmp.next = r;
		}
		return head.next;
	}
}
