package ch2.item8;

public class Room_finalize {
	static Long end;
	int numJunkPiles; // 방(Room) 안의 쓰레기 수

	// 방의 상태. cleanable과 공유한다.
	public Room_finalize(int numJunkPiles){
		this.numJunkPiles = numJunkPiles;
		end =0L;
	}

	public void finalize(){
		System.out.println("방 청소");
		end = System.currentTimeMillis();
		numJunkPiles = 0;
	}
}
