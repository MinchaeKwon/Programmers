/**
 * 보석 쇼핑
 * https://programmers.co.kr/learn/courses/30/lessons/67258
 * 
 * @author Minchae Gwon
 * @date 2021.2.1
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GemShopping {

	public static void main(String[] args) {
		String[] g1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		String[] g2 = {"AA", "AB", "AC", "AA", "AC"};
		String[] g3 = {"XYZ", "XYZ", "XYZ"};
		String[] g4 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		
		int[] result = solution(g1);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}

	}
	
	public static int[] solution(String[] gems) {
		//보석 종류를 담는 set
		HashSet<String> gemSet = new HashSet<>();
        
        //보석을 중복 없이 저장함 -> 보석의 종류가 몇개인지 확인하기 위함
        for (String g : gems) {
        	gemSet.add(g);
        }
        
        HashMap<String, Integer> gemMap = new HashMap<>(); //startPoint부터 현재까지의 보석 개수 저장
        Queue<String> q = new LinkedList<>(); //startPoint부터 현재까지의 보석들이 담김 -> 중복 포함
        
        int start = 0;
        int len = gems.length; //큐의 사이즈는 gems.length를 초과하지 않기 때문에 gems.length를 최대값으로 하고 갱신시킴
        
        int startPoint = 0;
        
        //앞에서 부터 순서대로 보석들을 고르다가 중복된 보석이 나오면 startPoint(시작 인덱스)를 하나씩 밀어주는 방식
        for (int i = 0; i < gems.length; i++) {
        	//gemCount에 이미 해당 보석이 추가된 경우 해당 보석의 개수에 1을 더함
        	if (!gemMap.containsKey(gems[i])) {
        		gemMap.put(gems[i], 1);
        	} else {
        		gemMap.put(gems[i], gemMap.get(gems[i]) + 1);
        	}
        	
        	//보석을 큐에 넣음
        	q.add(gems[i]);
        	
        	while (true) {
        		String g = q.peek();
        		
        		//큐에서 꺼낸 보석의 개수가 1개를 초과하는 경우
        		if (gemMap.get(g) > 1) {
        			q.poll(); //중복되었으므로 큐에서 뺌
        			startPoint++; //보석을 꺼냈으므로 startPoint를 1 증가시킴
        			gemMap.put(g, gemMap.get(g) - 1); //보석 개수도 하나 감소 시킴
        		} else {
        			break; //보석이 1개만 있을 경우에는 break
        		}
        	}
        	
        	/* 보석을 모두 다 고른 경우(gemMap의 사이즈와 gemSet의 사이즈가 같다는 것은 모든 종류의 보석이 적어도 1개 이상 있다는 것)
        		&& 새로 구한 보석 구간(큐의 사이즈)가 현재 보석 구간의 길이(len)보다 작을 경우 len을 큐의 사이즈로 갱신해줌 */
        	if (gemMap.size() == gemSet.size() && q.size() < len) {
        		//이때 큐에는 모든 종류의 보석이 적어도 1개 이상 들어있기 때문에 밑의 코드에서 끝 진열대 번호가 (start + len)이 됨
        		len = q.size();
        		start = startPoint; //시작 인덱스 갱신
        	}
        	
        }
        
        //인덱스 0부터 시작했으므로 시작 진열대 번호는 (start + 1)임, 끝 진열대 번호는 (start + len)임
        return new int[] {start + 1, start + len};
    }

}
