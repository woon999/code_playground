package baekjoon.ttzero.greedy;

// #5545
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Pizza {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] price = new int[2];

		for (int i = 0; i < 2; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		int dou_kcal = Integer.parseInt(br.readLine());

		int[] topping_kcal = new int[n];

		for (int i = 0; i < n; i++) {
			topping_kcal[i] = Integer.parseInt(br.readLine());
		}
		
		int t_price = price[0];
		int t_kcal = dou_kcal;
		int result = t_kcal/ t_price;
		
		
		int n_price = price[0];
		int n_kcal = dou_kcal;
		int n_result = n_kcal/ n_price;
	
		Arrays.sort(topping_kcal);
		
		for(int i=0; i<n; i++) {
			n_price += price[1];
			n_kcal += topping_kcal[n-1 -i];
			n_result = n_kcal / n_price;
			
			if(n_result >= result) {
				result = n_result;
				t_kcal = n_kcal;
				t_price = n_price;
			}else break;
		}
				
	System.out.println(result);
	}
}
