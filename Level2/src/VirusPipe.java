/**
 * Level 2 - 바이러스 파이프
 * https://school.programmers.co.kr/learn/courses/30/lessons/468373
 * 
 * @author minchae
 * @date 2026. 11. 10.
 */

import java.util.*;

public class VirusPipe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static class Pipe {
        int end;
        int type;
        
        public Pipe(int end, int type) {
            this.end = end;
            this.type = type;
        }
    }

    static int n, infection, k;
    
    static ArrayList<Pipe>[] pipes;
    static int[] order; // 파이프 여닫는 순서

    static int answer = 0;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.infection = infection;
        this.k = k;
        
        pipes = new ArrayList[n + 1];
        order = new int[k]; // 최대 행동 수만큼 공간 할당
        
        for (int i = 1; i <= n; i++) {
            pipes[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int type = edge[2];
            
            pipes[x].add(new Pipe(y, type));
            pipes[y].add(new Pipe(x, type));
        }
        
        comb(0, 0);
        
        return answer;
    }
    
    // 파이프 열고 닫는 순서 구하기
    private void comb(int depth, int prev) {
        if (depth == k) {
            answer = Math.max(answer, simulation());
            return;
        }
        
        // 파이프 종류가 3개
        for (int i = 1; i <= 3; i++) {
            // 이전에 열었던 파이프를 다시 열 필요가 없으므로 같으면 continue
            if (i == prev) {
                continue;
            }
            
            order[depth] = i;
            comb(depth + 1, i);
        }
    }
    
    private int simulation() {
        ArrayList<Integer> infected = new ArrayList<>();
        
        infected.add(infection);
        
        // 조합을 통해 나온 파이프 타입을 통해 실제로 감염시키기
        for (int i = 0; i < k; i++) {
            infect(order[i], infected);
        }
        
        return infected.size(); // 감염된 배양체 개수 반환
    }
    
    // 감염시키는 건 bfs 이용
    private void infect(int type, ArrayList<Integer> infected) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        for (int num : infected) {
            q.add(num);
            visited[num] = true;
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (Pipe next : pipes[cur]) {
                // 아직 방문하지 않았고 파이프 타입이 같은 경우 감염됨
                if (!visited[next.end] && next.type == type) {
                    q.add(next.end);
                    visited[next.end] = true;
                    
                    infected.add(next.end);
                }
            }
        }
    }

}
