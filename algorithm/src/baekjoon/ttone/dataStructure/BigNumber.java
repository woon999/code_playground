package baekjoon.ttone.dataStructure;

// #17299 dataStructrue 오등큰수 
import java.io.*;
import java.util.*;

public class BigNumber {

	static final int MAX = 1_000_001;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        
        int n = Integer.parseInt(br.readLine());
        int count[] = new int[MAX];
        int index[] = new int[MAX];
        int ans[] = new int[n];
        
        StringTokenizer st =  new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	index[i] = Integer.parseInt(st.nextToken());
            count[index[i]]++;
        }
        
        for(int i=0; i<n; i++) {
            while(!stack.empty() && count[index[i]]>count[index[stack.peek()]]) {
            	System.out.println(stack.peek() + " == " + index[i]);
                ans[stack.pop()] = index[i];
            }
            stack.push(i);
        }
        
        while(!stack.empty()) {
            ans[stack.pop()] = -1;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
        	sb.append(ans[i] + " ");
        }
        System.out.println(sb.toString());
	}
}
