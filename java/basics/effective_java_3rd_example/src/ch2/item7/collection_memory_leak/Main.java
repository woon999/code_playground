package ch2.item7.collection_memory_leak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<String, Car> carMap = new HashMap<>();

		carMap.put("car1", new Car());
		carMap.put("car2", new Car());
		carMap.put("car3", new Car());

		while(true){
			System.out.print("name : ");
			String name = br.readLine();

			if(name.equals("clear")){
				System.out.println("clear!");
				carMap.clear(); // gc 강제 실행
			} else{
				carMap.put(name, new Car());
			}

			// person은 입력받을 때 참조가 없기 때문에 GC대상이다.
			// 그래서 프로그램 켜놓고 입력대기 상태에서 jcmd로 힙 히스토그램을 살펴보면
			// 죽은 인스턴스이기 때문에 나타지 않는다.
			// 반면에 Car는 계속해서 증가한다.
			Person person = new Person();
			person.setName(name);
			System.out.println(person.getName());
		}
	}
}
