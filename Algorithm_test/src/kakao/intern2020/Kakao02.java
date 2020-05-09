package kakao.intern2020;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//	"100-200*300-500+20"	60420
//	"50*6-3*2"	300

public class Kakao02 {

	public static void main(String[] args) {
		
		String s = "100-200*300-500+20";
		
		solution(s);
		
	}
	
    public static long solution(String expression) {
    	
    	Pattern p = Pattern.compile("(^[0-9]*$)");
    	
    	String regex = "[*],[+],-";
    	String[] s = expression.split(regex);
    	
    	Matcher m = p.matcher(expression);
    	
    	int num;
    	if(m.find()) {
    		num = Integer.parseInt(expression);
    		System.out.println(num);
    	}
    	
    	System.out.println(m);

    	System.out.println(s[0]);
        long answer = 0;
        return answer;
    }
}

