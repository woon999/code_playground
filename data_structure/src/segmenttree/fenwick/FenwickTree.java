package segmenttree.fenwick;

import java.util.Arrays;

public class FenwickTree {
	static int[] tree;

	public FenwickTree(int size) {
		tree = new int[size+1];
	}

	long sum(int pos){
		long result = 0;
		while(pos > 0){
			result += tree[pos];
			pos &= (pos-1);
		}
		return result;
	}

	void add(int pos, int val){
		while(pos < tree.length){
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}

	int[] getTree(){
		return tree;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Arrays.stream(tree).forEach(s-> sb.append(s+" "));
		return sb.delete(0,2).toString();
	}
}
