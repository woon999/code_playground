package kakao.blind2020;

//blind #4 가사 검색 - Map, 정렬, 이진탐색 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LylicsSearch {
	static Map<Integer, ArrayList<String>> data,rData;
	static ArrayList<String> in,rIn;
	public static void main(String[] args) {
	
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		System.out.println(Arrays.toString(solution(words, queries)));
	}
	
	public static int[] solution(String[] words, String[] queries) {
        int wLen = words.length; 
        int qLen = queries.length;
        int[] answer = new int[qLen];
        
        // len, words 
        data = new HashMap<>();
        rData= new HashMap<>();
        for(int i=0; i<wLen ;i++) {
        	String word = words[i];
        	String reverseWord = reverseString(word);
        	int totalLen = word.length();
        	
        	if(!data.containsKey(totalLen)) {
        		in = new ArrayList<>();
        		in.add(word);
        		rIn = new ArrayList<>();
        		rIn.add(reverseWord);
        		data.put(totalLen, in);
        		rData.put(totalLen, rIn);
        	}else {
        		data.get(totalLen).add(word);
        		rData.get(totalLen).add(reverseWord);
        	}
        }
        
        // map에 저장된 word 오름차순 정렬 
        for(int len : data.keySet()) {
        	List<String> list = data.get(len);
        	List<String> rList = rData.get(len);
        	Collections.sort(list);
        	Collections.sort(rList);
        }
        
        for(int i=0; i<qLen; i++) {
        	String query = queries[i];
        	int totalLen = query.length();
        	
        	// ? 위치 접두사 false / 접미사 true
        	List<String> wordList;
        	if(query.charAt(0)=='?') {
        		wordList = rData.get(totalLen);
            	query = reverseString(query);
            }else {
            	wordList = data.get(totalLen);
            }
//        	System.out.println("----------" +query+ "----------" );
//        	System.out.println("len : "+ totalLen +", flag : " +flag+", restQuery : " + query );
        	
    		if(!data.containsKey(totalLen)) {
        		answer[i] = 0;
    			continue;
    		}
    		
            // 이진탐색 
    		int s=0,e=0;
    		String minQuery = query.replace('?', 'a');
    		String maxQuery = query.replace('?', 'z');
    		s = search(wordList, minQuery);
    		e = search(wordList, maxQuery);
//    		System.out.println(s+"~"+e);
    		answer[i] = e-s;
        }
        
        return answer;
    }
	
	static int search(List<String> wordList, String query) {
		int start =0, end =wordList.size();
		
		 while(start<end) {
         	int mid = (start+end)/2;
         	// query >= word
         	if(query.compareTo(wordList.get(mid))>=0) {
         		start =mid+1;
         	}else {
         		end = mid;
         	}
         }
		 return start;
	}
	
	static String reverseString(String s) {
		return new StringBuffer(s).reverse().toString();
	}
	
	
}
