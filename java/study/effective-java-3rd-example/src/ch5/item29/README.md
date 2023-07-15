# 29. 이왕이면 제네릭 타입으로 만들라
JDK가 제공하는 제네릭 타입과 메서드를 사용하는 일은 일반적으로 쉬운 편이지만, 제네릭 타입을 새로 만드는 일은 조금 더 어렵다. 그래도 배워두면 그만한 값어치는 충분하다.

아이템 7에서 다룬 스택 코드를 다시 살펴보자.
~~~
public class Stack {
   private Object[] elements;
   private int size;
   private static final intDEFAULT_INITIAL_CAPACITY= 16;

   public Stack() {
      elements = new Object[DEFAULT_INITIAL_CAPACITY];
   }

   public void push(Object e) {
      ensureCapacity();
      if (size % 10 == 0) {
         System.out.println("stack push " + size);
      }
      elements[size++] = e;
   }

   public Object pop() {
      if (size == 0) {
         throw new EmptyStackException();
      }
      Object result = elements[--size];
      elements[size] = null; // 다 쓴 참조 해제
      return result;
   }

   public boolean isEmpty(){
      return size == 0;
   }

   private void ensureCapacity() {
      if (elements.length == size) {
         elements = Arrays.copyOf(elements, 2 * size + 1);
      }
   }
}
~~~

<br>

이 클래스는 원래 제네릭 타입이어야 마땅하다. 이 클래스를 제네릭으로 바꾼다해도 현재 버전을 사용하는 클라이언트는 아무 문제가 없다. 오히려 클라이언트가 지금 상태에서 스택에서 꺼낸 객체를 형변환할 때 런타임 오류 날 위험이 있다.

## 일단, 클래스 선언에 타입 매개변수를 추가하자.
타입 이름으로는 보통 E를 사용한다. 그럼 다음 코드에 쓰인 Object를 적절한 타입 매개변수로 바꾸고 컴파일해보자.
~~~
public class Stack<E> {
   private E[] elements;
   private int size;
   private static final intDEFAULT_INITIAL_CAPACITY= 16;

   public Stack() {
      elements = new E[DEFAULT_INITIAL_CAPACITY]; // 컴파일 에러
   }

   public void push(E e) {
      ensureCapacity();
      if (size % 10 == 0) {
         System.out.println("stack push " + size);
      }
      elements[size++] = e;
   }

   public E pop() {
      if (size == 0) {
         throw new EmptyStackException();
      }
      E result = elements[--size];
      elements[size] = null; // 다 쓴 참조 해제
      return result;
   }

   public boolean isEmpty(){
      return size == 0;
   }

   private void ensureCapacity() {
      if (elements.length == size) {
         elements = Arrays.copyOf(elements, 2 * size + 1);
      }
   }
}
~~~

이 단계에서 대체로 오류가 하나 발생한다. 아이템 28에서 설명한 것 처럼, E와 같은 실체화 불가 타입으로는 배열을 만들 수 없다. 제네릭 배열은 항상 이 문제를 수반할 것이다.

## 제네릭 배열 오류 해결하기
적절한 해결책은 두 가지다. 

### 1. 형변환으로 우회하기
첫 번째는 제네릭 배열 생성을 금지하는 제약을 대놓고 우회하는 방법이다. Object 배열을 생성한 다음 제네릭 배열로 형변환해보자. 이제 컴파일러는 오류 대신 경고를 보낼 것이다. 하지만 type-safe하지는 않다.
#### 비검사 형변환 type-safe 증명
컴파일러는 할 수 없지만 우리는 type-safe함을 증명할 수 있다. 따라서 이 비검사 형변환이 type-safe를 해치지 않음을 스스로 검증해야 한다. 문제의 배열 elements는 private 필드에 저장되고, 클라이언트로 반환되거나 다른 메서드에 전달되는 일이 전혀 없다. push 메서드를 통해 배열에 저장되는 원소의 타입은 항상 E다. 따라서 이 비검사 형변환은 확실히 안전하다.

