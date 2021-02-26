package programmers;

// DFS/BFS #1 타겟 넘버 
public class TargetNumber {
	
//	1 1 1 1 1 
//	target 3 -> -1 1개  : 5C1 = 5  

	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		
		System.out.println(solution(numbers, target));
	}
	
	public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
	
	// dfs (return idx;) * n 번이 리턴되면 리턴 값이 idx x n 값으로 출력된다. 
	static int dfs(int[] num, int target, int idx, int pos) {
		if(idx == num.length) {
			if(pos == target) {
//				System.out.println(pos);
				return 1;
			}else return 0;
		}else {
			return dfs(num, target, idx+1, pos + num[idx])
				    + dfs(num, target, idx+1, pos - num[idx]);
		}
		

	}
	
}
