/**
 * 카펫
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 * 
 * @author minchae
 * @date 2024. 1. 18.
 */

public class Carpet {

	public static void main(String[] args) {
		int brown = 10;
		int yellow = 2;
		
		int[] result = solution(brown, yellow);
		
		for (int n : result) {
			System.out.print(n + " ");
		}
	}
	
	public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        // 노란색 격자의 수가 1이상이기 때문에 세로의 길이는 3이상
        for (int height = 3; height < brown + yellow; height++) {
        	int width = (brown + yellow) / height;
        	
        	// 가로의 길이는 세로의 길이와 같거나 길어야 되기 때문에 조건문 사용
        	if (width >= height) {
        		// 노란색 격자의 크기가 yellow와 같은 경우
        		if ((width - 2) * (height - 2) == yellow) {
        			answer[0] = width;
        			answer[1] = height;
        			
        			break;
        		}
        	}
        }
        
        return answer;
    }

}
