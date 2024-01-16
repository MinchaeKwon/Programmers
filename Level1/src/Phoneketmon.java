/**
 * 폰켓몬
 * https://school.programmers.co.kr/learn/courses/30/lessons/1845
 * 
 * @author minchae
 * @date 2024. 1. 16.
 */

import java.util.HashSet;

public class Phoneketmon {

	public static void main(String[] args) {
		int[] nums = { 3, 1, 2, 3 };
		System.out.println(solution(nums));
	}

	public static int solution(int[] nums) {
		HashSet<Integer> hs = new HashSet<>();
		
		for (int n : nums) {
			hs.add(n);
		}

        return hs.size() > nums.length / 2 ? nums.length / 2 : hs.size();
    }

}
