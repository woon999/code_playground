package kakao.blind2020;

// blind #3 자물쇠와 열쇠 - 그래프, 시뮬레이션 

public class KeyAndLock {

	static int n,m;
	public static void main(String[] args) {
		int[][] key = {
				{0,0,0},
				{1,0,0},
				{0,1,1}
				
		};
		
		int[][] lock = {
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,0},
				{1,1,0,1}
		};
		
		solution(key,lock);
	}
	
	public static boolean solution(int[][] key, int[][] lock) {
        n = lock.length; // NxN
        m = key.length; // MxM
        
        int t =0;
        int[][] tmp = getCopyKey(key);
        while(t<4) {
        	print(lock);
        	int[][] exKey = extendKeyToLock(tmp);
        	print(exKey);
        	boolean res = matchingKeyToLock(exKey,lock);
        	System.out.println(res);
        	if(res) return true;
        	tmp = turnKey(tmp);
        	
        	t++;
        }
        return false;
    }
	
	static boolean matchingKeyToLock(int[][] key, int[][] lock) {
		for(int i=0; i<m+n-1; i++) {
			for(int j=0; j<m+n-1; j++) {
				boolean flag = true;
				out:
				for(int y=0; y<n; y++) {
					for(int x=0; x<n; x++) {
						if(lock[y][x] == key[i+y][j+x]) {
//							System.out.println("conflict");
							flag = false;
							break out;
						}
					}
				}
				if(flag) return true;
			}
		}
		return false;
	}
	
	static int[][] extendKeyToLock(int[][] src){
		int[][] extendKey = new int[m+2*(n-1)][m+2*(n-1)];
		for(int i=0; i<m; i++) {
			for(int j=0; j<m; j++) {
				extendKey[i+n-1][j+n-1] = src[i][j];
			}
		}
		return extendKey;
	}
	
	
	static int[][] turnKey(int[][] src) {
		int[][] trunKey = new int[m][m];
		int y = m-1;
		for(int i=0; i<m; i++) { // y
			for(int j=0; j<m; j++) { // x
				trunKey[j][y] = src[i][j];
//				System.out.print("x : " +j +" -> " + (y) +" , "); //x
//				System.out.println("y : " +i +" -> " + (j)); //y
			}
			y--;
		}
		return trunKey;
	}
	


	
	static int[][] getCopyKey(int[][] src) {
		int[][] copy = new int[m][m];
		for(int i=0; i<m; i++) {
        	for(int j=0; j<m; j++) {
        		copy[i][j] = src[i][j];
        	}
        }
		return copy;
	}
	
	static void print(int[][] src) {
		for(int i=0; i<src.length; i++) {
        	for(int j=0; j<src[i].length; j++) {
        		System.out.print(src[i][j]+" ");
        	}
        	System.out.println();
        }
        System.out.println();
	}
	
}
