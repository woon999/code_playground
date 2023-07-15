# 18. 상속보다는 컴포지션을 사용하라
기존 클래스를 확장하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게 하자. 기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻에서 이러한 설계를 **컴포지션(composition)**이라 한다.

새 클래스의 인스턴스 메서드들을 (private 필드로 참조하는) 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환한다.
- 이 방식을 전달(forwarding)이라 하며, 새 클래스의 메서드들을 전달 메서드(forwarding method)라고 부른다.
- 그 결과 새로운 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어나며, 심지어 기존 클래스에 새로운 메서드가 추가되더라도 전혀 영향받지 않는다.

구현은 둘로 나눠진다. 하나는 집합 클래스 자신이고, 다른 하나는 전달 메서드만으로 이뤄진 재사용 가능한 전달 클래스이다.
 
~~~
// [ 래퍼 클래스 - 상속 대신 컴포지션을 사용했다. ]
public class InstrumentedSet<E> extends ForwardingSet<E> {
   private int addCount = 0;

   public InstrumentedSet(Set<E> s) {
      super(s);
   }

   @Override
   public boolean add(E e) {
      addCount++;
      return super.add(e);
   }

   @Override
   public boolean addAll(Collection<? extends E> c) {
      addCount += c.size();
      return super.addAll(c);
   }

	 public int getAddCount(){
			return addCount;
	 }
}
~~~

<br>

 
~~~
// [ 재사용할 수 있는 전달 클래스 ]
public class ForwardingSet<E> implements Set<E> {
   private final Set<E> s;

   public ForwardingSet(Set<E> s) {
      this.s = s;
   }

   @Override
   public int size() {
      return 0;
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   @Override
   public boolean contains(Object o) {
      return false;
   }

   @Override
   public Iterator<E> iterator() {
      return null;
   }

   @Override
   public Object[] toArray() {
      return new Object[0];
   }

   @Override
   public <T> T[] toArray(T[] a) {
      return null;
   }

   @Override
   public boolean add(E e) {
      return false;
   }

   @Override
   public boolean remove(Object o) {
      return false;
   }

   @Override
   public boolean containsAll(Collection<?> c) {
      return false;
   }

   @Override
   public boolean addAll(Collection<? extends E> c) {
      return false;
   }

   @Override
   public boolean retainAll(Collection<?> c) {
      return false;
   }

   @Override
   public boolean removeAll(Collection<?> c) {
      return false;
   }

   @Override
   public void clear() {
			s.clear();
   }
}
~~~

<br>

## 핵심 정리
- 상속은 강력하지만 캡슐화를 해친다는 문제가 있다.
- 상속은 상위 클래스와 하위 클래스가 순수한 is-a 관계일 때만 써야 한다.
- is-a 관계일 때도 안심할 수만은 없는 게, 하위 클래스의 패키지가 상위 클래스와 다르고, 상위 클래스가 확장을 고려해 설계되지 않았다면 여전히 문제가 될 수 있다.
- 상속의 취약점을 피하려면 상속대신 컴포지션과 전달을 사용하자.
- 특히 래퍼 클래스로 구현할 적당한 인터페이스가 있다면 더욱 그렇다.
- 래퍼 클래스는 하위 클래스보다 견고하고 강력하다.

--- 



💡 본문은 [개발 블로그](https://loosie.tistory.com/648) 에 있습니다.