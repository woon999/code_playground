package baekjoon.tttwo.graph;

// #19238 graph 스타트 택시 - bfs 
import java.io.*;
import java.util.*;

public class StartTaxi {

	static class Client implements Comparable<Client> {
		int idx;
		int fromX;
		int fromY;
		int toX;
		int toY;

		public Client(int fromY, int fromX, int toY, int toX) {
			this.fromX = fromX;
			this.fromY = fromY;
			this.toX = toX;
			this.toY = toY;
		}

		public void setIdx(int idx) {
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "Client [idx=" + idx + ", fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY
					+ "]";
		}

		@Override
		public int compareTo(Client o) {
			if (this.fromY == o.fromY) {
				return this.fromX - o.fromX;
			}
			return this.fromY - o.fromY;
		}

	}

	static int n, m, curFuel;
	static int[][] map;
	static List<Client> clients;
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { -1, 0, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		curFuel = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken()) - 1;
		int sx = Integer.parseInt(st.nextToken()) - 1;
		clients = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Client c = new Client(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			clients.add(c);
		}

		Collections.sort(clients);
		// client idx 설정 (행, 열 낮은순) 
		int idx = 1;
		for (Client c : clients) {
			c.setIdx(idx++);
		}

		for (int i = 0; i < m; i++) {
			// 가장 가까운 고객 찾기 
			int clientIdx = findClient(sx, sy);
			if (clientIdx == -1) {
				System.out.println(-1);
				return;
			}

			// 고객 데려다주기 
			Client client = getClient(clientIdx);
			int cf = curFuel;
			int restFuel = goToClientDestination(client.fromX, client.fromY, curFuel, client.toX, client.toY);
			if (restFuel == -1) {
				System.out.println(-1);
				return;
			}
			
			// 도착 후 연료 충전 
			curFuel = restFuel + 2 * (cf - restFuel);
//			System.out.println(curFuel +" --- 충전");
			sx = client.toX;
			sy = client.toY;
			clients.remove(client);
		}

		if (clients.size() != 0) {
			System.out.println(-1);
			return;
		}
		System.out.println(curFuel);

	}
	
	static Client getClient(int idx) {
		for(Client c : clients) {
			if(c.idx == idx) {
				return c;
			}
		}
		return null;
	}

	static int findClient(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] check = new boolean[n][n];
		q.add(new int[] { sx, sy, curFuel });
		check[sy][sx] = true;

		List<int[]> waitClient = new ArrayList<>();
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0];
			int py = p[1];
			int pf = p[2];

			if (pf < 0)
				continue;

			for (Client c : clients) {
				if (c.fromX == px && c.fromY == py) {
//					System.out.println(c + " -- insert ");
					waitClient.add(new int[] { c.idx, pf });
					continue;
				}
			}

			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1 || check[ny][nx])
					continue;

				if (map[ny][nx] == 0) {
					check[ny][nx] = true;
					q.add(new int[] { nx, ny, pf - 1 });
				}
			}
		}

		if(waitClient.size() == 0) {
			return -1;
		}
		
		Collections.sort(waitClient, (a, b) -> {
			if (b[1] == a[1]) {
				return a[0] - b[0];
			}
			return b[1] - a[1];
		});

//		System.out.println("--- 출발 ");
//		System.out.println(waitClient.get(0)[0] +", " + waitClient.get(0)[1]);
		curFuel = waitClient.get(0)[1];
		return waitClient.get(0)[0];
	}

	static int goToClientDestination(int sx, int sy, int fuel, int ex, int ey) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] check = new boolean[n][n];
		q.add(new int[] { sx, sy, fuel });
		check[sy][sx] = true;

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0];
			int py = p[1];
			int pf = p[2];

			if (ex == px && ey == py) {
//				System.out.println(px +","+py +" clinet 도착 --- " + pf);
				return pf;
			}

			if (pf == -1) {
				return -1;
			}

			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1 || check[ny][nx])
					continue;

				if (map[ny][nx] == 0) {
					check[ny][nx] = true;
					q.add(new int[] { nx, ny, pf - 1 });
				}
			}
		}
		return -1;
	}
}
