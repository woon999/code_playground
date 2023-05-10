package programmers.dev_matching;

// #3 다단계 칫솔 판매 
import java.util.HashMap;
import java.util.Map;

public class ToothBrushSelling {

	public static void main(String[] args) {
	
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		
		new ToothBrushSelling().solution(enroll, referral, seller, amount);
	}
	
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> member = new HashMap<>();
        int size=1;
        for(String s : enroll) {
        	member.put(s, size++);
        }
        
        int[] depth = new int[size];
        for(int i=0; i<referral.length; i++) {
        	String ref = referral[i];
        	String join = enroll[i];
        	int joinIdx = member.get(join);
        	if(!ref.equals("-")) {
        		int refIdx = member.get(ref);
        		depth[joinIdx]= depth[refIdx]+1;
        	}else {
        		depth[joinIdx] =1;
        	}
        }
        
        int[] earning = new int[size];
        for(int i=0; i<seller.length; i++) {
        	int sIdx = member.get(seller[i]);
        	int fee = amount[i]*100;
        	int cur = sIdx, pa = 0; 
        	int r = depth[sIdx];
        	earning[sIdx] += fee;
        	while(r>0) {
        		String parent = referral[cur-1];
        		
        		if(parent.equals("-")) pa =0;
        		else pa= member.get(parent);
        		
        		fee /= 10;
        		if(fee ==0 )break;
        		
				earning[pa] += fee;
				earning[cur] -= fee;
				cur = pa;
				r--;
        	}
        }
        
        
//        for(int num : earning) {
//        	System.out.print(num+" ");
//        }
        int[] answer = new int[size-1];
        for(int i=0; i<answer.length; i++) {
        	answer[i] = earning[i+1];
        }
        return answer;
    }
}
