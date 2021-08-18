/**
 * 상호 평가
 * https://programmers.co.kr/learn/courses/30/lessons/83201
 * 
 * @author minchae
 * @date 2021. 8. 18.
 */

public class Week02 {

	public static void main(String[] args) {
		int[][] s1 = {{100, 90, 98, 88, 65}, {50, 45, 99, 85, 77}, 
				{47, 88, 95, 80, 67}, {61, 57, 100, 80, 65}, {24, 90, 94, 75, 65}};
		int[][] s2 = {{50, 90}, {50, 87}};
		int[][] s3 = {{70, 49, 90}, {68, 50, 38}, {73, 31, 100}};
		
		System.out.println(solution(s1));
		System.out.println(solution(s2));
		System.out.println(solution(s3));
	}

	public static String solution(int[][] scores) {
		String answer = "";
		
		for (int i = 0; i < scores.length; i++) {
			int sum = 0;
			int len = scores.length;
			
			int max = -1;
			int min = 101;
			
			int myScore = scores[i][i]; // 자기 자신의 점수
			boolean isSame = false;
			
			for (int j = 0; j < scores.length; j++) {
				max = Math.max(max, scores[j][i]);
				min = Math.min(min, scores[j][i]);
				
				// 자기 자신과 똑같은 점수가 존재할 경우
				if (i != j && scores[j][i] == myScore) {
					isSame = true;
				}
				
				sum += scores[j][i];
			}
			
			// 자기 자신의 점수가 유일한 최고점 또는 최저점일 경우
			if (!isSame && (myScore == max || myScore == min)) {
				// 자신의 점수는 제외
				sum -= myScore;
				len--;
			}
			
			double avg = (double) sum / (double) len;
			answer += getGrade(avg);
		}
		
		return answer;
	}
	
	public static String getGrade(double score) {
		if (score >= 90) {
			return "A";
		}
		else if (score >= 80) {
			return "B";
		}
		else if (score >= 70) {
			return "C";
		}
		else if (score >= 50) {
			return "D";
		}
		else {
			return "F";
		}
	}

}
