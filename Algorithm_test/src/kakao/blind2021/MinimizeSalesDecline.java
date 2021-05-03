package kakao.blind2021;

// blind #7 매출하락최소화 - 중간 저장 
import java.util.*;

public class MinimizeSalesDecline {
	
	static int[] dp;
	static boolean[] check;
	

	public static void main(String[] args) {
		int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
		int[][] links = { {10, 8}, {1, 9}, {9, 7}, 
						{5, 4}, {1, 5}, {5, 10}, {10, 6},
						{1, 3}, {10, 2} };
		
		System.out.println(solution(sales, links));
	}

	
	public static int solution(int[] sales, int[][] links) {
        int answer = 0;
        int len = links.length;
        dp = new int[sales.length+1];
        
        
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list;
        
       for(int i=0; i<len; i++) {
    	   int root = links[i][0];
    	   int source = links[i][1];
    	   
    	   if(map.containsKey(root)) {
    		   list = map.get(root);
    		   list.add(source);
    		   map.put(root, list);
    	   }else {
    		   list = new ArrayList<>();
    		   list.add(root);
    		   list.add(source);
    		   map.put(root, list);
    	   }
       	}
       
       
       	// team(팀장) : 1,5,9,10 
       	for(int key : map.keySet()) {
       		System.out.println(key+" : " +map.get(key));
       	}

       	// sales대로 정렬
       	List<Record> saleRankList = new ArrayList<>();
       	for(int i=0; i<sales.length; i++) {
       		saleRankList.add(new Record(i+1, sales[i]));
       	}
       	
       Collections.sort(saleRankList, new Comparator<Record>() {

		@Override
		public int compare(Record o1, Record o2) {
			// TODO Auto-generated method stub
			return o1.saleMoney- o2.saleMoney;
			}
       	});
       
//       for(Record r: saleRankList) {
//    	   System.out.println(r.saleNum +" : " + r.saleMoney);
//       }
       	
       	
       // 팀list 생성 (A,B,C,D...) 
//       	List<Integer> teamNum = new ArrayList<>(map.keySet());
//       	
//       	for(int i : teamNum) {
//       		System.out.println(i);
//       	}
       	
       	//최소 신장트리 (같은 팀 중복 x)
       	// 판매금액 낮은순으로 조회 
       	
       	int sum =0;
       	
       	//DFS?
       	
       	for(int i=0; i<saleRankList.size(); i++) {
       		check = new boolean[sales.length+1];
       		System.out.println(saleRankList.get(i).saleNum);
       	}
       	
       	

        return answer;
    }
	
	static void solve(int pos, int moeny, int teamNum, List<Record> list) {
		
		
		int res =0;
		
		res = list.get(pos).saleMoney;
		
		// 같은 팀인가 
		
	}
}

class WorkShop{
	int to;
	int from;
	
	public WorkShop(int to, int from){
		this.to = to;
		this.from = from;
	}
}

class Record{
	int saleNum;
	int saleMoney;
	
	public Record(int saleNum, int saleMoney) {
		this.saleNum = saleNum;
		this.saleMoney =saleMoney;
	}
}