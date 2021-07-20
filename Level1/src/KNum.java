/**
 * K번째수
 * https://programmers.co.kr/learn/courses/30/lessons/42748
 * 
 * @author minchae
 * @date 2021. 7. 20.
 */

import java.util.Arrays;

public class KNum {

	public static void main(String[] args) {
		int[] a = {1, 5, 2, 6, 3, 7, 4};
		int[][] c = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		
		System.out.println(Arrays.toString(solution(a, c)));
	}
	
	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		
		for (int i = 0; i < commands.length; i++) {
			int[] slice = new int[commands[i][1] - commands[i][0] + 1];
			
			// i부터 j까지 잘라서 slice에 넣음
			for (int j = 0; j < slice.length; j++) {
				slice[j] = array[j + commands[i][0] - 1];
			}
			
			Arrays.sort(slice); // 오름차순 정렬
			answer[i] = slice[commands[i][2] - 1]; // K번째 수를 정답 배열에 넣음
		}
        
        return answer;
    }

}
