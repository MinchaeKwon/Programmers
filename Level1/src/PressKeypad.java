/**
 * 키패드 누르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/67256
 * 
 * @author Minchae
 * @date 2023. 11. 24.
 */

public class PressKeypad {

	public static void main(String[] args) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";

		System.out.println(solution(numbers, hand));
	}
	
	public static String solution(int[] numbers, String hand) {
		// *, 0, #은 10, 11, 12로 표현
		int left = 10;
		int right = 12;
		
		 String answer = "";
		
		for (int n : numbers) {
			if (n == 1 || n == 4 || n == 7) {
				left = n;
				answer += "L";
			} else if (n == 3 || n == 6 || n == 9) {
				right = n;
				answer += "R";
			} else {
				if (n == 0) {
					n = 11;
				}
				
				// 해당 버튼과 왼쪽, 오른쪽 엄지 손가락 사이의 거리 구하기
				// 위아래로 차이 나는 값 + 좌우로 차이 나는 값
				int lDist = (Math.abs(n - left) / 3) + (Math.abs(n - left) % 3);
				int rDist = (Math.abs(n - right) / 3) + (Math.abs(n - right) % 3);
				
				if (lDist < rDist) {
					left = n;
					answer += "L";
				} else if (lDist > rDist) {
					right = n;
					answer += "R";
				} else {
					if (hand.equals("left")) {
						left = n;
						answer += "L";
					} else {
						right = n;
						answer += "R";
					}
				}
			}
		}
		
        return answer;
    }

}
