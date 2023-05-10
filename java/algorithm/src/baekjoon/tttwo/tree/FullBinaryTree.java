package baekjoon.tttwo.tree;

// #3038 tree 완전 이진 트리 
import java.io.*;
import java.util.*;

public class FullBinaryTree {

	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println("----");
		dfs(1, 1, n);
		
	}
	
	static void dfs(int here, int h, int n) {
		if(h == n) {
			System.out.println("#" + here);
			return;
		}
		
		int startNum = (1<<n) - (1<<h)+1;
		int hOfnodes = (1<<(h-1));
		System.out.println(hOfnodes - here + startNum);
		
		dfs(here, h+1, n);
		dfs(here+(1<<h-1), h+1, n);
	}
}
