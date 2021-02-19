package programmers;

// Sort #2 가장 큰 수  
import java.util.Arrays;
import java.util.Comparator;

public class BigNumber {

	public static void main(String[] args) {
//		int[] numbers = {3, 30, 34, 5, 9};
		int[] numbers = {0,0,0,0};
//		int[] numbers = {6, 10, 2};
		StringBuilder sb = new StringBuilder();
//		int a = 1;
//		int b =2;
//		
//		sb.append(a);
//		sb.append(b);
//		System.out.println(sb.toString());
		
		System.out.println(solution(numbers));
		
	}
	
	public static String solution(int[] numbers) {
        String answer = "";
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<numbers.length; i++) {
        	sb.append(numbers[i]);
        	if(i<numbers.length-1) sb.append(",");
        }
        String[] number = sb.toString().split(",");
        
//        for(String s : number) {
//        	System.out.println("length :" + s.length());
//        	System.out.println(Integer.parseInt(s));
//        }
        
        Arrays.sort(number, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int num1 = Integer.parseInt(o1+o2);
				int num2 = Integer.parseInt(o2+o1);
				System.out.println("num1 : "+ num1+ ", num2 : "+num2);
				// 30 3 -> 3 , 30 // 34 3 -> 34 3 
				return num2-num1;
			}
		});
        
        
        sb = new StringBuilder();
        for(String s : number) {
        	sb.append(s);
        }
        
        if(sb.toString().charAt(0) =='0') {
        	answer = "0";
        }else {
        	answer = sb.toString();
        }
        
        return answer;
    }
}