<br>

### 2. 필드 타입을 Object[]로 변경하기
제네릭 배열 생성 오류를 해결하는 두 번째 방법은 elements 필드의 타입을 E[]에서 Object[]로 바꾸는 것이다. 이렇게 하면 첫 번째와는 다른 오류가 발생한다.
#### 비검사 형변환 type-safe 증명
E는 실체화 불가 타입이므로 컴파일러는 런타임에 이뤄지는 형변환이 type-safe한지 증명할 방법이 없다. 이번에도 마찬가지로 직접 증명하고 숨길 수 있다. pop 메서드 전체에서 경고를 숨기지 말고, 아이템 27 조언을 따라 비검사 형변환을 수행하는 할당문에서만 숨겨보자.

<br> 

### 정리
제네릭 배열 생성을 제거하는 두 방법 모두 나름의 지지를 얻고 있다.     
- 첫 번째 방법은 가독성이 더 좋다. 배열의 타입을 E[]로 선언하여 오직 E 타입 인스턴스만 받음을 확실히 어필한다. 코드도 더 짧다. 보통의 제네릭 클래스라면 코드 이곳저곳에서 이 배열을 자주 사용할 것이다.
- 첫 번째 방식에서는 형변환을 배열 생성시 단 한 번만 해주면 되지만, 두 번째 방식에서는 배열에서 원소를 읽을 때마다 해줘야 한다.
- 따라서 현업에서는 첫 번째 방식을 더 선호하며 자주 사용한다.
- 하지만 (E가 Object가 아닌 한) 배열의 런타임 타입이 컴파일 타입과 달라 힙 오염(heap pollution; 아이템 32)을 일으킨다. 힙 오염이 맘에 걸리는 프로그래머는 두 번째 방식을 고수하기도 한다. (이번 아이템에서는 힙 오염이 해가 되지 않았다.)

<br>

## 아니 아이템 28에서는 리스트를 우선하라면서?
지금까지의 설명은 아이템 28 “배열보다는 리스트를 우선하라”과 모순되어 보인다. 사실 제네릭 타입 안에서 리스트를 사용하는 게 항상 가능하지도, 꼭 더 좋은 것도 아니다.
- 자바는 리스트를 기본 타입으로 제공하지 않으므로 ArrayList같은 제네릭 타입도 결국은 기본 타입인 배열을 사용해 구현해야 한다.
- HashMap같은 제네릭 타입은 성능을 높일 목적으로 배열을 사용하기도 한다.

<br>

## 제네릭 타입의 타입 매개변수
제네릭 타입은 타입 매개변수에 아무런 제약을 두지 않는다. 단, 기본 타입은 사용할 수 없다. 이는 자바 제네릭 타입 시스템의 근본적인 문제이나 박싱된 기본 타입(아이템 61)을 사용해 우회할 수 있다.
- 가능: Stack<Object>, Stack<int[]>, String<List<String>>
- 불가능: Stack<int>, Stack<double>

<br>

## 한정적 타입 매개변수(bounded type parameters)
일부로 타입 매개변수에 제약을 둘 수도 있다. 다음은 <E extends Delayed>는 Delayed의 하위 타입만 받는다는 뜻이다. 이렇게 하면 ClassCastException은 걱정할 필요가 없다.
~~~
public class DelayQueue<E extends Delayed> implements BlockingQueue<E>
~~~

<br>

## 핵심 정리
클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 더 안전하고 쓰기 편하다. 그러니 새로운 타입을 설계할 때는 형변환 없이도 사용할 수 있도록 하라. 그렇게 하려면 제네릭 타입으로 만들어야 할 경우가 많다. 기존 타입 중 제네릭이었어야 하는 게 있다면 제네릭 타입으로 변경하자. 기존 클라이언트에는 아무 영향을 주지 않으면서, 새로운 사용자를 훨씬 편하게 해주는 길이다(아이템 26).

<br>

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/681) 에 있습니다.
