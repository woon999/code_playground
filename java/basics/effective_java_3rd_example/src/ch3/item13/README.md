# 13. clone 재정의는 주의해서 진행하라
Cloneable은 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스(mixin inteface, 아이템 20)지만, 아쉽게도 의도한 목적을 제대로 이루지 못했다. 가장 큰 문제는 clone 메서드가 선언된 곳이 Cloneable이 아닌 Object이고, 그마저도 protected라는 데 있다.
- 그래서 Cloneable을 구현하는 것만으로는 외부 객체에서 clone 메서드를 호출할 수 없다. 리플렉션(아이템 65)을 사용하면 가능하지만, 100% 성공하는 것도 아니다.
- 해당 객체가 접근이 허용된 clone 메서드를 제공한다는 보장이 없기 때문이다.

하지만 이를 포함한 여러 문제점에도 불구하고 Cloneable 방식은 널리 쓰이고 있어서 잘 알아두는 것이 좋다. 이번 아이템에서는 clone 메서드를 잘 동작하게끔 해주는 구현 방법과 언제 그렇게 해야 하는지를 알려주고, 가능한 다른 선택지에 관해 논의하겠다.

<br>

## Cloneable 인터페이스
메서드 하나 없는 Cloneable 인터페이스는 Object의 protected 메서드인 clone의 동작 방식을 결정한다. Cloneable을 구현한 클래스의 인스턴스에서 clone을 호출하면 극 객체의 필드들을 하나하나 복사한 객체를 반환하며, 그렇지 않은 클래스의 인스턴스에서 호출하면 CloneNotSupportedException을 던진다. 
- 이는 인터페이스를 상당히 이례적으로 사용한 예이니 따라 하지는 말자.
- 인터페이스를 구현한다는 것은 일반적으로 해당 클래스가 그 인터페이스에서 정의한 기능을 제공한다고 선언하는 행위다.
- 그런데 Cloneable의 경우에는 상위 클래스에 정의된 protected 메서드의 동작 방식을 변경한 것이다.

명세에서는 이야기하지 않지만 **실무에서 Cloneable을 구현한 클래스는 clone 메서드를 public으로 제공하며, 사용자는 당연히 복제가 이뤄지리라 기대한다.** 
- 이 기대를 만족시키려면 그 클래스와 모든 상위 클래스는 복잡하고, 강제할 수 없고, 허술하게 기술된 프로토콜을 지켜야만 하는데, 그 결과로 깨지기 쉽고, 위험하고, 모순적인 매커니즘이 탄생된다.
- 생성자를 호출하지 않고도 객체를 생성할 수 있게 되는 것이다.

<br>

## clone 메서드 일반 규약
clone 메서드의 일반 규약은 허술하다. Object 명세에서 가져온 다음 설명을 보자.
~~~
이 객체의 복사본을 생성해 반환한다. '복사'의 정확한 뜻은 그 객체를 구현한 클래스에 따라 다를 수 있다. 일반적인 의도는 다음과 같다. 어떤 객체 x에 대해 다음 식은 참이다.

- x.clone() ≠ x

또한 다음 식도 참이다.

- x.clone().getClass() == x.getClass()

하지만 이상의 요구를 반드시 만족해야 하는 것은 아니다. 한편 다음 식도 일반적으로 참이지만, 역시 필수는 아니다.

- x.clone().equals(x)

관례상, 이 메서드가 반환하는 객체는 super.clone을 호출해 얻어야 한다. 이 클래스와 (Object를 제외한) 모든 상위 클래스가 이 관례를 따른다면 다음 식은 참이다. 

- x.clone.getClass() == x.getClass()
~~~
관례상, 반환된 객체와 원본 객체는 독립적이어야 한다. 이를 만족하려면 super.clone으로 얻은 객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수도 있다.

<br>

강제성이 없다는 점만 빼면 생성자 연쇄(constructor chaining)와 살짝 비슷한 매커니즘이다. 
- 즉, clone 메서드가 super.clone이 아닌, 생성자를 호출해 얻은 인스턴스를 반환해도 컴파일러는 불평하지 않을 것이다.
- 하지만 이 클래스와 하위 클래스에서 super.clone을 호출한다면 잘못된 클래스의 객체가 만들어져, 결국 하위 클래스의 clone 메서드가 제대로 동작하지 않게 된다.

clone을 재정의한 클래스가 final이라면 걱정해야 할 하위 클래스가 없으니 이 관례는 무시해도 안전하다. 하지만 final 클래스의 clone 메서드가 super.clone을 호출하지 않는다면 Cloneable을 구현할 이유도 없다. Object의 clone 구현의 동작 방식에 기댈 필요가 없기 때문이다.

