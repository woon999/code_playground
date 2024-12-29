package leetcode;

// #389 - Find the Difference

public class FindTheDifference_389 {
    public static void main(String[] args) {
        String s = "abcde";
        String t = "abcd";

        System.out.println("findTheDifference(s, t) = " + findTheDifference(s, t));
    }

    public static char findTheDifference(String s, String t) {
        char answer = 0;
        for(char c : s.toCharArray()) answer ^= c;
        for(char c : t.toCharArray()) answer ^= c;
        return answer;
    }
}
