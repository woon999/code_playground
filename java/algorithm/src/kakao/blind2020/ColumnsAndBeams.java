package kakao.blind2020;

//blind #5 기둥과 보 설치 - 구현 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnsAndBeams {
	static int size;
	static int[][] map;
	static List<Build> buildList;
	public static void main(String[] args){
		int n =5;
		int[][] build_frame = {
				{1,0,0,1},
				{1,1,1,1},
				{2,1,0,1},
				{2,2,1,1},
				{5,0,0,1},
				{5,1,0,1},
				{4,2,1,1},
				{3,2,1,1}
		};
		
		int[][] build_frame2 = {
				{0,0,0,1},
				{2,0,0,1},
				{4,0,0,1},
				{0,1,1,1},
				{1,1,1,1},
				{2,1,1,1},
				{3,1,1,1},
				{2,0,0,0},
				{1,1,1,0},
				{2,2,0,1}
		};
		
		solution(n, build_frame);
	}
	
	public static int[][] solution(int n, int[][] build_frame) {
        size = n;
        map = new int[n+1][n+1];
        
        buildList = new ArrayList<>();
        for(int i=0; i<build_frame.length; i++	) {
    		int x = build_frame[i][0];
    		int y = build_frame[i][1];
    		int type = build_frame[i][2];
    		int option = build_frame[i][3];
    		
//    		System.out.println("("+x+","+y +") :" + type);
    		if(option==1) {
    			if(canBuild(x,y,type)) {
//    				System.out.println("add --- ("+x+","+y +") :" + type);
    				buildList.add(new Build(x,y,type));
    			}
    		}else {
    			buildList.remove(new Build(x,y,type));
    			if(!isSustainable(buildList)) {
    				buildList.add(new Build(x,y,type));
    			}
//    			else {
//    				System.out.println("remove --("+x+","+y +") :" + type);
//    			}
    		}
        }
        
//        System.out.println("------------------------");
        Collections.sort(buildList);
        int[][] answer = new int[buildList.size()][3];
        int idx=0;
        for(Build b : buildList) {
//        	System.out.println("("+b.x+","+b.y +") :" + b.type);
//        	map[b.y][b.x] = b.type;
        	answer[idx][0] = b.x;
        	answer[idx][1] = b.y;
        	answer[idx++][2] = b.type;
        }
//        print();
        
        return answer;
    }
	static boolean isSustainable(List<Build> list) {
		for(Build b : list) {
			if(!canBuild(b.x, b.y, b.type)) return false;
		}
		return true;
		
	}
	static boolean canBuild(int x, int y, int type) {
		// 기둥 
		if(type ==0) {
			// 바닥 | 보 한쪽 끝 | 기둥 위  
			if(y==0 || buildList.contains(new Build(x,y-1,0)) || 
					buildList.contains(new Build(x-1,y,1)) ||
					buildList.contains(new Build(x,y,1))){
				return true;
			}
		}
		// 보 
		else {
			// 한쪽 끝 부분이 기둥 위 | 양쪽 끝 부분 다른 보 
			if(buildList.contains(new Build(x,y-1,0)) ||  
					buildList.contains(new Build(x+1,y-1,0)) ||
					(buildList.contains(new Build(x-1,y,1)))&& 
							buildList.contains(new Build(x+1,y,1))){
					return true;
			}
		}
		return false;
	}
	
	static void print() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
	}
}

class Build implements Comparable<Build>{
	int x;
	int y;
	int type;
	
	public Build(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type =type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Build other = (Build) obj;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Build o) {
		if(this.x > o.x) {
			return 1;
		}else if(this.x == o.x){
			if(this.y > o.y) {
				return 1;
			}
			else if(this.y == o.y) {
				return this.type - o.type;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}
}
