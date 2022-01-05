package segmenttree.fenwick;

import java.util.Arrays;

public class FenwickTree_Lazy {
	int[] degreeTree;
	int[] constTree;

	public FenwickTree_Lazy(int size) {
		degreeTree = new int[size+1];
		constTree = new int[size+1];
	}

	void rangeUpdate(int l, int r, int val){
		add(degreeTree, l, val);
		add(degreeTree, r+1, -val);

		add(constTree, l, (-l+1)*val);
		add(constTree, r+1, r*val);
	}

	long rangeSum(int l, int r){
		long result = sum(degreeTree, r)*r + sum(constTree, r);
		return result -= sum(degreeTree, l-1)*(l-1) + sum(constTree, l-1);
	}

	long sum(int[] tree, int pos){
		long result = 0;
		while(pos > 0){
			result += tree[pos];
			pos &= (pos-1);
		}
		return result;
	}

	void add(int[] tree, int pos, int val){
		while(pos < tree.length){
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb1, sb2;
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
		Arrays.stream(degreeTree).forEach(s-> sb1.append(s+" "));
		sb1.delete(0,2);
		sb1.append("\n");
		Arrays.stream(constTree).forEach(s-> sb2.append(s+" "));
		sb2.delete(0,2);
		return sb1.toString()+sb2.toString();
	}

}
