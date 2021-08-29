package kakao.blind2019;

// blind #3 후보키 - 조합, 비트마스크

// 모든 조합을 뽑고 중복컬럼은 예외처리해서 후보키 후보를 뽑는 것까지 설계하였지만 
// 비트마스크로 부분집합을 예외처리해주는 부분은 생각해내지 못했다. 
// 저장된 답 {0}, {1,2}
// 새로운 후보 {1,2,3}
// 이렇게 있으면 그저 1,2가 현재 답안에 포함되니깐 제거해줬는데 부분집합에 포함되므로 예외시켜줘야했다.
// 실전이었으면 이거 얕게봤다가 큰 코 다쳤을 것 같다. 틀린 원인 : 설계 부족 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SubKey {

	static String[][] relations;
	static Stack<Integer> s;
	static List<Integer> ans;
	static boolean[] check;
	static int row, col;
	public static void main(String[] args) {
		String[][] relation = {
				{"100","ryan","music","2"},
				{"200","apeach","math","2"},
				{"300","tube","computer","3"},
				{"400","con","computer","4"},
				{"500","muzi","music","3"},
				{"600","apeach","music","2"}
		};
		
//		String[][] relation = {
//				{"100","ryan"},
//				{"200","apeach"},
//				{"300","tube"},
//				{"400","con"},
//				{"500","muzi"},
//				{"600","apeach"}
//		};
		
		System.out.println(solution(relation));
		
	}
	public static int solution(String[][] relation) {
        relations = relation;
        col = relation.length;
        row = relation[0].length;
        System.out.println(col + "," + row);
        
        ans = new ArrayList<>();
        for(int i=0; i<row; i++) {
        	s= new Stack<>();
            check= new boolean[row];
            System.out.println("#########");
            comb(0, i+1);
        }
        return ans.size();
    }
	
	static void comb(int pos, int r) {
		if(s.size() == r) {
			List<Integer> res = new ArrayList<>();
			for(int num : s) {
				res.add(num);
			}
			
			if(isSubKey(res)) {
				int cur =0;
				for(int num : res) {
					System.out.print(num +" ");
					cur |= 1<<(num);
				}
				System.out.println();
				
				if(!isSubSet(cur)) {
					ans.add(cur);	
				}
			}
			return;
		}
		for(int i=pos; i<row; i++) {
			if(!check[i]) {
				check[i] = true;
				s.push(i);
				comb(i,r);
				s.pop();
				check[i] = false;
			}
		}
	}
	
	static boolean isSubKey(List<Integer> rowList) {
		Set<String> set = new HashSet<>();
        for(int i=0; i<col; i++) {
        	String data = "";
        	for(int row : rowList) {
        		data += relations[i][row];
        	}
        	if(set.contains(data)) {
        		return false;
    		}
    		set.add(data);
        }
        return true;
	}
	
	static boolean isSubSet(int now) {
		for(int i=0; i<ans.size(); i++) {
			int ansData = ans.get(i);
			if((ansData & now) == ansData) return true;
		}
		return false;
		
	}
}
