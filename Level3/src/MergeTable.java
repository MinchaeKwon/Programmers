/**
 * 표 병합
 * https://school.programmers.co.kr/learn/courses/30/lessons/150366
 * 
 * @author Minchae
 * @date 2023. 12. 3.
 */

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MergeTable {
	
	// 표의 크기가 50x50이기 때문에 크기를 2501로 함 (1부터 시작)
	static int[] parent = new int[2501];
	static String[] values = new String[2501];

	public static void main(String[] args) {
		String[] commands = { "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean",
				"UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
				"UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4",
				"UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4" };

		String[] result = solution(commands);
		
		for (String s : result) {
			System.out.print(s + " ");
		}
	}
	
	public static String[] solution(String[] commands) {
		for (int i = 1; i <= 2500; i++) {
			parent[i] = i;
			values[i] = "";
		}
		
        ArrayList<String> answer = new ArrayList<>();
        
        for (String command : commands) {
        	StringTokenizer st = new StringTokenizer(command);
        	
        	String order = st.nextToken();
        	
        	if (order.equals("UPDATE")) {
        		if (st.countTokens() == 3) {
        			// r행 c열에 있는 값을 value로 수정
        			int r = Integer.parseInt(st.nextToken());
        			int c = Integer.parseInt(st.nextToken());
        			String value = st.nextToken();
        			
        			int num = convertNum(r, c);
        			int root = find(num);
        			
        			values[root] = value;
        		} else {
        			// value1 값을 value2로 수정
        			String value1 = st.nextToken();
        			String value2 = st.nextToken();
        			
        			for (int i = 1; i <= 2500; i++) {
        				if (value1.equals(values[i])) {
        					values[i] = value2;
        				}
        			}
        		}
        	} else if (order.equals("MERGE")) {
        		// (r1, c1) 위치의 셀과 (r2, c2) 위치의 셀을 선택하여 병합
        		int r1 = Integer.parseInt(st.nextToken());
        		int c1 = Integer.parseInt(st.nextToken());
        		int r2 = Integer.parseInt(st.nextToken());
        		int c2 = Integer.parseInt(st.nextToken());
        		
        		int num1 = convertNum(r1, c1);
        		int num2 = convertNum(r2, c2);
        		
        		int root1 = find(num1);
        		int root2 = find(num2);
        		
        		// 같은 그룹일 경우 다음 명령으로 넘어감 -> 이미 병합된 상태이기 때문에 병합할 필요 없음
        		if (root1 == root2) {
        			continue;
        		}
        		
        		String mergeStr = values[root1].isBlank() ? values[root2] : values[root1]; // 값을 가지고 있는 셀의 값을 가짐
        		
        		values[root1] = "";
        		values[root2] = "";
        		
        		union(root1, root2);
        		
        		values[root1] = mergeStr; // 두 개의 셀 그룹이 같으니까 root1의 값만 바꿔주면 됨
        		
        	} else if (order.equals("UNMERGE")) {
        		// (r, c) 위치의 셀을 선택하여 해당 셀의 모든 병합을 해제
        		int r = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		
        		int num = convertNum(r, c);
        		int root = find(num);
        		
        		String unmergeStr = values[root];
        		
        		values[root] = "";
        		values[num] = unmergeStr;
        		
        		ArrayList<Integer> deleteList = new ArrayList<>();
        		
                for (int i = 1; i <= 2500; i++) {
                    if (find(i) == root) {
                    	deleteList.add(i);
                    }
                }
                
                // 병합을 해제했으니까 그룹을 나눠줌
                for (int n : deleteList) {
                	parent[n] = n;
                }
                
        	} else if (order.equals("PRINT")) {
        		// (r, c) 위치의 셀을 선택하여 셀의 값을 출력
        		int r = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		
        		int num = convertNum(r, c);
        		int root = find(num);
        		
        		answer.add(values[root].isBlank() ? "EMPTY" : values[root]);
        	}
        }
        
        return answer.toArray(new String[0]);
    }
	
	// 좌표를 번호로 변환
    public static int convertNum(int x, int y) {
        int result = 50 * (x - 1);
        return result + y;
    }
	
	// 노드 x가 속하는 부모 노드를 찾음
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
	
    // 두 개의 노드가 속한 집합을 합침(연결함)
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
    		
        // 최상위 노드가 같지 않을 경우 union
        if (x != y) {
            parent[y] = x;
        }
    }

}
