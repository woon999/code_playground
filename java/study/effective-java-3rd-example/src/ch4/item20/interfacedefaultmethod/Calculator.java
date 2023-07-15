package ch4.item20.interfacedefaultmethod;

public interface Calculator<E> {
	public E plus(E i, E j);
	public E minus(E i, E j);
	public E divide(E i, E j);
	public E multiple(E i, E j);

	default void print(E i, E j){
		System.out.println(i +" " + j);
	}
}
