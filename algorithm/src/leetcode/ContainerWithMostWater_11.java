package leetcode;

// #11 - Container With Most Water

public class ContainerWithMostWater_11 {
	public static void main(String[] args) {
		int[] height = {1,8,6,2,5,4,8,3,7};

		System.out.println("maxArea = " + maxArea(height));
	}

	public static int maxArea(int[] height) {
		int s = 0;
		int e = height.length-1;

		int max = 0;
		while(s < e){
			int h = Math.min(height[s], height[e]);
			int width = (e-s) * h;
			max = Math.max(width, max);
			if(height[s] > height[e]){
				e--;
			}else{
				s++;
			}
		}

		return max;
	}
}
