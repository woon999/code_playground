package ch3.item13;

public class HashTable implements Cloneable{
	private Entry[] buckets;
	private int size;

	public HashTable(){
		size = 10;
		buckets = new Entry[size];
	}

	public void init(){
		buckets[0] = new Entry(1, 1, null);
		for(int i=1; i<size; i++){
			buckets[i] = new Entry(i+1, i+1, buckets[i-1]);
		}
	}


	public void print(){
		System.out.println(buckets[size-1]);
	}

	private static class Entry{
		final Object key;
		Object value;
		Entry next;

		Entry(Object key, Object value, Entry next){
			this.key = key;
			this.value = value;
			this.next = next;
		}

		Entry deepCopy(){
			// 재귀 방식
			// return new Entry(key, value ,next == null? null : next.deepCopy());

			// 엔트리 자신이 가리키는 연결 리스트를 반복적으로 복사한다.
			Entry result = new Entry(key, value, next);
			for(Entry p = result; p.next != null; p = p.next){
				p.next = new Entry(p.next.key, p.next.value, p.next.next);
			}
			return result;
		}

		@Override
		public String toString() {
			return "Entry{" +
				"key=" + key +
				", value=" + value +
				", next=" + next +
				'}';
		}
	}

	// 잘못된 clone 메서드 - 가변 상태를 공유한다
	// @Override public HashTable clone() {
	// 	try{
	// 		HashTable result = (HashTable) super.clone();
	// 		result.buckets = buckets.clone();
	// 		return  result;
	// 	} catch (CloneNotSupportedException e){
	// 		throw new AssertionError();
	// 	}
	// }

	// 복잡한 가변 상태를 갖는 클래스용 재귀적 clone 메서드
	@Override public HashTable clone() {
		try{
			HashTable result = (HashTable) super.clone();
			result.buckets = new Entry[buckets.length];
			for(int i=0; i<buckets.length; i++){
				result.buckets[i] = buckets[i].deepCopy();
			}
			return  result;
		} catch (CloneNotSupportedException e){
			throw new AssertionError();
		}
	}

	// clone 메서드 퇴화시키기
	// @Override final Object clone() throws CloneNotSupportedException {
	// 	throw new CloneNotSupportedException();
	// }

	public static void main(String[] args) {
		HashTable hashTable = new HashTable();
		hashTable.init();

		HashTable clone = hashTable.clone();
		System.out.print("hashtable buckets.equals( clone.buckets ) ? ");
		System.out.println(hashTable.buckets[1].equals(clone.buckets[1]));

		// System.out.println("------ HashTable ------");
		// hashTable.print();
		// System.out.println("------ clone ------");
		// clone.print();
	}

}
