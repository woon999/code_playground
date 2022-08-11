package leetcode;

// #118 - Pascal's Triangle
import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle_118 {
	public static void main(String[] args) {
		int numRows = 5;
		System.out.println("generate(numRows) = " + generate(numRows));
	}

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> row = new ArrayList<>(numRows);
		for(int i=0; i<numRows; i++){
			if(i==0){
				List<Integer> elem = new ArrayList<>(1);
				elem.add(1);
				row.add(elem);
			}else{
				List<Integer> elems = new ArrayList<>(i+1);
				for(int j=0; j<i+1; j++){
					if (j==0 || j==i) elems.add(1);
					else elems.add(0);
				}
				row.add(elems);
 				if(i < 2) continue;

				for(int j=1; j<i; j++){
					row.get(i).set(j, row.get(i-1).get(j-1) + row.get(i-1).get(j));
				}
			}
		}
		return row;
	}
}
