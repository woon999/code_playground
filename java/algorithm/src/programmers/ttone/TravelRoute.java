package programmers.ttone;

import java.util.*;

// dfs/bfs #4 여행경로 
public class TravelRoute {
	
	static boolean[] visited;
	static List<String> route;

	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"},
							{"JFK", "HND"}};
		
		System.out.println(Arrays.toString(solution(tickets)));
	}
	
	public static String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        route = new ArrayList<String>();
        
        int cnt =0;
        dfs(tickets[0][0], tickets[0][0], tickets, cnt );
        Collections.sort(route);
        String[] answer =route.get(0).split(" ");
        return answer;
    }
	
	static void dfs(String from, String to, String[][] tickets, int cnt) {
		if(cnt == tickets.length) {
			route.add(to);
			return;
		}
		
		for(int i=0; i<tickets.length; i++) {
			if(!visited[i] && tickets[i][0].equals(from)) {
				visited[i] = true;
				dfs(tickets[i][1], to+" "+tickets[i][1], tickets, cnt+1);
				visited[i] = false;
			}
		}
		return; 
	}
}

