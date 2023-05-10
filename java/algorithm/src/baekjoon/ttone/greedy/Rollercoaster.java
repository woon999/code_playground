package baekjoon.ttone.greedy;

// #2873 greedy 롤러코스터 - 구현 
import java.io.*;
import java.util.StringTokenizer;

public class Rollercoaster {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		// 둘 다 짝수 
		if((y%2==0) && (x%2==0)) {
			// 가장 작은 수 찾기
			int minX=0;
			int minY=0;
			int minV = Integer.MAX_VALUE;
			
			// (0,0) : 흰돌이라 할 때, (i,j) : i+j값이 홀수인 검은 돌 하나 생략 가능 
			for(int i=0; i<y; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<x; j++) {
					if((i+j)%2==1) { 
						int v = Integer.parseInt(st.nextToken());
						if(minV > v) {
							minV = v;
							minY = i;
							minX = j;
						}
					}else {
						st.nextToken();
					}
				}
			}
			sb.append(getGreedyZigZag(x, y, minX, minY));
		}
		else { // 모든 경로 탐색 가능  
			// 'ㄹ'자 탐색 
			if(y%2==1) { 
				sb.append(getZigZag('R','L','D',x,y));
			}
			// 'N'자 탐색 
			else {
				sb.append(getZigZag('D','U','R',y,x));
			}
		}
		System.out.println(sb.deleteCharAt(sb.length()-1).toString());
	}
	
	static StringBuilder getGreedyZigZag(int x, int y, int ex, int ey) {
		StringBuilder sb = new StringBuilder();
		int uy = ey/2*2;
		sb.append(getZigZag('R','L','D',x,uy));
		
		for(int i=0; i<ex; i++) {
			if(i%2==0) {
				sb.append("DR");
			}else {
				sb.append("UR");
			}
		}
		for(int i=ex; i<x-1; i++) {
			if(i%2==0) {
				sb.append("RD");
			}else {
				sb.append("RU");
			}
		}
		sb.append("D");
		sb.append(getZigZag('L' ,'R' ,'D' ,x ,y-uy-2));
		return sb;
	}
	
	static StringBuilder getZigZag(char f, char b, char d, int x, int y) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<y; i++) {
			for(int j=0; j<x-1; j++) {
				if(i%2==0) {
					sb.append(f);
				}else {
					sb.append(b);
				}
			}
			sb.append(d);
		}
		return sb;
	}
}




