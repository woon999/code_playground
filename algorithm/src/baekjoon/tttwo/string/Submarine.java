package baekjoon.tttwo.string;

// #2671 string 잠수함식별 - 정규식 
import java.io.*;
import java.util.regex.Pattern;

public class Submarine {
	private static final Pattern pattern = Pattern.compile("(100+1+|01)+");

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		
		if(pattern.matcher(line).matches()) {
			System.out.println("SUBMARINE");
		}else {
			System.out.println("NOISE");
		}
		
		
	}
}
