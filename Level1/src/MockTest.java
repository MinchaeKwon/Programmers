/**
 * Level 1 - 모의고사
 * https://programmers.co.kr/learn/courses/30/lessons/42840
 * 
 * @author minchae
 * @date 2021. 7. 29.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MockTest {
	public static void main(String[] args) {
		int[] a1 = {1, 2, 3, 4, 5};
		int[] a2 = {1, 3, 2, 4 ,2};
		
		System.out.println(Arrays.toString(solution(a1)));
		System.out.println(Arrays.toString(solution(a2)));
	}
	
	public static int[] solution(int[] answers) {
		// 수포자들이 문제를 찍는 패턴
		int[] first = {1, 2, 3, 4, 5};
		int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
		int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		
		int[] score = new int[3];
		
		// 수포자들이 맞추는 문제 개수를 계산
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == first[i % 5]) {
				score[0]++;
			}
			
			if (answers[i] == second[i % 8]) {
				score[1]++;
			}
			
			if (answers[i] == third[i % 10]) {
				score[2]++;
			}
		}
		
		// 가장 많은 문제를 맞춘 사람의 점수(가장 높은 점수)를 구함
		int max = Math.max(score[0], Math.max(score[1], score[2]));
		
		ArrayList<Integer> result = new ArrayList<>();
		
		// i가 0부터 시작하기 때문에 오름차순 정렬이 돼서 리스트에 추가 됨
		for (int i = 0; i < score.length; i++) {
			// 가장 높은 점수와 같은 점수인 사람(가장 많은 문제를 맞춘 사람)을 리스트에 추가
			if (score[i] == max) {
				result.add(i + 1);
			}
		}
		
        return result.stream().mapToInt(i -> i).toArray();
    }
}
