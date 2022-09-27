package leetcode;

// #160 - Intersection Of Two Linked Lists
public class IntersectionOfTwoLinkedLists_160 {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static void main(String[] args) {
		int[] a = {4,1,8,4,5};
		ListNode headA = new ListNode(a[0]);
		ListNode tmp = headA;
		for(int i=1; i<5; i++){
			tmp.next = new ListNode(a[i]);
			tmp = tmp.next;
		}

		int[] b = {5,6,1,8,4,5};
		ListNode headB = new ListNode(b[0]);
		ListNode tmp2 = headB;
		for(int i=1; i<6; i++){
			tmp2.next = new ListNode(b[i]);
			tmp2 = tmp2.next;
		}

		ListNode result = getIntersectionNode(headA, headB);
		System.out.println(" ------------------ ");
		System.out.println("result = " + result);

	}

	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode node1 = headA;
		ListNode node2 = headB;
		while(node1 != node2){
			node1 = node1 == null? headA : node1.next;
			node2 = node2 == null? headB : node2.next;
		}
		return node1;
	}
}
