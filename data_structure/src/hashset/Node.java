package hashset;

public class Node<K> {

	final K key; // 데이터
	final int hash; // 데이터 hash 값
	Node<K> next;

	public Node(int hash, K key, Node<K> next){
		this.hash = hash;
		this.key = key;
		this.next = next;
	}

}
