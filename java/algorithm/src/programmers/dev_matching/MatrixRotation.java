package programmers.dev_matching;

// #2 matrix 행렬 테두리 회전하기 
public class MatrixRotation {

	public static void main(String[] args) {
		int rows =6;
		int columns = 6;
		int[][] queries = {
				{2,2,5,4},
				{3,3,6,6},
				{5,1,6,3},
		};
		
//		int rows =3;
//		int columns = 3;
//		int[][] queries = {
//				{1,1,2,2},
//				{1,2,2,3},
//				{2,1,3,2},
//				{2,2,3,3},
//		};
		
//		int rows =100;
//		int columns = 97;
//		int[][] queries = {
//				{1,1,100,97},
//		};
		new MatrixRotation().solution(rows, columns, queries);
	}
	
	static int[] d= {1,1,-1,-1};
	static int[][] map;
	static int x,y;
	public int[] solution(int rows, int columns, int[][] queries) {
        x = columns;
        y = rows;
        map = new int[y][x];
        for(int i=0; i<y; i++) {
        	for(int j=0; j<x; j++) {
        		map[i][j] = i*x+j+1; // ?
        	}
        }
        
        print();
        int[] answer = new int[queries.length];
        for(int i=0; i<queries.length; i++)	{
        	int sy = queries[i][0]-1, sx = queries[i][1]-1;
        	int ey = queries[i][2]-1, ex = queries[i][3]-1;
//        	System.out.println(sx+","+sy +" ~ " + ex+","+ey);
        	answer[i] = rotate(sx,sy,ex,ey);
        }
        return answer;
    }
	
	static int rotate(int sx, int sy, int ex, int ey) {
		int min = Integer.MAX_VALUE;
		int px = sx, py =sy;
    	int tmp=map[py][px];
    	
    	for(int i=0; i<4; i++) {
			while(true) {
	    		int cur = tmp;
	    		int nx = px, ny =py;
	    		if(i%2==0) {
	    			nx = px +d[i];
	    		}else {
	    			ny = py +d[i];
	    		}
	    		if(nx<sx || nx>ex || ny<sy || ny>ey) break;
	    		tmp = map[ny][nx];
	    		map[ny][nx] = cur;
	    		px = nx;
	    		py = ny;
	    		min = Math.min(min, tmp);
	    	}
    	}
    	
//    	System.out.println(min);
    	return min;
	}
			
	
	static void print() {
		for(int i=0; i<y; i++) {
        	for(int j=0; j<x; j++) {
        		System.out.print(map[i][j] +" ");
        	}
        	System.out.println();
        }
		System.out.println("----");
	}
}
