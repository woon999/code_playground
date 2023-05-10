package programmers.week_challenge;

// week10 교점에 별 만들기 - math
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MakeStar {

	public static void main(String[] args) {
		int[][] line = {
				{2, -1, 4}, 
				{-2, -1, 4}, 
				{0, -1, 1},
				{5, -8, -12}, 
				{5, 8, 12}
		};
		int[][] line2 = {
				{0, 1, -1}, 
				{1, 0 ,-1}, 
				{1, 0 ,1},
		};
		int[][] line3 = {
				{1, -1, 0}, 
				{2, -1, 0}, 
				{4, -1 ,0},
		};
		new MakeStar().solution(line);
	}
	static Set<Pos> set;
	static int minX, minY, maxX, maxY;
	public String[] solution(int[][] line) {
        set = new HashSet<>();
        for(int i=0; i<line.length-1; i++) {
        	for(int j=i+1; j<line.length; j++) {
        		long a = line[i][0], b = line[i][1], e = line[i][2];
        		long c = line[j][0], d = line[j][1], f = line[j][2];
        		long v = (long)a*d-b*c;
        		if(v!=0) {
        			long xv = b*f-e*d;
        			long yv = e*c-a*f;
        			if(xv%v ==0 && yv%v ==0) {
    					set.add(new Pos((int)xv/(int)v, (int)yv/(int)v));
        			}
        		}
        	}
        }
        
        getBorderSize(set.size());
        int ySize = maxY-minY+1;
        String[] answer = new String[ySize];
        StringBuilder sb;
        for(int i=maxY, idx=0; i>=minY; i--) {
        	sb = new StringBuilder();
        	for(int j=minX; j<=maxX; j++) {
        		if(set.contains(new Pos(j,i))) {
        			sb.append("*");
        		}
        		else sb.append(".");
        	}
        	answer[idx++] = sb.toString();
        }
        return answer;
    }
	
	static void getBorderSize(int size) {
		List<Pos> posList = new ArrayList<>(set);
		
		// x
		Collections.sort(posList, new XComp());
        minX = posList.get(0).x; maxX = posList.get(size-1).x;
        
        // y
        Collections.sort(posList, new YComp());
        minY = posList.get(0).y; maxY = posList.get(size-1).y;
	}
	
	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
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
			Pos other = (Pos) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	static class XComp implements Comparator<Pos>{
		@Override
		public int compare(Pos o1, Pos o2) {
			return o1.x-o2.x;
		}
	}
	
	static class YComp implements Comparator<Pos>{
		@Override
		public int compare(Pos o1, Pos o2) {
			return o1.y-o2.y;
		}
	}
}
