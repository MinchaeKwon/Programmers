/**
 * 후보키
 * https://programmers.co.kr/learn/courses/30/lessons/42890
 * 
 * @author Minchae Gwon
 * @date 2021.1.11
 * 
 * 릴레이션을 나타내는 문자열 배열 relation이 매개변수로 주어질 때, 이 릴레이션에서 후보 키의 개수를 return 하도록 solution 함수를 완성하라.
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Candidatekey {
	
	static ArrayList<HashSet<Integer>> candidateKey;
	
    public static int solution(String[][] relation) {
    	candidateKey = new ArrayList<>();
    	int col = relation[0].length;
    	
    	//후보키가 될 수 있는 모든 조합을 구하면서 최소성과 유일성을 확인함
    	for (int i = 1; i <= col; i++) {
    		findCandidate(0, 0, i, col, new HashSet<Integer>(), relation); //i는 부분집합의 개수(몇개를 뽑을지 정함)
    	}
    	
        int answer = candidateKey.size();
        return answer;
    }
    
    public static void findCandidate(int start, int end, int pick, int col, HashSet<Integer> hs, String[][] relation) {
    	//종료 조건
    	if (end == pick) {
    		//최소성을 만족하는지 확인
    		for (HashSet<Integer> key : candidateKey) { 
    			if (hs.containsAll(key)) {//어떤 후보키가 유일성을 만족하는 후보키를 포함하고 있다면 최소성을 위반한 것
    				return; //종료
    			}
    		}
    		
    		//유일성을 만족하면 후보키에 추가
    		if (isUnique(hs, col, relation)) {
    			candidateKey.add(hs);
    		}
    		
    		return;
    	}
    	
    	//문자 대신 컬럼 번호로 후보키 저장함(개수만 출력하기 때문에 숫자로 바꿔서 넣어도 됨 -> 첫번째 컬럼은 1, 두번째 컬럼은 2로 저장됨)
    	for (int i = start; i < col; i++) {
    		HashSet<Integer> newHs = new HashSet<Integer>(hs);
    		newHs.add(i);
    		findCandidate(i, end + 1, pick, col, newHs, relation);
    	}
    }
    
    public static boolean isUnique(HashSet<Integer> check, int col, String[][] relation) {
    	ArrayList<String> list = new ArrayList<>(); //유일성 체크를 위한 리스트
    	
    	for (int i = 0; i < relation.length; i++) {
    		String s = "";
    		
    		//현재 조합으로 얻은 컬럼들의 값을 더함
    		for (int j : check) {
    			s += relation[i][j];
    		}
    		
    		//더한 값이 이미 리스트에 저장되어 있던 값이라면 유일성을 만족하지 않음
    		if (list.contains(s)) {
    			return false;
    		} else { //리스트에 없는 값이라면 유일성을 만족하는 것이기 때문에 리스트에 추가
    			list.add(s);
    		}
    	}
    	
    	return true;
    }
	
	public static void main(String[] args) {
		String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, 
				{"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};

		System.out.println(solution(relation));
	}

}
