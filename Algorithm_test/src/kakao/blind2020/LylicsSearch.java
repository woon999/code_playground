package kakao.blind2020;

//blind #4 가사 검색 - 정확성 100%, 효율성 2/5 통과 
import java.util.Arrays;

public class LylicsSearch {

	public static void main(String[] args) {
	
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		System.out.println(Arrays.toString(solution(words, queries)));
	}
	
	public static int[] solution(String[] words, String[] queries) {
        int wLen = words.length; 
        int qLen = queries.length;
        int[] answer = new int[qLen];
        
        for(int i=0; i<qLen ;i++) {
        	String query = queries[i];
        	int totalLen = query.length();
        	boolean flag = false;
        	
        	// ? 위치 접두사 true / 접미사 false
        	if(query.charAt(0)=='?') {
            	flag = true;
            }
//        	System.out.println("----------" +query+ "----------" );
        	query = query.replace("?", "");
        	int cutLen = query.length();
//        	System.out.println("len : "+ totalLen +", flag : " +flag+", restQuery : " + query + ", cutLen : " + cutLen);
        	
            int cnt=0;
            for(int j=0; j<wLen; j++) {
            	String w = words[j];
            	if(w.length()!=totalLen) continue;
            	if(flag) {
                	w = w.substring(w.length()-cutLen, w.length());
            	}else {
            		w = w.substring(0,cutLen);
            	}
            	if(query.equals(w)) {
            		++cnt;
//            		System.out.println(w + " , " + (++cnt));
            	}
            }
            answer[i]= cnt;
        }
        
        return answer;
    }
	
}
