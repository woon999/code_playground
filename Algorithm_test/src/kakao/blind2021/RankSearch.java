package kakao.blind2021;


// blind #3 순위 검색  (효율성0점...) 
import java.util.*;


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
		
		System.out.println(Arrays.toString(solution(info, query)));
	}
	
	public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        // 
        for(int i=0; i<query.length; i++) {
        	query[i] = query[i].replaceAll(" and", "");
        }

        List<Participant> input = new ArrayList<>();
        List<Participant> question = new ArrayList<>();
        
        input = inputData(info);
        question = inputData(query);
        
        Collections.sort(input, new Comparator<Participant>() {

			@Override
			public int compare(Participant o1, Participant o2) {
				// TODO Auto-generated method stub
				return o2.score - o1.score;
			}
		});
        for(Participant r : input) {
    		System.out.println(r.language+", "+ r.part +", " + r.career +", " +r.food+", "+r.score);
    	}
        
        for(int i=0; i<question.size(); i++) {
        	Participant res = question.get(i);
        	int count = 0;

        	for(Participant data : input) {
//        		System.out.println(res.language+", "+ res.part +", " + res.career +", " +res.food+", "+res.score);
        		if(res.score > data.score) {
//        			System.out.println("scpre 땡 "+ data.score);
        			continue;
        		}
        		if(!res.language.equals("-") && !res.language.equals(data.language)) {
//        			System.out.println("lan 땡 "+ data.language);
        			continue;
        		}
        		if(!res.part.equals("-") && !res.part.equals(data.part)) {
//        			System.out.println("part 땡 "+ data.part);
        			continue;
        		}
        		if(!res.career.equals("-") && !res.career.equals(data.career)) {
//        			System.out.println("career 땡 "+ data.career);
        			continue;
        		}
        		if(!res.food.equals("-") && !res.food.equals(data.food)) {
//        			System.out.println("food 땡 "+ data.food);
        			continue;
        		}
        		
        		count++;
        		
        	}
        	answer[i] = count;
        	System.out.println("데이터 수 : " + count);
        }

      
        return answer;
    }
	
	static List<Participant> inputData(String[] arr) {
		List<Participant> list = new ArrayList<>();
		for(String s : arr) {
        	String[] insert = s.split(" ");        
       
        	list.add(new Participant(insert[0], insert[1], insert[2], insert[3], Integer.parseInt(insert[4])));
        }
//        for(Participant r : list) {
//    		System.out.println(r.language+", "+ r.part +", " + r.career +", " +r.food+", "+r.score);
//    	}
        
		return list;
	}
	
	

}

class Participant {
	String language;
	String part;
	String career;
	String food;
	int score;
	
	public Participant(String language, String part, String career, String food, int score) {
		this.language = language;
		this.part = part;
		this.career = career;
		this.food = food;
		this.score = score;
	}
	
	
}