제대로 동작하는 clone 메서드를 가진 상위 클래스를 상속해 Cloneable을 구현하고 싶다고 해보자. 
1. 먼저 super.clone을 호출한다. 그렇게 얻은 객체는 원본의 완벽한 복제본일 것이다. 
2. 클래스에 정의된 모든 필드는 원본 필드와 똑같은 값을 갖는다. 
3. 모든 필드가 기본 타입이거나 불변 객체를 참조한다면 이 객체는 완벽히 우리가 원하는 상태라 손볼 것이 없다.
4. 쓸데없는 복사를 지양한다는 관점에서 보면 불변 클래스는 굳이 clone 메서드를 제공하지 않는 게 좋다.

<br>

### 가변 상태를 참조하지 않는 클래스용 clone 메서드 
~~~
public final class PhoneNumber implements Cloneable {
	@Override public PhoneNumber clone() {
	   try{
	      return (PhoneNumber) super.clone();
	   } catch (CloneNotSupportedException e){
	      throw new AssertionError(); // 일어날 수 없는 일이다.
	   }
	}
}
~~~
이 메서드가 동작하게 하려면 PhoneNumber의 클래스 선언에 Cloneable을 구현한다고 추가해야 한다.
- Object의 clone 메서드는 Object를 반환하지만 PhoneNumber의 clone 메서드는 Phonenumber를 반환하게 헀다.
- 자바가 공변 타이핑(covariant return typing)을 지원하니 이렇게 하는 것이 가능하고 권장하는 방식이기도 하다.
- 달리 말해서, 재정의한 메서드의 반환 타입은 상위 클래스의 메서드가 반환하는 타입의 하위 타입일 수 있다.
- super.clone 호출을 try-catch 블록으로 감싼 이유는 Object의 clone 메서드가 검사 예외(checked exception)인 CloneNotSupportedException을 던지도록 선언되었기 때문이다.

<br>

### 가변 상태를 참조하는 클래스용 clone 메서드
위의 구현은 클래스가 가변 객체를 참조하는 순간 문제가 발생한다. Stack 클래스를 예로 들어보자
~~~
public class Stack {
   private Object[] elements;
   private int size = 0;
   private static final intDEFAULT_INITIAL_CAPACITY= 16;

   public Stack() {
      this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
   }

   public void push(Object e) {
      ensureCapacity();
      elements[size++] = e;
   }

   public Object pop() {
      if (size == 0) {
         throw new EmptyStackException();
      }
      Object result = elements[size--];
      elements[size] = null; // 다 쓴 참조 해제
      return result;
   }

   private void ensureCapacity() {
      if (elements.length == size) {
         elements = Arrays.copyOf(elements, 2 * size + 1);
      }
   }

    // 가변 상태를 참조하는 클래스용 clone 메서드
    @Override public Stack clone() {
       try{
          Stack result = (Stack) super.clone();
          result.elements = elements.clone();
          return result;
       }catch (CloneNotSupportedException e){
          throw new AssertionError();
       }
    }
}
~~~

<br>

단순 super.clone을 하면 Stack 인스턴스의 size 필드는 올바른 값을 갖겠지만, elements 필드는 원본 Stack 인스턴스와 똑같은 배열을 참조할 것이다.
- 원본이나 복제본 중 하나를 수정하면 다른 하나도 수정되어 불변식을 해친다는 얘기이다.
- 따라서 프로그램이 이상하게 움직이거나 NullPointerException을 던질 것이다.

Stack 클래스의 하나뿐인 생성자를 호출한다면 이러한 상황은 절대 일어나지 않는다. clone 메서드는 사실상 생성자와 같은 효과를 낸다. 즉, clone은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.

그래서 Stack의 clone 메서드는 제대로 동작하려면 스택 내부 정보를 복해야 하는데, 가장 쉬운 방법은 elements 배열의 clone을 재귀적으로 호출해주는 것이다.
~~~
// 가변 상태를 참조하는 클래스용 clone 메서드
@Override public Stack clone() {
   try{
      Stack result = (Stack) super.clone();
      result.elements = elements.clone();
      return result;
   }catch (CloneNotSupportedException e){
      throw new AssertionError();
   }
}
~~~

한편, elements 필드가 final 이었다면 앞서의 방식은 작동하지 않는다. 이는 근본적인 문제로, 직렬화와 마찬가지로 **Cloneable 아키텍처는 '가변 객체를 참조하는 필드는 final로 선언하라'는 일반 용법과 충돌한다. 
- 단, 원본과 복제된 객체가 그 가변 객체를 공유해도 안전하다면 괜찮다.
- 그래서 복제할 수 있는 클래스를 만들기 위해 일부 필드에서 final 한정자를 제거해야 할 수도 있다.

<br>

