package segmenttree.fenwick;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FenwickTree_LazyTest {

	static final int SIZE = 8;
	static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
	static FenwickTree_Lazy tree;

	@BeforeEach
	void before() {
		tree = new FenwickTree_Lazy(SIZE);
		for (int i = 1; i <= SIZE; i++) {
			tree.rangeUpdate(i, SIZE, 1);
		}
	}

	@Test
	public void fenwick_test() {
		System.out.println(tree);
		assertEquals(tree.rangeSum(1,SIZE), Arrays.stream(arr).sum());
	}

	@Test
	public void 구간업데이트_test() {
		// [1, 3] +10
		int a = 1, b = 3, k = 10;
		tree.rangeUpdate(a, b, k);

		int[] update_arr = arr.clone();
		for(int i=a; i<=b; i++){
			update_arr[i] += k;
		}
		System.out.println(tree);

		assertEquals(tree.rangeSum(1,SIZE), Arrays.stream(update_arr).sum());
	}

	@Test
	public void 구간합_test() {
		// [1, 3] -> 6
		int a = 1, b = 3;

		assertEquals(tree.rangeSum(a, b), 6);
		assertEquals(tree.rangeSum(1,SIZE), Arrays.stream(arr).sum());
	}
}