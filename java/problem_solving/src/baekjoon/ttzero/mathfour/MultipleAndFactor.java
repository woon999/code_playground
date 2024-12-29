package baekjoon.ttzero.mathfour;

// #5086
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MultipleAndFactor {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			if( first ==0 && second ==0)break;
			
			if(second % first == 0) {
				System.out.println("factor");
			}
			else if(first % second ==0 ) {
				System.out.println("multiple");
			}
			else {
				System.out.println("neither");
			}
		}
	}
}
