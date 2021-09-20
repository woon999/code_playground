package baekjoon.ttone.dataStructure;

// #17219 dataStructure 비밀번호찾기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FindPassword {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Map<String, String> keyMap = new HashMap<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
		
			String url = st.nextToken();
			String password = st.nextToken();
			keyMap.put(url, password);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			String findUrl = br.readLine();
			sb.append(keyMap.get(findUrl)+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
