package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxHeap {

	private List<Integer> heap;

	public MaxHeap() {
		heap = new ArrayList<>();
	}

	// 정수를 담는 최대 힙에 새 원소 삽입
	public void push(int val){
		heap.add(val);
		int idx =  heap.size()-1;

		// 루트에 도달하거나 새 원소 이상의 원소를 가진 조상을 만날 때 까지
		// idx 0부터 시작하므로 idx-1
		while(idx > 0 && heap.get((idx-1)/2) < heap.get(idx)){
			swap((idx-1)/2, idx, heap.get((idx-1)/2), heap.get(idx));
			idx = (idx-1)/2; // 부모로 이동
		}
	}

	private void swap(int to, int from, int a, int b) {
		int tmp = a;
		heap.set(to,  b);
		heap.set(from,  tmp);
	}

	public int pop(){
		if(heap.isEmpty()) return 0;

		int delete = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);

		int here = 0;
		while(true){
			int left = here * 2 + 1,  right = here * 2 + 2;

			// 리프에 도달한 경우
			if(left >= heap.size()) break;

			// heap[here]이 내려갈 위치를 찾는다.
			int next = here;

			// here vs 왼쪽 자식 노드
			if(heap.get(next) < heap.get(left)){
				next = left;
			}

			// 위 결과 vs 오른쪽 자식 노드
			if(right < heap.size() && heap.get(next) < heap.get(right)){
				next = right;
			}

			if(next == here) break;

			swap(here, next, heap.get(here), heap.get(next));
			here = next;
		}

		return delete;

	}

	@Override
	public String toString() {
		return "MaxHeap{" +
			"heap=" + heap +
			'}';
	}

	public static void main(String[] args) {
		MaxHeap heap = new MaxHeap();
		Random random = new Random();

		for(int i=0; i<100; i++){
			heap.push(random.nextInt(100));
		}

		System.out.println(heap);

		for(int i=0; i<100; i++){
			System.out.println(heap.pop());
		}
	}
}
