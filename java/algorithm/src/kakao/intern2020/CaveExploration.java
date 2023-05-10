package kakao.intern2020;

//intern #5 동굴 탐험 (BFS)  
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CaveExploration {
	
	static List<Integer>[] list;
	static int[] limit;
	static int[] save;
	static boolean[] check;
	

	public static void main(String[] args) {
		int n = 9;
		int[][] path = {{0,1}, {0,3}, {0,7}, {8,1}, {3,6}, {1,2}, {4,7}, {7,5}};
		int[][] order = {{8,5}, {6,7}, {4,1}};
		
		
		System.out.println(solution(n,path,order));
		
	}
	
	 public static boolean solution(int n, int[][] path, int[][] order) {
	        
	        list = new ArrayList[n];
	        limit = new int[n];
	        save = new int[n];
	        check = new boolean[n];
	        
	        for(int i=0; i<n; i++) {
	        	list[i] = new ArrayList<>();
	        }
	        
	        for(int i=0; i<path.length; i++) {
	        	int a = path[i][0];
	        	int b = path[i][1];
	        	
	        	list[a].add(b);
	        	list[b].add(a);
	        }
	        
	        for(int i=0; i<order.length; i++) {
	        	limit[order[i][1]] = order[i][0];
	        }
	        if(limit[0] !=0) return false;
	        bfs(0);
	        
	        for(int i=0; i<n; i++) {
	        	if(!check[i]) {
	        		return false;
	        	}
	        }
	        return true;
	        
	        
	    }
	 
	 
	 static void bfs(int start) {
		 Queue<Integer> q = new LinkedList<>();
		 
		 check[start]=true;
		 q.add(start);
		 System.out.println(start);
		 
		 for(int i=0; i<list[start].size(); i++) {
			 q.add(list[start].get(i));
		 }
		 
		 while(!q.isEmpty()) {
			 int pos = q.poll();
			 
			 if(check[pos]) continue;
			 
			 // 제한되어 있으면 save에 저장 
			 if(!check[limit[pos]]) {
				 save[limit[pos]] =pos;
				 continue;
			 }
			 
			 check[pos] = true;
			 for(int i=0; i<list[pos].size(); i++) {
				 int nPos = list[pos].get(i);
					 System.out.println(nPos);
					 q.add(nPos);
			 }
			
			 // if pos ==8, save[8] = 5 추가
			 q.add(save[pos]);
			 
		 }
	 }
	 
	
}

