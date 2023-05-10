package leetcode;

// #2 - AddTwoNumber

public class AddTwoNumber_2 {
	static class ListNode {
	  int val;
	  ListNode next;
	  ListNode() {}
	  ListNode(int val) { this.val = val; }
	  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void main(String[] args) {
		ListNode node3 = new ListNode(3);
		ListNode node2 = new ListNode(4, node3);
		ListNode l1 = new ListNode(2, node2);

		ListNode node5 = new ListNode(4);
		ListNode node4 = new ListNode(6, node5);
		ListNode l2 = new ListNode(5, node4);


		ListNode listNode = addTwoNumbers(l1, l2);
		System.out.println("---");
		while(listNode != null){
			System.out.println(listNode.val);
			listNode = listNode.next;
		}

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int flag = 0;
		ListNode head = null, tmp = null;
		while(l1 != null || l2 != null){
			int sum = flag;
			if(l1 != null){
				sum += l1.val;
				l1 = l1.next;
			}

			if(l2 != null){
				sum += l2.val;
				l2 = l2.next;
			}

			flag = 0;

			ListNode sumNode = new ListNode(sum % 10);

			if (head == null) { // 첫 노드
				head = sumNode;
				tmp = head;
			} else{
				tmp.next = sumNode;
				tmp = sumNode;
			}

			if(sum >= 10){
				flag = 1;
			}
		}

		if(flag == 1){
			tmp.next = new ListNode(1);
		}

		return head;
	}

}
