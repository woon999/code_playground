package leetcode;

// #21 - Merge Two Sorted Lists

public class MergeTwoSortedLists_21 {

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
		ListNode l3 = new ListNode(4);
		ListNode l2 = new ListNode(2, l3);
		ListNode l1 = new ListNode(1, l2);

		ListNode l6 = new ListNode(4);
		ListNode l5 = new ListNode(3, l6);
		ListNode l4 = new ListNode(1, l5);

		ListNode tmp = mergeTwoLists(l1, l4);
		while (tmp != null){
			System.out.println("tmp = " + tmp.val);
			tmp = tmp.next;
		}

	}

	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode tmp = new ListNode();
		ListNode head = tmp;
		// 하나라도 끝나면 종료
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				tmp.next = list1;
				list1 = list1.next;
			} else {
				tmp.next = list2;
				list2 = list2.next;
			}

			tmp = tmp.next;
		}

		// 남은 부분 이어붙이기
		tmp.next = list1 != null? list1 : list2;
		return head.next;
	}
}
