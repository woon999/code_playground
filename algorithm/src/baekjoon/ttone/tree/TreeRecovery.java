package baekjoon.ttone.tree;

// #6597 tree 트리 복구 
import java.io.*;
import java.util.StringTokenizer;

public class TreeRecovery {

	static int[] pre, in, inIdx;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input;
		StringTokenizer st;
		try {
			while(true) {
				input = br.readLine();
				if(input.split(" ").length ==0) break;
				st = new StringTokenizer(input);
				String preOrder = st.nextToken();
				String inOrder = st.nextToken();
				
				int size = preOrder.length();
				pre = new int[size];
				in = new int[size];
				inIdx = new int[size];
				
				for(int i=0; i<size; i++) {
					pre[i] = preOrder.charAt(i)-'A';
					in[i] = inOrder.charAt(i)-'A';
					
				}
				
				for(int i=0; i<size; i++) {
					inIdx[in[i]] = i;
				}
					
				recover(0, 0, size);
				sb.append("\n");
			}
		} catch(Exception e) {
			
		}
		System.out.println(sb.toString());
	}
	
	static void recover(int idx, int left, int right) {
		int root = pre[idx];
		int rootIdx = inIdx[root];
		
		recover(idx+1, left, rootIdx);
		recover(idx+1+(rootIdx-left), rootIdx+1, right);
		sb.append((char)(root+'A'));
		
		
	}
}
