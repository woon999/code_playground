package programmers.dev_matching;

// #1 - 로또와 최고 순위와 최저 순위 
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

	public static void main(String[] args) {
		
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums= {31, 10, 45, 1, 6, 19};
		
		System.out.println(Arrays.toString(new Lotto().solution(lottos, win_nums)));
	}
	
	public int[] solution(int[] lottos, int[] win_nums) {
        List<Integer> win_list = Arrays.stream(win_nums).boxed().collect(Collectors.toList());
        int zero=0;
        int match=0;
        for(int i=0; i<lottos.length; i++) {
        	if(lottos[i]==0) zero++;
        	else {
        		if(win_list.contains(lottos[i])) {
        			match++;
        		}
        	}
        }
//        System.out.println(match+"," + zero);
        int[] answer = {getRanking(match+zero), getRanking(match)};
        return answer;
    }
	
	static int getRanking(int num) {
		if(num ==6) return 1;
		else if(num ==5) return 2;
		else if(num ==4) return 3;
		else if(num ==3) return 4;
		else if(num ==2) return 5;
		else return 6;
		
	}
}