## 해시테이블용 clone 메서드
재귀적으로 호출하는 것만으로 충분하지 않을 때도 있다. 이번에는 해시테이블용 clone 메서드를 생각해보자.  
- 해시테이블 내부는 버킷들의 배열이고, 각 버킷은 키-값 쌍을 담는 연결 리스트의 첫 번째 엔트리를 참조한다.
- 그리고 성능을 위해 java.util.LinkedList 대신 직접 구현한 경량 연결 리스트를 사용하겠다.

~~~
public class HashTable implements Cloneable{
   private Entry[] buckets = {
      new Entry(1,2,null)
   };

   private static class Entry{
      final Object key;
      Object value;
      Entry next;

      Entry(Object key, Object value, Entry next){
         this.key = key;
         this.value = value;
         this.next = next;
      }
   }

   @Override public HashTable clone() {
      try{
         HashTable result = (HashTable) super.clone();
         result.buckets = buckets.clone();
         return  result;
      } catch (CloneNotSupportedException e){
         throw new AssertionError();
      }
   }
}
~~~

<br>

복제본은 자신만의 버킷 배열을 갖지만, 이 배열은 원본과 같은 연결 리스트를 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생긴다. 
- 이를 해결하려면 각 버킷을 구성하는 연결 리스트를 복사해야 한다. 다음은 일반적인 해법이다.

복잡한 가변 상태를 갖는 클래스용 재귀적 clone 메서드
~~~
public class HashTable implements Cloneable{
	private Entry[] buckets;
	private int size;

	public HashTable(){
		size = 10;
		buckets = new Entry[size];
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
			return new Entry(key, value ,next == null? null : next.deepCopy());
		}
	}

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
}
~~~

<br>

private 클래스인 Hashtable.Entry는 깊은 복사(deep copy)를 지원하도록 보강되었다. 
- 하지만 연결 리스트를 복제하는 방법으로는 그다지 좋지 않다. 재귀 호출 때문에 리스트의 원소 수만큼 스택 프레임을 소비하여, 리스트가 길면 스택 오버플로를 일으킬 위험이 있기 때문이다.
- 이 문제를 피하려면 deepCopy를 재귀 호출 대신 반복자를 써서 순회하는 방향으로 수정해야 한다.

엔트리 자신이 가리키는 연결 리스트를 반복적으로 복사한다.
~~~
Entry deepCopy(){
   // 엔트리 자신이 가리키는 연결 리스트를 반복적으로 복사한다.
   Entry result = new Entry(key, value, next);
   for(Entry p = result; p.next != null; p = p.next){
      p.next = new Entry(p.next.key, p.next.value, p.next.next);
   }
   return result;
}
~~~

이제 복잡한 가변 객체를 복제하는 마지막 방법을 살펴보겠다. 
1. 먼저 super.clone을 호출하여 얻은 객체의 모든 필드를 초기 상태로 설정한 다음, 원본 객체의 상태를 다시 생성하는 고수준 메서드들을 호출한다.
2. HashTable 예에서라면, buckets 필드를 새로운 버킷 배열로 초기화한 다음 원본 테이블에 담긴 모든 키-값 쌍 각각에 대해 복제본 테이블의 (책에 싣지 않은) put(key, value) 메서드를 호출해 둘의 내용이 똑같게 해주면 된다.
    1. 이처럼 고수준 API를 활용해 복제하면 보통은 간단하고 제법 우아한 코드를 얻게 되지만, 아무래도 저수준에서 바로 처리할 때보다는 느리다.
    2. 또한 Cloneable 아키텍처의 기초가 되는 필드 단위 객체 복사를 우회하기 때문에 전체 Cloneable 아키텍처와는 어울리지 않는 방식이기도 하다.

<br>

### clone 메서드에서는 재정의 될 수 있는 메서드는 호출하면 안된다.
생성자에서는 재정의될 수 있는 메서드를 호출하지 않아야 하는데(아이템 19) clone 메서드도 마찬가지다. 
- 만약 clone이 하위 클래스에서 재정의한 메서드를 호출하면, 하위 클래스는 복제 과정에서 자신의 상태를 교정할 기회를 잃게 되어 원본과 복제본의 상태가 달라질 가능성이 크다.
- 따라서 앞 문단에서 얘기한 put(key, value) 메서드는 final이거나 private이어야 한다.

<br>

### public인 clone 메서드에서는 throws 절을 없애야 한다.
Object의 clone 메서드는 CloneNotSupportedException을 던진다고 선언했지만 재정의한 메서드는 그렇지 않다. **public인 clone 메서드에서는 throws 절을 없애야 한다.** 검사 예외를 던지지 않아야 그 메서드를 사용하기 편하기 때문이다.

<br>

### 상속용 클래스는 Cloneable을 구현해서는 안 된다.
상속해서 쓰기 위한 클래스 설계 방식 두 가지(아이템 19) 중 어느 쪽에서든, 상속용 클래스는 Cloneable을 구현해서는 안 된다.
- Object의 clone 메서드를 그대로 사용할 수 있지만 이 방식은 마치 Object를 바로 상속할 때처럼 Cloneable 구현 여부를 하위 클래스에서 선택하도록 해준다.
- 다른 방법으로는, clone을 동작하지 않게 구현해놓고 하위 클래스에서 재정의하지 못하게 할 수도 있다.

