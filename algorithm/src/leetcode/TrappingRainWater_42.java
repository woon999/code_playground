package leetcode;

// #42 - Trapping Rain Water
public class TrappingRainWater_42 {
	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

		System.out.println("trap(height) = " + trap(height));
	}

	public static int trap(int[] height) {
		int maxLeft = height[0], maxRight = height[height.length-1];
		int lp = 0, rp = height.length-1;

		int result = 0;
		while(lp <= rp){
			if(maxLeft <= maxRight){
				if(maxLeft < height[lp]){
					maxLeft = height[lp];
				} else {
					result += maxLeft - height[lp];
				}
				lp++;
			} else{
				if(maxRight < height[rp]){
					maxRight = height[rp];
				} else {
					result += maxRight - height[rp];
				}
				rp--;
			}
		}

		return result;
	}
}
