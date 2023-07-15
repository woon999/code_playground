package ch2.item8;


public class Adult {
	public static void main(String[] args) {
		Long s = System.currentTimeMillis();
		try(Room myRoom = new Room(7)){
			System.out.println("안녕~");
		}
		System.out.println(Room.end-s);

		s = System.currentTimeMillis();
		try{
			new Room_finalize(7);
			System.out.println("안녕~");
		}catch (Exception e){
			System.out.println(e);
		}

		System.gc();
		System.runFinalization();
		System.out.println(Room_finalize.end-s);
	}
}
