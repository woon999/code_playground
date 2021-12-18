package baekjoon.ttone.dataStructure;

// #17299 자료구조 오동큰수 
import java.io.*;
import java.util.*;

public class BigNumber {

	static final int MAX = 1_000_001;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        long arr[] = new long[MAX];
        int index[] = new int[MAX];
        long ans[] = new long[n];
        
        StringTokenizer st =  new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[num]++;
            index[i] = num;
        }
        
        for(int i=0; i<n; i++) {
            if(stack.empty()) {
                stack.push(i);
            }
            while(!stack.empty() && arr[index[i]]>arr[index[stack.peek()]]) {
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
