package kakao.intern2021;

// #5 kakao2021intern 시험장 나누기 - 트리 dp, 파라메트릭 서치 
import java.util.ArrayList;
import java.util.List;

public class DivisionExamRoom {

	static class Node{
		int data;
		int left, right;
		
		
		public Node(int data, int left, int right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	static int INF = 789654321, maxSection =10001;
	static List<Node>[] list;
	static int[][] cost;
	public static void main(String[] args) {
//		int k = 1;
//		int[] num = {6,9,7,5};
//		int[][] links = {{-1, -1}, {-1, -1}, {-1, 0}, {2, 1}};
		
		int k = 3;
		int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
		int[][] links = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, 
						{8, 5}, {2, 10}, {3, 0}, {6, 1},
						{11, -1}, {7, 4}, {-1, -1}, {-1, -1}};
		
		System.out.println(solution(k, num, links));
	}
	
	public static int solution(int k, int[] num, int[][] links) {
        int size = num.length;
        list = new ArrayList[size];
        for(int i=0; i<size; i++) {
        	list[i] = new ArrayList<>();
        }
        int sum = 0;
        boolean[] check = new boolean[size];
        for(int i=0; i<size; i++) {
        	int left = links[i][0];
        	int right = links[i][1];
        	if(left != -1) check[left] = true;
        	if(right != -1) check[right] = true;
        	list[i].add(new Node(num[i], left, right));
        	sum += num[i];
        }	
        
        int root = -1;
        for(int i=0; i<size; i++) {
        	if(!check[i]) root = i;
        }
//        System.out.println("root : " + root);
        
        int left = sum/k;
        int right = sum;
        if(left == right) return right;
        else {
	        while(left+1 < right) {
//	        	System.out.println();
//	        	System.out.println(left+"," + right);
	        	int mid = (left+right)/2;
	        	cost = new int[size][2];
	        	traversal(root, mid);
//	        	System.out.println("result : " +cost[root][0] +" <= "+ k);
	        	if(cost[root][0] <= k) {
	        		right = mid;
	        	}else {
	        		left = mid;
	        	}
	        }
	        return right;
        }

    }
	
	static void traversal(int pos, int w) {
		Node curNode = list[pos].get(0);
		int data = curNode.data;
		int l = curNode.left;
		int r = curNode.right;
		
		if(l != -1) traversal(l,w);
		if(r != -1) traversal(r,w);
		
//		System.out.println("===========================");
//		System.out.println(pos + "번 노드 "  + w);
//		System.out.println((curNode.left==-1?"null":curNode.left) + " -  [" + curNode.data + "] - " + (curNode.right==-1?"null":curNode.right) );
//		System.out.println();
		// leaf 노드 
		if(l == -1 && r== -1) {
			if(data <= w) {
				cost[pos][0] = 1;
				cost[pos][1] = data;
			}else {
				cost[pos][0] = maxSection;
				cost[pos][1] = INF;
			}
		}
		// full 노드 
		else if(l!=-1&&r!=-1){
//			System.out.println("====full=====");
			// 1) pos + 왼쪽 트리 + 오른쪽 트리 <= L 
			// section l+r-1
			if(data + cost[l][1] + cost[r][1] <=w) {
				cost[pos][0] = cost[l][0] + cost[r][0] -1;
				cost[pos][1] = data + cost[l][1] + cost[r][1];
//				System.out.println("#1 "  + cost[pos][0] + "," + cost[pos][1]);
			}
			// 2) pos + min(왼쪽, 오른쪽)  <= L
			// section  l+r
			else if(data + Math.min(cost[l][1],cost[r][1]) <= w) {
				cost[pos][0] = cost[l][0] + cost[r][0];
				cost[pos][1] = data + Math.min(cost[l][1], cost[r][1]);
//				System.out.println("#2 "  + cost[pos][0] + "," + cost[pos][1]);
			}
			// 3) pos <= L
			// section  l+r +1
			else if(data <= w) {
				cost[pos][0] = cost[l][0] + cost[r][0] +1;
				cost[pos][1] = data;
//				System.out.println("#3 "  + cost[pos][0] + "," + cost[pos][1]);
				
			}
			else {
				cost[pos][0] = maxSection;
				cost[pos][1] = INF;
//				System.out.println("#4 "  + cost[pos][0] + "," + cost[pos][1]);
			}
		}else {
			// 왼쪽 자식만 있는 경우
			if(r == -1) {
//				System.out.println("====left=====");
				// 1) pos + 왼쪽 트리 <= L
				// section l 
				if(data + cost[l][1] <=w) {
					cost[pos][0] = cost[l][0];
					cost[pos][1] = data + cost[l][1];
//					System.out.println("#1 "  + cost[pos][0] + "," + cost[pos][1]);
				}
				// 1) pos <= L
				// section l +1  
				else if(data <= w) {
					cost[pos][0] = cost[l][0]+1;
					cost[pos][1] = data;
//					System.out.println("#2 "  + cost[pos][0] + "," + cost[pos][1]);
				}
				else {
					cost[pos][0] = maxSection;
					cost[pos][1] = INF;
//					System.out.println("#3 "  + cost[pos][0] + "," + cost[pos][1]);
				}
			}
			
			// 오른쪽 자식만 있는 경우
			if(l == -1) {
//				System.out.println("====right=====");
				// 1) pos + 오른쪽 트리 <= L
				// section r 
				if(data + cost[r][1] <=w) {
					cost[pos][0] = cost[r][0];
					cost[pos][1] = data + cost[r][1];
//					System.out.println("#1 " + cost[pos][0] + "," + cost[pos][1]);
				}
				// 1) pos <= L
				// section r +1  
				else if(data <= w) {
					cost[pos][0] = cost[r][0]+1;
					cost[pos][1] = data;
//					System.out.println("#2 "  + cost[pos][0] + "," + cost[pos][1]);
				}
				else {
					cost[pos][0] = maxSection;
					cost[pos][1] = INF;
//					System.out.println("#3 "  + cost[pos][0] + "," + cost[pos][1]);
				}
			}
		}
		

	}
}
