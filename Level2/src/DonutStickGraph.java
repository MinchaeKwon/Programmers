/**
 * 도넛과 막대 그래프
 * https://school.programmers.co.kr/learn/courses/30/lessons/258711
 * 
 * @author minchae
 * @date 2024. 3. 23.
 */

public class DonutStickGraph {
	
	static int[][] exchangeCnt;

	public static void main(String[] args) {
		 int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
//	      int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3,8}};

		 int[] result = solution(edges);
		 
		 for (int n : result) {
			 System.out.print(n + " ");
		 }
	}
	
	public static int[] solution(int[][] edges) {
		int max = 0;
		
		for (int[] edge : edges) {
			max = Math.max(max, Math.max(edge[0], edge[1]));
		}
		
		exchangeCnt = new int[max + 1][2];
		
		for (int[] edge : edges) {
			int a = edge[0];
			int b = edge[1];
			
			exchangeCnt[a][0]++;
			exchangeCnt[b][1]++;
		}
		
		int[] answer = new int[4];
		
		for (int i = 1; i < exchangeCnt.length; i++) {
			if (exchangeCnt[i][0] >= 2 && exchangeCnt[i][1] == 0) {
				answer[0] = i;
			} else if (exchangeCnt[i][0] == 0 && exchangeCnt[i][1] > 0) {
				answer[2]++;
			} else if (exchangeCnt[i][0] >= 2 && exchangeCnt[i][1] >= 2) {
				answer[3]++;
			}
	   
		}
		
	    answer[1] = (exchangeCnt[answer[0]][0] - answer[2] - answer[3]);
	    
        return answer;
    }

}
