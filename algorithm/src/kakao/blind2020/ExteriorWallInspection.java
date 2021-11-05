package kakao.blind2020;

// blind #6 외벽 점검 - 조합, 브루트포스 
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExteriorWallInspection {
	
	static int answer, size, wSize, INF = Integer.MAX_VALUE;
	static int[] dis, fullWeak;
	static Stack<Integer> st;
	static boolean[] check;
	public static void main(String[] args) {
		int n = 12;
//		int[] weak = {1, 5, 6, 10};
//		int[] dist = {1, 2, 3, 4};
		int[] weak = {1, 3, 4, 9 ,10};
		int[] dist = {3, 5, 7};
		System.out.println(solution(n, weak, dist));
	}
	public static int solution(int n, int[] weak, int[] dist) {
        wSize = weak.length;
        dis = dist;
        size = dis.length;
        answer=INF;
        
        fullWeak = getFullWeak(n,weak);
        for(int i=1; i<dist.length+1; i++) {
        	st = new Stack<>();
        	check = new boolean[size];
        	permutation(i);
        }
        return answer == INF ? -1 : answer;
    }

	static void permutation(int r) {
		if(st.size() == r) {
			List<Integer> distCase = new ArrayList<>();
			for(int num : st) {
				distCase.add(num);
			}
			inspection(distCase);
		}
		
		for(int i=0; i<size; i++) {
			if(!check[i]) {
				check[i] = true;
				st.push(dis[i]);
				permutation(r);
				st.pop();
				check[i] = false;
			}
		}
	}
	
	static void inspection(List<Integer> distCase) {
        for(int i=0; i<fullWeak.length-wSize+1; i++) {
        	int idx=0;
        	int cur=0;
        	int nxt=0;
        	//현재 dist 케이스로 외벽 조사 가능한지 완전 탐색 
        	for(cur=i; cur<i+wSize;) {
        		nxt = cur+1;
        		while(nxt < i+wSize &&
        				fullWeak[cur] + distCase.get(idx) >= fullWeak[nxt]) {
        			nxt++;
        		}
        		cur= nxt;
        		idx++;
        		if(idx == distCase.size()) break;
        	}
        	
        	// (cur-i) == wSize : 모든 외벽을 수리했는지 체크 
        	if((cur-i)==wSize && idx<answer) {
        		answer= idx;
        	}
        }
        
	}
	
	static int[] getFullWeak(int n, int[] weak) {
		int fullSize = weak.length*2-1;
		int[] arr = new int[fullSize];
		for(int i=0; i<weak.length; i++) {
			arr[i] = weak[i];
		}
		
		for(int i=weak.length; i<fullSize; i++) {
			arr[i] =weak[i-weak.length] +n;
		}
		return arr;
	}
}
