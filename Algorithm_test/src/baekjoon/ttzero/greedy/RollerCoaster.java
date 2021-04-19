package baekjoon.ttzero.greedy;

import java.io.*;
import java.util.*;

// #2873
public class RollerCoaster {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        if((r & 1) == 0 && (c & 1) == 0) {
            int minI = 0;
            int minJ = 0;
            int min = Integer.MAX_VALUE;
            
            for(int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                
                for(int j = 0; j < c; j++) {
                    if(((i + j) & 1) == 1) {
                        int n = Integer.parseInt(st.nextToken());
                        
                        if(min > n) {
                            min = n;
                            minI = i;
                            minJ = j;
                        }
                    } else {
                        st.nextToken();
                    }
                }
            }
            
            System.out.println(solve(r, c, minI, minJ));
        } else {
            for(int i = 0; i < r; i++) {
                br.readLine();
            }
            
            if((r & 1) == 1) {
                System.out.println(getZigZag('R', 'L', 'D', c, r).deleteCharAt(r * c - 1));
            } else {
                System.out.println(getZigZag('D', 'U', 'R', r, c).deleteCharAt(r * c - 1));
            }
        }
    }
 
    private static String solve(int r, int c, int minI, int minJ) {
        StringBuffer sb = new StringBuffer();
        int tmp = minI / 2 * 2;        
        sb.append(getZigZag('R', 'L', 'D', c, tmp));
        
        for(int i = 0; i < minJ; i++) {
            if((i & 1) == 0 ) {
                sb.append("DR");
            } else {
                sb.append("UR");
            }
        }
        
        for(int i = minJ; i < c - 1; i++) {
            if((i & 1) == 0 ) {
                sb.append("RD");
            } else {
                sb.append("RU");
            }
        }
        
        sb.append('D');
        sb.append(getZigZag('L', 'R', 'D', c, r - tmp - 2));
        
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
 
    private static StringBuffer getZigZag(char f, char b, char d, int c, int r) {
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c - 1; j++) {
                if((i & 1) == 0) {
                    sb.append(f);
                } else {
                    sb.append(b);
                }
            }
            
            sb.append(d);
        }
        
        return sb;
    }
}
