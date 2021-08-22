package kakao.blind2018.first;

//blind #2 뉴스 클러스터링 - 자료구조, 문자열 
import java.util.ArrayList;
import java.util.List;

public class NewsClustering {

	public static void main(String[] args) {
//		String str1 = "aa1+aa2";
//		String str2 = "AAAA12";
		
//		String str1 = "FRANCE";
//		String str2 = "french";
		String str1 = "handshake";
		String str2 = "shake hands";
		
		System.out.println(solution(str1, str2));
	}
	
	public static int solution(String str1, String str2) {
        List<String> l1 = getSubStr(str1.toUpperCase());
        List<String> l2 = getSubStr(str2.toUpperCase());
        
        List<String> mul = new ArrayList<>();
        List<String> sum = new ArrayList<>();
        for(String s1 : l1) {
        	if(l2.contains(s1)) {
        		l2.remove(s1);
    			mul.add(s1);
        	}
        	sum.add(s1);
        }
        
        for(String s2 : l2) {
        	sum.add(s2);
        }
        
        if(sum.size()==0) return 65536;
        double jac = (double)mul.size()/sum.size();
        
        jac = Math.floor(jac*65536);
        
        return (int)jac;
    }
	
	static List<String> getSubStr(String str) {
		// A ~ Z : 65 ~90
		List<String> list = new ArrayList<>();
        for(int i=0; i<str.length()-1; i++) {
        	char c1 = str.charAt(i);
        	char c2 = str.charAt(i+1);
        	
        	if((int)c1<65 || (int)c1>90) continue;
        	if((int)c2<65 || (int)c2>90) continue;
        	String s = str.substring(i,i+2);
        	list.add(s);
        }
        
        return list;
	}
}
