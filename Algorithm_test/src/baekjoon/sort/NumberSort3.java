package baekjoon.sort;

import java.io.*;
import java.util.*;

//카운팅정렬 O(n)
public class NumberSort3 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] count = new int[10000];
        int check = 0;
        
        for(int i=0 ; i<n ; i++)
            count[Integer.parseInt(br.readLine())-1]++; 
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0 ; i<10000 ; i++) {
            for(int j=0 ; j<count[i] ; j++) {
            	check++;
                bw.write(Integer.toString(i+1) + "\n");
                
                if(check == n)break;
            }
        }
        bw.close();
        br.close();        
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		int n = Integer.parseInt(br.readLine());
//
//		// index가 해당 숫자, index의 value: index가 나온 횟수
//		int[] count_arr = new int[10001]; // 나온 숫자의 개수 count
//		int check = 0;
//
//		// 숫자 입력받기
//		for (int i = 0; i < n; i++) {
//			int temp = Integer.parseInt(br.readLine());
//			count_arr[temp] += 1; // 숫자가 나온 횟수 저장
//		}
//
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//		for (int i = 1; i <= 10000; i++) {
//			for (int j = 0; j < count_arr[i]; j++) {
//				check++;
//				bw.write(Integer.toString(i) + "\n");
//				// 모든 수를 출력했는데 for문 돌고 있을 경우 방지
//				if (check == n)
//					break;
//			}
//		}
//
//		br.close();
//		bw.close();
		
//		////////////////////////////////////////////////////////////////

//		Integer[] num = new Integer[n];
//		for (int i = 0; i < n; i++) {
//			num[i] = Integer.parseInt(br.readLine());
//		}
//		int m = Collections.max(Arrays.asList(num));
//		Integer[] cnt = new Integer[m + 1];
//		Arrays.fill(cnt, 0);
//		
//		
//		//각 원소 갯수 계산
//		for(int i = 0; i<n;i++) {
//			cnt[num[i]]++;
//		}
//		
//		//누적합
//		for(int i=1; i<cnt.length;i++) {
//			cnt[i]+=cnt[i-1];
//		}
//		
//		//누적합을 이용한 정렬
//		Integer[] res = new Integer[n];
//		for(int i=0; i<n;i++) {
//			res[--cnt[num[i]]] = num[i];
//		}
//		
//		for(int i=0; i<n;i++) {
//			bw.write(res[i]+"\n");
//		}

//		bw.flush();
//		bw.close();

	}

}
