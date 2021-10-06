package programmers.ttone;

// DFS/BFS #3 단어변환   
public class ChangeWord {

	static boolean[] checked;
	static int answer;
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		
		System.out.println(solution(begin, target, words));
		
	}
	
	public static int solution(String begin, String target, String[] words) {
        answer = 51;
        checked =new boolean[words.length];
        dfs(begin, target, words, 0);
        return answer == 51 ? 0 : answer;
    }
	
	static void dfs(String begin, String target, String[] words, int count) {
		
		if(begin.equals(target)) {
			answer = (answer > count) ? count : answer;
			return;
		}
		for(int i=0; i<words.length; i++) {
			if(!checked[i] && changeWord(begin, words[i])) {
				checked[i] = true;
				dfs(words[i], target, words, count+1);
				checked[i] = false;
			}
		}
	}
	
	static boolean changeWord(String begin, String change) {
		int count =0;
		for(int i=0; i< begin.length(); i++) {
			if(begin.charAt(i) != change.charAt(i)) {
				count++;
			}
		}
		return count == 1? true: false;
	}
}
