package segmenttree.fenwick;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FenwickTreeTest {

	static final int SIZE = 8;
	static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
	static FenwickTree tree;

	@BeforeEach
	void before() {
		tree = new FenwickTree(SIZE);
		for (int i = 1; i <= SIZE; i++) {
			tree.add(i, arr[i - 1]);
		}
	}

	@Test
	public void fenwick_test() {
		assertEquals(tree.getTree()[SIZE], Arrays.stream(arr).sum());
	}

	@Test
	public void 구간업데이트_test() {
		// 3번(arr[2]) = 3 -> 10
		int idx = 3, val = 10;
		int nxt = idx+(idx & -idx);
		// test용 변수
		int initVal1 = tree.getTree()[idx], initVal2 = tree.getTree()[nxt];

		int dif = val - arr[idx-1];
		arr[idx-1] = val;
		tree.add(idx, dif);
		System.out.println(tree);

		assertEquals(tree.getTree()[idx], initVal1+dif);
		assertEquals(tree.getTree()[nxt], initVal2+dif);
	}

	@Test
	public void 구간합구하기_test() {
		int idx = 3;
		assertEquals(tree.sum(idx), 6);
		assertEquals(tree.sum(SIZE), Arrays.stream(arr).sum());
	}
}