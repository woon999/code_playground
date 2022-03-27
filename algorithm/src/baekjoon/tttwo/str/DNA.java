package baekjoon.tttwo.str;

// #1969 str DNA 
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DNA {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] dnas = new char[n][m];
		for (int i = 0; i < n; i++) {
			dnas[i] = br.readLine().toCharArray();
		}

		StringBuilder sb = new StringBuilder();
		int hamDis = 0;
		Map<Character, Integer> map = new TreeMap<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				map.put(dnas[j][i], map.getOrDefault(dnas[j][i], 0) + 1);
			}
			
			Entry<Character, Integer> oneOfDnas = getFirstResult(map);
			sb.append(oneOfDnas.getKey());
			hamDis += n-oneOfDnas.getValue();
			map.clear();
		}
		

		System.out.println(sb.toString());
		System.out.println(hamDis);
	}

	private static Entry<Character, Integer> getFirstResult(Map<Character, Integer> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue((a,b)->(b-a))).collect(Collectors.toList()).get(0);
	}
}
