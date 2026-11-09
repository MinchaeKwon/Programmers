/**
 * Level 1 - 중요한 단어를 스포 방지
 * https://school.programmers.co.kr/learn/courses/30/lessons/468370
 * 
 * @author minchae
 * @date 2026. 11. 9.
 */

import java.util.*;

public class Spoiler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class Solution {
	    public int solution(String message, int[][] spoiler_ranges) {
	        String blurMessage = blurStr(message, spoiler_ranges);
	        
	        // 공백을 기준으로 단어 분리
	        String[] originWord = message.split(" ");
	        String[] blurWord = blurMessage.split(" ");
	        
	        int answer = getSecret(originWord, blurWord);
	        return answer;
	    }
	    
	    // 메시지 블러 처리
	    private static String blurStr(String message, int[][] spoiler_ranges) {
	        StringBuilder result = new StringBuilder(message);
	        
	        for (int[] range : spoiler_ranges) {
	            int start = range[0];
	            int end = range[1];
	            
	            for (int i = start; i <= end; i++) {
	                // 공백이 아닌 경우 블러 처리
	                if (message.charAt(i) != ' ') {
	                    result.setCharAt(i, '*');
	                }
	            }
	        }
	        
	        return result.toString();
	    }
	    
	    // 중요한 단어 개수 구하기
	    private static int getSecret(String[] originWord, String[] blurWord) {
	        HashSet<String> wordSet = new HashSet<>();
	        HashSet<String> spoilerSet = new HashSet<>();
	        
	        int len = originWord.length;
	        
	        for (int i = 0; i < len; i++) {
	            if (blurWord[i].contains("*")) { // 스포 방지가 포함되어 있는 경우
	                spoilerSet.add(originWord[i]);
	            } else { // 아닌 경우 일반 Set에 추가
	                wordSet.add(originWord[i]);
	            }
	        }
	        
	        spoilerSet.removeAll(wordSet); // 스포 방지 셋에서 일반 단어들을 제거
	        
	        return spoilerSet.size(); // 남은 건 중요한 단어들
	    }
	}

}
