package baekjoon.ttzero.greedy;

// #13305
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GasStation {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long[] dis = new long[n-1];
		long[] fuel = new long[n];
		
		for(int i=0; i<n-1;i++) {
			int d = Integer.parseInt(st.nextToken());
			dis[i] = d;
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) {
			int f = Integer.parseInt(st.nextToken());
			fuel[i] = f;
		}
		
		long price = dis[0] * fuel[0];
		int now = 0;
		int next = now +1;
		
		while(next <n-1) {
			if(fuel[now] < fuel[next]) {
				price += fuel[now] * dis[next];
			}
			else {
				price += fuel[next] * dis[next];
				now = next;
			}
			
			next++;
		}
		
		System.out.println(price);
	
		
	}
}
