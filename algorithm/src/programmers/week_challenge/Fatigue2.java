package programmers.week_challenge;

// week12 피로도 -dfs  

public class Fatigue2 {
	public static void main(String[] args) {
		int k = 80;
		int[][] dungeons = {{80, 20},{50, 40},{30, 10}};
		solution(k, dungeons);
	}
	
	public static int solution(int k, int[][] dungeons) {
        return dfs(k,dungeons);
    }
	
	static int dfs(int k, int[][] dungeons) {
		int cnt=0;
		for(int[] d : dungeons) {
			int a = d[0];
			if(k>=d[0]) {
				d[0] = 5001;
				cnt = Math.max(1+dfs(k-d[1],dungeons), cnt);
				d[0] = a;
			}
		}
		System.out.println(k+" -" + cnt);
		return cnt;
	}
}
