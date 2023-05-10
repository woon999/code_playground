package kakao.blind2021;


// blind #1 신규 아이디 추천
// 정규식, 문자열  (replaceAll)
// 효율성은 두개가 그닥 차이가 나지 않는다 
// 그러나 가벼운 연산은 1번이 살짝 더 효율적인듯 하지 코드 작성쪽에서는 replaceAll이 훨씬 편하다   

public class RecommendNewId2 {

	public static void main(String[] args) {
		String new_id = "abcdefghijklmn.p";
//		String new_id = "=.=";
		
		System.out.println(solution(new_id));
	}
	
    public static String solution(String new_id) {
        
    	String tmp = new_id.toLowerCase();
    	
    	System.out.println(tmp);
    	
    	
    	// 2단계 
    	tmp = tmp.replaceAll("[^-_.a-z0-9]", "");
    	System.out.println(tmp);
    	
    	// 3단계 
    	tmp = tmp.replaceAll("[.]{2,}", ".");
    	System.out.println(tmp);
    	
    	// 4단계 
    	tmp = tmp.replaceAll("^[.]|[.]$", "");
    	System.out.println(tmp);
    	
    	
    	// 5단계 
    	if(tmp.equals("")) tmp += "a";
    	if(tmp.length() > 15) {
    		tmp = tmp.substring(0,15);
    		tmp = tmp.replaceAll("^[.]|[.]$", "");
    	}
    	System.out.println(tmp);
    	
    	
    	if(tmp.length()<3) {
    		while(tmp.length()<3) {
    			tmp += tmp.charAt(tmp.length()-1);
    		}
    	}
    	System.out.println(tmp);
    	
    	return tmp;
        
    }
    
}
