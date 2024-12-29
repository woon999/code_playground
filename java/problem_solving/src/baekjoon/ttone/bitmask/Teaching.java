package baekjoon.ttone.bitmask;

// #1062 bitmask 가르침 - 비트마스킹, 조합 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teaching {

	static String[] data;
	static int n,k, answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int mask =0;
		// a 1, c 3, i 9, n 14, t 20
		mask |= 1 << (int)'a'-96;
		mask |= 1 << (int)'c'-96;
		mask |= 1 << (int)'i'-96;
		mask |= 1 << (int)'n'-96;
		mask |= 1 << (int)'t'-96;
		
		data = new String[n];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			line = line.replaceAll("[a,c,i,n,t]", "");
			data[i] = line;
		}
		
		test(0, 5, mask);
		System.out.println(answer);
		
		
	}
	
	static void test(int idx, int cnt, int mask) {
		if(cnt == k) {
			int canRead = 0;
			for(int i=0; i<n; i++) {
				boolean flag = true;
				for(int j=0; j<data[i].length(); j++) {
					if((mask & (1<< data[i].charAt(j)-96)) == 0) {
						flag = false;
						break;
					}
				}
				if(flag) canRead++;
			}
			answer = Math.max(answer, canRead);
			return;
		}
		
		for(int i=idx; i<27; i++) {
			if((mask & (1<<i)) == 0) {
				test(i+1, cnt+1, mask|(1<<i));
			}
		}
	}
}
