package programmers;

// programmers 멀쩡한 사각형 
public class FineSquare {

	public static void main(String[] args) {
		int w = 8;
		int h =12;
		
		System.out.println(solution(w,h));
	}
	
	public static long solution(int w, int h) {
        long answer = 0;

        // W ; a*gcd
        // H ; b*gcd
        // a x b 사각형이 gcd개 존재
        // a x b의 직선을 지나치는 사각형의 개수 : 1(default) +  (a-1) + (b-1) = a+b-1
        
        // 따라서 , W*H - (a+b-1)*gcd
        // 정리하면 W*H - W - H + gcd 가 된다.
        int gcd =0;
        if(w<h) gcd= gcd(w,h);
        else gcd = gcd(h,w);
        
        long total = (long)w*h;
        return total - w-h+gcd;
    }
	
	static int gcd(int a, int b) {
		if(b==0) {
			return a;
		}
		return gcd(b, a%b);
	}
}
