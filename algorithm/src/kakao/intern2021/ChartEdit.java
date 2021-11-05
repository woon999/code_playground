package kakao.intern2021;

// #3 kakao2021intern 표 편집 - stack 
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class ChartEdit {

	static int cursor,chart_size;
	static Stack<Integer> dStack;
	public static void main(String[] args) {
//		int n = 8;
//		int k = 2;
//		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		
//		int n = 50;
//		int k = 12;
//		String[] cmd = {"D 12","C","U 3","C","D 4","C","U 2","Z","Z",
//				"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		
		int n = 8;
		int k = 2;
		String[] cmd = { "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		System.out.println(solution(n,k,cmd));
	}
	
	public static String solution(int n, int k, String[] cmd) {
		chart_size = n; cursor = k;
		dStack = new Stack<>();
		
		for(int i=0; i<cmd.length; i++) {
			String[] command_line = cmd[i].split(" ");
			char op = command_line[0].charAt(0);
			if(op=='D') {
				int opNum = Integer.parseInt(command_line[1]);
				down(opNum);
			}else if(op=='U') {
				int opNum = Integer.parseInt(command_line[1]);
				up(opNum);
			}else if(op=='C') {
				chart_size--;
				delete();
			}else {
				chart_size++;
				restore();
			}
		}
		StringBuilder answer= new StringBuilder();
		for(int i=0; i<chart_size; i++) {
			answer.append("O");
		}
		Collections.sort(dStack, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		while(!dStack.isEmpty()) {
			int num =dStack.pop().intValue();
			System.out.println(num);
			answer.insert(num, 'X');
		}
	
		return answer.toString();
	}
	static void down(int num) {
		cursor += num;
	}
	static void up(int num) {
		cursor -= num;
	}
	static void delete() {
		dStack.push(cursor);
		if(cursor== chart_size) cursor--;
	}
	static void restore() {
		int bNum = dStack.pop();
		if(cursor >= bNum) cursor++;
	}
}
