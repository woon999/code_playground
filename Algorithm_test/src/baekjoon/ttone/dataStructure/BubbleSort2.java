package baekjoon.ttone.dataStructure;

// #1517 dataStructure (1517. 버블 소트) - 합병 정렬  
import java.io.*;
import java.util.StringTokenizer;

public class BubbleSort2 {

	static long ans;
	static long[] elements, sorted;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		elements = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			elements[i] = Integer.parseInt(st.nextToken());
		}
		
		sorted = new long[n];
		
		divide(0,n-1);
		System.out.println(ans);
		
		
	}
	static void divide(int low, int high) {
		
		if(low < high) {
			int mid = (low+high)/2;
			
			divide(low, mid);
			divide(mid+1, high);
			
			merge(low, mid, high);
			
		}
	}
	
	static void merge(int low, int mid, int high) {
		int i = low;  // 왼쪽 시작 인덱스 
		int j=  mid+1; // 오른쪽 시작 인덱스 
		int k = low; // sorted배열 인덱스 
		
		while(i<= mid && j <= high) {
			if(elements[i] <= elements[j]){
				sorted[k] = elements[i++];
			}else { // elements[i] > elements[j] 앞에 자신보다 큰 수 있는 경우
				sorted[k] = elements[j++];
				ans += (mid+1-i);  // 연산횟수 카운트
			}
			k++;
		}
		
		// 나머지 합병과정 처리
		while(i <= mid) {
			sorted[k] = elements[i];
			i++;
			k++;
		}
		while(j <= high) {
			sorted[k] = elements[j];
			j++;
			k++;
		}
		
		for(int m=low; m<high+1; m++) {
			elements[m] = sorted[m];
		}
		
	}
	
}


