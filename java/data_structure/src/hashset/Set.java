package hashset;


public interface Set<E> {

	/**
	 * 지정된 원소가 Set 내부에 중복된 원소가 없을 경우 추가합니다.
	 *
	 * @param e 집합에 추가할 요소
	 * @return {@code true} 만약 Set에 중복되는 원소가 없어 정상적으로 추가되었을 경우,
	 *         else, {@code false}
	 */
	boolean add(E e);

	/**
	 * 지정된 원소가 Set에 있는 경우 해당 원소를 삭제합니다.
	 *
	 * @param o Set 자료구조에서 삭제할 원소
	 * @return {@code true} 만약 Set에 해당 원소가 포함되어 정상적으로 삭제되었을 경우,
	 *         else, {@code false}
	 */
	boolean remove(Object o);

	/**
	 * 현재 집합에 특정 원소가 포함되어있는지 여부를 반환합니다.
	 *
	 * @param o
	 * @return {@code true} Set에 지정된 원소가 포함되어 있을 경우,
	 *         else, {@code false}
	 */
	boolean contains(Object o);

}
