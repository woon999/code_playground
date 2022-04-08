package baekjoon.tttwo.suffixarray;

// #11656 suffixarray 접미사 배열 
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuffixArray {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		
		List<String> suffix = getSuffixArray(text);
		
		for(String s : suffix) {
			System.out.println(s);
		}
	}
	
	static List<String> getSuffixArray(String text){
		int n  = text.length();
		
		List<String> perm = new ArrayList<>();
		for(int i=0; i<n; i++) {
			perm.add(text.substring(i, text.length()));
		}
		
		Collections.sort(perm);
		return perm;
	}
}
