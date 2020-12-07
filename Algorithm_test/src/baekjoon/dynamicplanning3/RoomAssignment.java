package baekjoon.dynamicplanning3;

// #14697
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RoomAssignment {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int res = 0;
		
		for(int i=0; i<51; i++)
			for(int j=0; j<51; j++)
				for(int k=0; k<51; k++)
					if((a*i) + (b*j) + (c*k) ==n) res=1;
		
		System.out.println(res);
		
	}
}
