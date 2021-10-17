/**
 * 최소직사각형
 * https://programmers.co.kr/learn/courses/30/lessons/86491
 * 
 * @author minchae
 * @date 2021. 10. 17.
 */

public class Week08 {

	public static void main(String[] args) {
		int[][] s = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
		
		System.out.println(solution(s));
	}
	
	public static int solution(int[][] sizes) {
		int width = 0;
		int height = 0;
		
		for (int[] size : sizes) {
			// 명함 가로, 세로 중에서 더 긴 것을 가로로 하고 짧은 것을 세로로 설정
			// 그 중에서 각각 최댓값을 찾음
			width = Math.max(width, Math.max(size[0], size[1]));
			height = Math.max(height, Math.min(size[0], size[1]));
		}
		
        return width * height;
    }

}
