package baekjoon.tttwo.string;

// #1593 str 문자 해독 - 슬라이딩 윈도우 
import java.io.*;
import java.util.*;

public class WordDecoding {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		
		String w = br.readLine();
		String s = br.readLine();
		
		
		int[] wArr = new int[52];
		int[] sArr = new int[52];
		
		for(char c : w.toCharArray()) {
			putWord(c, wArr, 1);
		}
		
		int start = 0;
		int cnt = 0;
		int size = 0;
		for(int i=0; i<s.length(); i++) {
			char sc = s.charAt(i);
			putWord(sc, sArr, 1);
			size++;
			
			if(size == w.length()) {
				if(Arrays.equals(wArr, sArr)) {
					cnt++;
				}
				putWord(s.charAt(start), sArr, -1);
				start++;
				size--;
			}
		}
		System.out.println(cnt);
	}

	static void putWord(char word, int[] arr, int dif) {
		if('a' <= word && word <= 'z') {
			arr[word-'a']+= dif;
		}else {
			arr[word-'A'+26]+= dif;
		}
	}
}
