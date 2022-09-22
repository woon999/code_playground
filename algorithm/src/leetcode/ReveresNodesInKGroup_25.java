package leetcode;


// # 25 - Reverse Nodes in k-Group
// 선행 - ReverseLinkedList_206

public class ReveresNodesInKGroup_25 {
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
		for(int i=2; i<=2; i++){
			tmp.next = new ListNode(i);
			tmp = tmp.next;
		}
		int k = 2;

		ListNode result = reverseKGroup(head, k);
		System.out.println("--- result ---");
		print(result);
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode result = new ListNode();
		ListNode prev = null;
		while(head != null) {
			prev = deepCopy(head);
			Object[] nodes = reverseList(head, k);
			ListNode front = (ListNode)nodes[0];
			ListNode end = (ListNode)nodes[1];
			if(end == null && (int)nodes[2] != 0){
				result = link(result, prev);
			}else{
				result = link(result, front);
			}

			head = end;
			print(result);
		}

		return result.next;
	}

	static ListNode deepCopy(ListNode node){
		if(node == null) return null;

		ListNode tmp = new ListNode(node.val);
		ListNode copy = tmp;
		while(true){
			node = node.next;
			if(node == null) break;
			copy.next = new ListNode(node.val);
			copy = copy.next;
		}

		return tmp;
	}

	static ListNode link(ListNode front, ListNode end){
		ListNode tmp = front;
		while(tmp.next != null){
			tmp = tmp.next;
		}
		tmp.next = end;
		return front;
	}

	static void print(ListNode node){
		System.out.println("----- node print -----");
		while(node != null){
			System.out.print("node(" + node.val +") -- ");
			node = node.next;
		}
		System.out.println();
	}

	public static Object[] reverseList(ListNode head, int k) {
		ListNode t1 = head, t2 = null;
		head = head.next;
		k--;

		t1.next = null;
		while(head != null){
			if(k==0) break;
			t2 = head.next;
			head.next = t1;
			t1 = head;
			head = t2;
			k--;
		}

		if(k > 0) return new Object[]{t1, null, k};
		return new Object[]{t1, head, k};
	}
}

