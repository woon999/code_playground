package baekjoon.ttone.dataStructure;

// #1302 dataStructure 베스트셀러 - map 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestSeller {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			map.put(line, map.getOrDefault(line, 0)+1);
		}
		
		List<Integer> list = new ArrayList<>(map.values());
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		int max = list.get(0);
		
		List<String> ans = new ArrayList<>(map.keySet());
		Collections.sort(ans);
		for(String key : ans) {
			if(map.get(key)== max){
				System.out.println(key);
				break;
			}
		}
		
		
	}
}


