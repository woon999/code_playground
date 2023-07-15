package ch5.item31.pecs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


interface D extends Comparable<D>{ }
interface SF extends D{ }
class ImplSF implements SF{
	@Override
	public int compareTo(D o) {
		return 0;
	}
}

public class Practice2 {
	public static void main(String[] args) {
		List<SF> sf = new ArrayList<>();
		sf.add(new ImplSF());
		sf.add(new ImplSF());
		sf.add(new ImplSF());
		max(sf);
	}



	public static <E extends Comparable<? super E>> E max(List<? extends E> c){
		if(c.isEmpty()){
			throw new IllegalArgumentException("컬렉션이 비었습니다.");
		}

		E result = null;
		for(E e : c){
			if(result == null || e.compareTo(result) > 0){
				result = Objects.requireNonNull(e);
			}
		}
		return result;
	}


}
