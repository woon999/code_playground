package kakao.intern2021;

// #5 kakao2021intern 시험장 나누기 - 중간저장 
import java.util.ArrayList;
import java.util.List;

public class DivisionExamRoom {

	static class Node{
		int data;
		int left, right;
		public Node() {
			/* no-op */
		}
		
		public Node(int data, int left, int right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	static int sectionSize, root, total=0, depth=-1;
	static List<Node>[] list;
	static int[][] cost;
	public static void main(String[] args) {
		int k = 2;
		int[] num = {6,9,7,5};
		int[][] links = {{-1, -1}, {-1, -1}, {-1, 0}, {2, 1}};
		
		solution(k, num, links);
	}
	
	public static int solution(int k, int[] num, int[][] links) {
        int answer = 0;
        int size = num.length;
        sectionSize = k;
        list = new ArrayList[size];
        cost = new int[size][k+1];
        for(int i=0; i<size; i++) {
        	list[i] = new ArrayList<>();
        }
        boolean[] check = new boolean[size];
        for(int i=0; i<size; i++) {
        	int left = links[i][0];
        	int right = links[i][1];
        	if(left != -1) check[left] = true;
        	if(right != -1) check[right] = true;
        	list[i].add(new Node(num[i], left, right));
        	total += num[i];
        	
        }
        
        root = -1;
        for(int i=0; i<size; i++) {
        	if(!check[i]) root = i;
        	Node pos = list[i].get(0);
        	System.out.println(i+"번 노드 : " + (pos.left==-1?"null":pos.left) + " -  [" + pos.data + "] - " + (pos.right==-1?"null":pos.right) );
        }
        
        System.out.println();
        
        trevarsal(root,0);
        
        System.out.println();
        for(int a=0; a<size; a++) {
        	System.out.print(a + "번 노드 : ");
	        for(int i=1; i<k+1; i++	) {
	        	System.out.print(cost[a][i]+ " " );
	        }
	        System.out.println();
        }
        return answer;
    }
	
	static void trevarsal(int pos, int cnt) {
		
		Node curNode = list[pos].get(0);
//		System.out.println(curNode.left+" , " +pos + " ," + curNode.right);
		
		for(Node child : list[pos]) {
			
			if(child.left!=-1) trevarsal(child.left, cnt+1);
			if(child.right!=-1) trevarsal(child.right, cnt+1);
			
			System.out.println("--------------------------");
			System.out.println(pos + "번 노드 " + cnt);
			System.out.println((child.left==-1?"null":child.left) + " -  [" + child.data + "] - " + (child.right==-1?"null":child.right) );
			
			
			cost[pos][1] = child.data;
			System.out.println(pos+" 데이터 저장 " + child.data);
			int mSize = (int)Math.pow(2, depth+1 - cnt)-1;
			
			// leaf노드 
			if(child.left==-1 && child.right == -1) {
				depth = Math.max(depth, cnt);
			}

			else {
//				System.out.println("최대분할개수 : " +mSize);
				// 왼쪽 자식노드
				if(child.left!=-1) {
					for(int d=1; d<mSize+1; d++) {
						if(d > sectionSize ) break;
						if(d< sectionSize)  { //d < sectionSize
							System.out.println((d+1) + " .#1 " + cost[pos][d] + " vs " +cost[child.left][d]);
							cost[pos][d+1] = Math.max(cost[pos][d],cost[child.left][d]);
							System.out.println(pos+","+(d+1) +" = " +cost[pos][d+1]);
							System.out.println();
						}
						if( d < sectionSize) {
							System.out.println(d+ ". #2 " + cost[pos][d] + " + " +cost[child.left][d]);
							if(d==sectionSize) {
								int comp1 = Math.max(total-cost[pos][d],cost[pos][d]);
								int tmp = cost[pos][d] +cost[child.left][d];
								int comp2 = Math.max(total-tmp, cost[pos][d] +cost[child.left][d]);
								System.out.println(comp1 + "," +comp2);
								if(comp1 > comp2) {
									cost[pos][d] = cost[pos][d] +cost[child.left][d];
								}
							}else {
								if(cost[pos][d] < cost[pos][d] +cost[child.left][d]) {
									cost[pos][d] = cost[pos][d] +cost[child.left][d];
//									System.out.println(pos+","+(d) +" = " +cost[pos][d]);
									System.out.println();
								}
							}
						}
					}
				}
				 
				if(child.right!=-1) {
					for(int d=1; d<mSize+1; d++) {
						if(d > sectionSize ) break;// k==2
						
						if(d< sectionSize) { //d < sectionSize
							System.out.println((d+1)+ " .#3 " + cost[pos][d]+ " vs " +cost[child.right][d]);
							cost[pos][d+1] = Math.max(cost[pos][d],cost[child.right][d]);
							System.out.println(pos+","+(d+1) +" = " +cost[pos][d+1]);
							System.out.println();
						}
						if( d < sectionSize) {
							System.out.println(d +" .#4 " + cost[pos][d] + " + " +cost[child.right][d]);
							if(cost[pos][d] < cost[pos][d] +cost[child.right][d]) {
								cost[pos][d] = cost[pos][d] +cost[child.right][d];
//								System.out.println(pos+","+(d) +" = " +cost[pos][d]);
								System.out.println();
							}
						}
						
					}
				}
			}
		}
	}
}
