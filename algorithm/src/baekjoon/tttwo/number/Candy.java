package baekjoon.tttwo.number;

// #3955 number 캔디 분배 - 확장 유클리드 호재법   
import java.io.*;
import java.util.StringTokenizer;

public class Candy {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		System.out.println(-465%1337);
		while(t-->0){
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
				
			long[] res = eeg(k,c);
			
			if(res[1] != 1) { // 나머지가 1이 아니라면 impossible
				System.out.println("IMPOSSIBLE");
			}else {
				long t0 = res[0];
				
				// t0 = (t0 % k + k ) % k 
				// 이는 -t0 % k = -t0이기 때문 
				// t0 % = k;
				// if(t0 < 0) t0 += k;와 같은 값 
				t0 = (t0% k + k) % k;
				t0 = Math.max(t0, (k+c)/c);
				
				if(t0 <= 1e9) {
					System.out.println(t0);
				}else { // 10^9보다 크면 impossbile
					System.out.println("IMPOSSIBLE");
				}
			}
		}
	}
	
	static long[] eeg(long a, long b) {
		long r0 = a, r1 = b;
		long s0 = 1, s1 = 0;
		long t0 = 0, t1 = 1;
		
		long tmp;
		while(r1 != 0) {
			long q = r0/r1;
			
			tmp = r0 - q*r1;
			r0 = r1;
			r1 = tmp;
//			System.out.println("r0 :"+r0 +", r1 :" + r1);
			
			tmp = s0 - q*s1;
			s0 = s1;
			s1 = tmp;
//			System.out.println("s0 :"+s0 +", s1 :" + s1);
			
			tmp = t0 - q*t1;
			t0 = t1;
			t1 = tmp;
//			System.out.println("t0 :"+t0 +", t1 :" + t1);
		}
		
		return new long[] {t0, r0};
		
	}
}