<br>

다음과 같이 clone을 퇴화시켜놓으면 된다.
~~~
@Override final Object clone() throws CloneNotSupportedException {
   throw new CloneNotSupportedException();
}
~~~

### clone 메서드 동기화
기억해둬야 할 게 하나 더 남았다. Cloneable을 구현한 스레드 안전 클래스를 작성할 때는 clone 메서드 역시 적절히 동기화해줘야 한다는 점이다.
- Object의 clone 메서드는 동기화를 신경 쓰지 않았다.
- 그러니 super.clone 호출 외에 다른 할 일이 없더라도 clone을 재정의하고 동기화해줘야 한다.

<br>

요약하자면, Cloneable을 구현하는 모든 클래스는 clone을 재정의해야 한다.  
- 이때 접근제한자는 public으로, 반환 타입 클래스는 자기 자신으로 변경한다.
- 이 메서드는 가장 먼저 super.clone을 호출한 후 필요한 필드를 전부 적절히 수정한다.
    - 일반적으로 이 말은 그 객체의 내부 '깊은 구조'에 숨어 있는 모든 가변 객체를 복사하고, 복제본이 가진 객체 참조 모두가 복사된 객체들을 가리키게 함을 뜻한다.
    - 이러한 내부 복사는 주로 clone을 재귀적으로 호출해 구현하지만, 이 방식이 항상 최선인 것은 아니다.
- 기본 타입 필드와 불변 객체 참조만 갖는 클래스라면 아무 필드도 수정할 필요가 없다.
- 단, 일련 번호나 고유 ID는 비록 기본 타입이나 불변일지라도 수정해줘야 한다.

<br>


## 복사 생성자와 복사 팩토리
Cloneable을 이미 구현한 클래스를 확장한다면 어쩔 수 없이 clone을 잘 작동하도록 구현해야 한다. 그렇지 않은 상황에서는 복사 생성자와 복사 팩토리라는 더 나은 객체 복사 방식을 제공할 수 있다. 복사 생성자란 단순히 자신과 같은 클래스의 인스턴스를 인수로 받는 생성자를 말한다.

- 복사 생성자 
~~~
public Yum(Yum yum) { ... } ;
~~~

<br>

복사 팩토리는 복사 생성자를 모방한 정적 팩토리다.
- 복사 팩토리
~~~
public static Yum newInstance(Yum yum) { ... } ;
~~~

복사 생성자와 그 변형인 복사 팩토리는 Cloneable/clone 방식보다 나은 면이 많다. 언어 모순적이고 위험천만한 객체 생성 메커니즘(생성자를 쓰지 않는 방식)을 사용하지 않으며, 엉성하게 문서화된 규약에 기대지 않고, 정상적인 final 필드 용법과도 충돌하지 않으며, 불필요한 검사 예외를 던지지 않고, 형변환도 필요치 않다.

추가로 복사 생성자와 복사 팩토리는 해당 클래스가 구현한 '인터페이스' 타입의 인스턴스를 인수로 받을 수 있다.
- 예컨대 관례상 모든 범용 컬렉션 구현체는 Collection이나 Map 타입을 받는 생성자를 제공한다.

인터페이스 기반 복사 생성자와 복사 팩토리의 더 정확한 이름은 '변환 생성자(conversion constructor)'와 '변환 팩토리(conversion factory)'다. 
- 이들을 이용하면 클라이언트는 원본의 구현 타입에 얽매이지 않고 복제본의 타입을 직접 선택할 수 있다.
- 예컨대 HashSet객체를 s를 TreeSet 타입으로 복제할 수 있다. clone으로는 불가능한 이 기능을 변환 생성자로는 간단히 new TreeSet<>(s)로 처리할 수 있다.


<br>

## 핵심 정리
Cloneable이 몰고 온 모든 문제를 되짚어봤을 때, 새로운 인터페이스를 만들 때는 절대 Cloneable을 확장해서는 안 되며, 새로운 클래스도 이를 구현해서는 안 된다.

final 클래스라면 Cloneable을 구현해도 위험이 크지 않지만, 성능 최적화 관점에서 검토한 후 별다른 문제가 없을 때만 드물게 허용해야 한다. 

기본 원칙은 '복제 기능은 생성자와 팩토리를 이용하는 게 최고'라는 것이다.
- 단, 배열만은 clone 메서드 방식이 가장 깔끔한, 이 규칙의 합당한 예외라 할 수 있다.

<br>

--- 


💡 본문은 [개발 블로그](https://loosie.tistory.com/620) 에 있습니다.