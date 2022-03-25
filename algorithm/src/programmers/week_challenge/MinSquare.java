package programmers.week_challenge;


// week_challenge 최소 직사각형 

import java.util.*;

public class MinSquare {

	public static void main(String[] args) {
		int[][] sizes = {
				{60, 50}, {30, 70}, 
				{60, 30}, {80, 40}
		};
		
		solution(sizes);
	} 
	static class Square{
		int x;
		int y;
		public Square(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int solution(int[][] sizes) {
        List<Square> squares = new ArrayList<>();
        int x,y;        
        for(int i=0; i<sizes.length; i++) {
        	x  = sizes[i][0];
    		y = sizes[i][1];
        	if(sizes[i][0] < sizes[i][1]) {
        		x  = sizes[i][1];
        		y = sizes[i][0];
        	}
			squares.add(new Square(x, y));
        }
        
        Collections.sort(squares, (a,b) -> b.x-a.x);
        
        int maxX = squares.get(0).x;
        Collections.sort(squares, (a,b) -> b.y-a.y);
        int maxY= squares.get(0).y;
        return maxX*maxY;
    }
	
	
}
