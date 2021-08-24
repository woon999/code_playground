package kakao.blind2018.first;

//blind #1 비밀지도 - 자료구조, 배열 
import java.util.Arrays;

public interface SecretMap {
	
	public static void main(String[] args) {
//		int n = 5;
//		int[] arr1 = {9, 20, 28, 18, 11};
//		int[] arr2 = {30, 1, 21, 17, 28};
		
		int n = 6;
		int[] arr1 = {46, 33, 33 ,22, 31, 50};
		int[] arr2 = {27 ,56, 19, 14, 14, 10};
		
		System.out.println(Arrays.toString(solution(n, arr1, arr2)));
	}
	
	public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
        	int sum  = arr1[i]|arr2[i];
        	String line = decodeMap(sum, n);
        	answer[i] = line;
        }
        
        return answer;
    }
	
	static String decodeMap(int num, int size) {
		String iToB = Integer.toBinaryString(num);
    	
    	while(iToB.length()<size) {
    		iToB = "0"+iToB;
    	}
    	
    	String build = "";
    	for(int i=0; i<size; i++) {
    		if(iToB.charAt(i) =='1') {
    			build +="#";
    		}else {
    			build +=" ";
    		}
    	}
    	return build;
	}
}
