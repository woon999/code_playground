package programmers.week_challenge;

// week12 피로도 - 완전탐색  
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Fatigue {

	static int f, max=-1;
	static int[][] data;
	static boolean[] check;
	static Stack<Integer> s;
	public static void main(String[] args) {
		int k = 80;
		int[][] dungeons = {{80, 20},{50, 40},{30, 10}};
		solution(k, dungeons);
	}
	
	public static int solution(int k, int[][] dungeons) {
        int answer = -1;
        f = k;
        data = dungeons.clone();
        int n = dungeons.length;
    	s = new Stack<>();
    	check = new boolean[n+1];
    	comb(n);
    	
    	System.out.println("## " + max);
        return answer;
    }
	
	static void comb(int r) {
		if(s.size() == r) {
			List<Integer> list = new ArrayList<>();
			for(int num : s) {
				list.add(num-1);
				System.out.print(num +" ");
			}
			System.out.println();
			simulation(list);
			return;
		}
		for(int i=1; i<=r; i++) {
			if(!check[i]) {
				check[i] = true;
				s.push(i);
				comb(r);
				s.pop();
				check[i] =false;
			}
		}
	}
	
	static void simulation(List<Integer> list) {
		// 1 2 3
		int cnt=0;
		int fatigue = f;
		for(int order : list) {
			if(fatigue>= data[order][0]) {
				fatigue-= data[order][1];
				System.out.println(order +" :" + data[order][1]);
				cnt++;
			}
		}
		max = Math.max(max, cnt);
		System.out.println(cnt);
	}
}
