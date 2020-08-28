package baekjoon.MinimumSpanningTree;

// #9372 최소 스패닝트리 v-1 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Travel {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<e; j++) {
				br.readLine();
			}
			
			sb.append(v-1+" \n");
		}
		
		System.out.println(sb.toString());
	}
 }
