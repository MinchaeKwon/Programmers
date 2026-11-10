/**
 * Level 1 - 택배 상자 꺼내기
 * https://school.programmers.co.kr/learn/courses/30/lessons/389478
 * 
 * @author minchae
 * @date 2026. 11. 10.
 */

public class DeliveryBox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int solution(int n, int w, int num) {
        int h = (n / w) + 1; // 쌓는 높이
        int cur = 1; // 현재 쌓는 택배 상자 번호
        int x = 0, y = 0; // 꺼내야 하는 상자의 열, 행 좌표
        
        int[][] arr = new int[h][w];
        
        for (int i = 0; i < h; i++) {
            if (i % 2 == 0) {
                // 정방향으로 상자 놓기
                for (int j = 0; j < w; j++) {
                    if (cur > n) {
                        break;
                    }
                    
                    if (cur == num) {
                        x = i;
                        y = j;
                    }
                    
                    arr[i][j] = cur++;
                }
            } else {
                // 역방향으로 상자 놓기
                for (int j = w - 1; j >= 0; j--) {
                    if (cur > n) {
                        break;
                    }
                    
                    if (cur == num) {
                        x = i;
                        y = j;
                    }
                    
                    arr[i][j] = cur++;
                }
            }
        }
        
        int answer = 0;
        
        // 열만 확인
        for (int i = x; i < h; i++) {
            if (arr[i][y] > 0) {
                answer++;
            }
        }
        
        return answer;
    }

}
