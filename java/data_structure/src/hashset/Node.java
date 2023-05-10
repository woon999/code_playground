package hashset;

public class Node<E> {

	final E key; // 데이터
	final int hash; // 데이터 hash 값
	Node<E> next;

	public Node(int hash, E key, Node<E> next){
		this.hash = hash;
		this.key = key;
		this.next = next;
	}
}
