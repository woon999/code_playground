package baekjoon.ttone.sort;

// #11004 sort K번째 수 - 합병정렬  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KNumber {

	static int[] arr, tmp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		tmp = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(0,n-1);
		System.out.println(arr[k-1]);
	}
	
	static void mergeSort(int left, int right) {
		if(left >= right) return;
		
		int mid = (left+right)/2;
		mergeSort(left, mid);
		mergeSort(mid+1, right);
		
		merge(left, mid, right);
	}
	
	static void merge(int left, int mid, int right) {
		int l = left;
		int r = mid+1;
		int idx = l;
		
		while(l <= mid || r <= right) {
			
			// 1. 오른쪽 분할의 원소를 이미 다 가져온 경우
			// 2. 왼쪽 분할에서 가져오지 않은 원소가 있고, 해당 원소(l)가 오른쪽 분할 원소(r)보다 작은 경우 
			if(r>right || (l<=mid && arr[l]<arr[r])) {
				tmp[idx++] = arr[l++];
			}else { // 위에 해당하지않으면 오른쪽 분할에서 원소를 가져온다.
				tmp[idx++] = arr[r++];
			}
		}
		
		// left~right구간 정렬한 부분을 원래 배열 arr에 저장한다.
		for(int i=left; i<=right; i++) {
			arr[i] = tmp[i];
		}
	}
	
	static void print() {
		for(int num : arr) {
			System.out.print(num+" ");
		}
		System.out.println();
	}
}
