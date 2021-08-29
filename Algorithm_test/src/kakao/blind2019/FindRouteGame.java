package kakao.blind2019;

import java.util.ArrayList;
import java.util.List;

public class FindRouteGame {

	public static void main(String[] args) {
		int[][] nodeinfo = {
			{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}
		};
		
		solution(nodeinfo);
	}
	
	public static int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        int size = nodeinfo.length;
        System.out.println(size);
        
        List<Integer>[] list = new ArrayList[size];
        return answer;
    }
}
