package programmers;

//그리디 #3 큰 수 만들기  

public class MakeBigNumber {

	public static void main(String[] args) {
//		String number = "1924";
//		String number = "1231234";
		String number = "23563850135";
		int k = 7;
		
		System.out.println(solution(number, k));
	}
	
	public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        // String[]	배열만들고 parseInt하면 시간초과 
        int idx = 0;
        int max = 0;
        for(int i=0; i<number.length()-k; i++) {
        	max =0;
        	System.out.println(i);
        	for(int j=idx; j<i+k+1; j++) {
        		int n = number.charAt(j) -'0';
//        		System.out.println(j);
        		if(max < n) {
        			System.out.println("max : " + max +  ", " + n +" ," + idx);
        			max = n;
        			idx = j+1; // max값 위치로 idx 변경 
        		}
        	}
        	sb.append(max);
        }
        
        return sb.toString();
    }
}
