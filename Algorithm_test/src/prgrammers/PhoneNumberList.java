package prgrammers;


public class PhoneNumberList {

	public static void main(String[] args) {
		Solution3 s = new Solution3();
		
		String[] phone_book = {"12","123","1235","567","88"};
		
		System.out.println(s.solution(phone_book));
		
	}
}


class Solution3{
	
	// equals()나 startWith()의 메소드는 문자열을 다시 비교하는 로직이 포함되어있기 때문에 성능이 좋지않다.
	// 그래서 hashCode로 문자열을 int로 반환하여 비교하였다.
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        int start = 0;
       
        while(start< phone_book.length-1 ) {
        	
        	int hash = phone_book[start].hashCode();
        	
        	for(int i=start+1; i<phone_book.length; i++) {
            	if((phone_book[start].length() <= phone_book[i].length()) &&
            			hash == phone_book[i].substring(0,phone_book[start].length()).hashCode()) {
            		System.out.println("start : " + start +", i:" +i+ " ,1 :" +phone_book[start] +" ," + phone_book[i].substring(0,phone_book[start].length()));
            		answer = false;
            		break; // for문 break
            		
            	}else if((phone_book[start].length() > phone_book[i].length()) &&
            			phone_book[start].substring(0,phone_book[i].length()).hashCode()
            			== phone_book[i].hashCode()) {
            		System.out.println("2 : " +phone_book[start] +" ," + phone_book[i].substring(0,phone_book[start].length()));
            		answer = false;
            		break;
            		
            	}
            	
//            	while breaking not work -> different scopes
//            	if(!answer) break;
        	}
        	
        	if(!answer) break;
        	
        	start++;
        }
        	
        
        
        return answer;
    }
}