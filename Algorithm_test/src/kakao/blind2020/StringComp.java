package kakao.blind2020;

// blind #1 문자열 압축 - 문자열  

public class StringComp {

	public static void main(String[] args) {
		String s = "aabbaccc";
//		String s = "ababcdcdababcdcd";
		solution(s);
	}
	
	public static int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int len = s.length();
        
        if(s.length()==1) return 1;
        
        for(int r=1; r<=len/2; r++) {
        	String pattern  = s.substring(0,r);
        	int compLen =0;
        	int cnt =1;
//        	String reStr="";
        	for(int i=r; i<=s.length()-r; i+=r){
        		if(pattern.equals(s.substring(i,i+r))){
    				cnt++;
    			}else {
    				if(cnt>1) {
//    					reStr += cnt+"";
    					compLen += (int)Math.log10(cnt)+1;
    				}
//    				reStr += pattern;
    				pattern = s.substring(i,i+r);
    				compLen += pattern.length();
    				cnt=1;
    			}
        	}
        	
        	if(cnt>1) {
//    			reStr+= ""+cnt;
    			compLen += (int)Math.log10(cnt)+1;
    		}
//    		reStr+= pattern;
    		
    		int div = s.length()%r;
    		if(div>0) compLen += div;
    		compLen += pattern.length();
    		
//    		System.out.println(reStr + ", " + compLen);
    		answer = Math.min(answer, compLen);
        }
        
        System.out.println(answer);
        return answer;
    }
	
	
	
}
