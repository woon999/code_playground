package kakao.blind2021;


// blind #3 순위 검색 
import java.util.HashMap;
import java.util.Map;

public class RankSearch {
	public static void main(String[] args) {
		String[] info = {
				"java backend junior pizza 150",
				"python frontend senior chicken 210",
				"python frontend senior chicken 150",
				"cpp backend senior pizza 260",
				"java backend junior chicken 80",
				"python backend senior chicken 50"
		};
		
		String[] query = {
				"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150",
				"- and - and - and chicken 100",
				"- and - and - and - 150"
		};
		
		solution(info, query);
	}
	
	public static int[] solution(String[] info, String[] query) {
        int[] answer = {};
        
        Map<Result, Integer> map = new HashMap<>();
        
        		
        for(String s : info) {
        	String[] insert = s.split(" ");
        	
        	String in_lan = insert[0];
        	String in_part = insert[1];
        	String in_career = insert[2];
        	String in_food = insert[3];
        	int in_score = Integer.parseInt(insert[4]);
        	
        	new Result(in_lan, in_part, in_career, in_food, in_score);
        	for(String in : insert) {
        		System.out.print(in + " ");
        	}
        	System.out.println();
        }
        return answer;
    }
	
	

}

class Result{
	String language;
	String part;
	String career;
	String food;
	int score;
	
	public Result(String language, String part, String career, String food, int score) {
		this.language = language;
		this.part = part;
		this.career = career;
		this.food = food;
		this.score = score;
	}
	
	
}
