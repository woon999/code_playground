# 30. 이왕이면 제네릭 메서드로 만들어라
클래스와 마찬가지로, 메서드도 제네릭으로 만들 수 있다. 매개변수화 타입을 받는 정적 유틸리티 메서드는 보통 제네릭이다. 예컨대 Collections의 알고리즘 메서드(binarySerach, sort 등)은 모두 제네릭이다.

제네릭 메서드 작성법은 제네릭 타입 작성법과 비슷하다. 다음은 두 집합의 합집합을 반환하는 문제가 있는 메서드이다.
~~~
// [ 로 타입 사용 - 수용 불가! ] 컴파일은 되지만 경고가 발생한다.
public static Set union(Set s1, Set s2){
   Set result = new HashSet(s1);
   result.addAll(s2);
   return result;
}
~~~

경고들을 없애려면 type-safe하게 만들어야 한다. 메서드 선언에서 세 집합(입력 2개, 반환 1개)의 원소 타입을 매개변수로 명시하고, 메서드 안에서도 이 타입 매개변수만 사용하게 수정하면 된다. **(타입 매개변수들을 선언하는) 타입 매개변수 목록은 메서드의 제한자와 반환 타입 사이에 온다.** 

다음 코드에서 타입 매개변수 목록은 <E>이고 반환 타입은 Set<E>이다. 타입 매개변수의 명명 규칙은 제네릭 메서드나 제네릭 타입이나 똑같다(아이템 29, 68).
~~~
// [ 제네릭 메서드 ]
public static <E> Set<E> union(Set<E> s1, Set<E> s2){
   Set<E> result = new HashSet<>(s1);
   result.addAll(s2);
   return result;
}
~~~

단순한 제네릭 메서드라면 이 정도면 충분하다. 이 메서드는 경고 없이 컴파일되며, 타입 안전하고, 쓰기도 쉽다. 다음은 이 메서드를 사용하는 간단한 프로그램이다. 직접 형변환하지 않아도 어떤 오류나 경고 없이 컴파일된다.

~~~
// [ 제네릭 메서드를 활용하는 간단한 프로그램 ]
public static void main(String[] args) {
   Set<String> guys = Set.of("톰", "딕", "해리");
   Set<String> stooges = Set.of("래리", "모에", "컬리");
   Set<String> aflCio =union(guys, stooges);
   System.out.println(aflCio);
}
~~~

이 프로그램을 실행하면 “[모에, 톰, 해리, 래리, 컬리, 딕]”이 출력된다(원소 순서는 구현 방식에 따라 달라진다).

위에서 구현한 union메서드는 집합 3개(입력 2개, 반환 1개)의 타입이 모두 같아야 한다. 이를 한정적 와일드카드 타입(아이템 31)을 사용하여 더 유연하게 개선할 수 있다.

<br>

## 싱글턴 팩토리
때때로 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 할 때가 있다. 제네릭은 런타임에 타입 정보가 소거(아이템 28)되므로 하나의 객체를 어떤 타입으로든 매개변수화할 수 있다. 하지만 이렇게 하려면 요청한 타입 매개변수에 맞게 매번 그 객체의 타입을 바꿔주는 정적 팩토리를 만들어야 한다. 이 패턴을 싱글턴 팩토리라 하며, Collections.reverseOrder 같은 함수 객체나 이따금 Collections.emptySet 같은 컬렉션용으로 사용한다.

### 항등함수(identity function)를 담은 클래스
자바 라이브러리의 Function.identity를 사용하면 되지만, 공부를 위해서 직접 한번 작성해보자. 항등함수 객체는 상태가 없으니 요청할 때 마다 새로 생성하는 것은 낭비다. 자바의 제네릭이 실체화된다면 항등함수를 타입별로 하나씩 만들어야 했겠지만, 소거 방식을 사용한 덕에 제네릭 싱글턴 하나면 충분하다.

IDENTITY_FN을 UnaryOperator<T>로 형변환하면 비검사 형변환 경고가 발생한다. T가 어떤 타입이든 UnaryOperator<Object>는 UnaryOperator<T>가 아니기 때문이다. 하지만 항등함수란 입력 값을 수정없이 그대로 반환하는 특별 함수이므로, T가 어떤 타입이든 UnaryOperator<T>를 사용해도 type-safe하다. 그러므로 비검사 형변환 경고를 숨겨도 된다. @SuppressWarnings("unchecked")
~~~
// [ 제네릭 싱글턴 팩토리 패턴 ]
private static UnaryOperator<Object>IDENTITY_FN= (t) -> t;

@SuppressWarnings("unchecked")
public static <T> UnaryOperator<T> identityFunction() {
   return (UnaryOperator<T>)IDENTITY_FN;
}
~~~

다음 코드는 위 제네릭 싱글턴을 UnaryOperator<String>과 UnaryOperator<Number>로 사용하는 모습이다. 지금까지와 마찬가지로 형변환을 하지 않아도 컴파일 오류나 경고가 발생하지 않는다.
~~~
// [ 제네릭 싱글턴을 사용하는 예 ]
public static void main(String[] args) {
   String[] strings = {"삼베", "대마", "나일론"};
   UnaryOperator<String> sameString =identityFunction();
   for(String s : strings){
      System.out.println(sameString.apply(s));
   }

   Number[] numbers = {1, 2.0, 3L};
   UnaryOperator<Number> sameNumber =identityFunction();
   for (Number n : numbers) {
      System.out.println(sameNumber.apply(n));
   }
}
~~~

<br>

## 재귀적 타입 한정(recursive type bound)
상대적으로 드물긴 하지만, 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있다. 바로 재귀적 타입 한정(recursive type bound)이라는 개념이다.
- 재귀적 타입 한정은 주로 타입의 자연적 순서를 정하는 Comparable 인터페이스와 함께 쓰인다. 여기서 타입 매개변수 T는 Comparable<T>를 구현한 타입이 비교할 수 있는 원소의 타입을 정의한다.

~~~
public interface Comparable<T> {
	int compareTo(T o);
}
~~~

<br>

Comparable을 구현한 원소의 컬렉션을 입력받는 메서드들은 주로 그 원소들을 정렬 혹은 검색하거나, 최솟값이나 최댓값을 구하는 식으로 사용된다. 이 기능을 수행하려면 컬렉션에 담긴 모든 원소가 상호 비교될 수 있어야 한다. 다음은 이 제약을 코드로 표현한 모습이다.
~~~
// [ 재귀적 타입 한정을 이용해 상호 비교할 수 있음을 표현했다. ]
public static <E extends Comparable<E>> E max(Collection<E> c);
~~~

타입 한정이 <E extends Comparable<E>>는 “모든 타입 E는 자신과 비교할 수 있다”라고 읽을 수 있다. 상호 비교 가능한다는 뜻을 아주 정확하게 표현했다라고 할 수 있다.

다음은 방금 선언한 메서드의 구현이다. 컬렉션에 담긴 원소의 자연적 순서를 기준으로 최댓값을 계산하며, 컴파일 오류나 경고는 발생하지 않는다.
~~~
// [ 컬렉션에서 최댓값을 반환한다. - 재귀적 타입 한정 사용 ]
public static <E extends Comparable<E>> E max(Collection<E> c){
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
~~~

재귀적 한정 타입은 훨씬 복잡해질 가능성이 있긴 하지만, 다행히 그런 일은 잘 일어나지 않는다. 이번 아이템에서 설명한 관용구, 여기에 와일드카드를 사용한 변형(아이템 31), 그리고 시뮬레이트한 셀프 타입 관용구(아이템 2)를 이해하고 나면 실전에서 마주치는 대부분의 재귀적 타입 한정을 무리 없이 다룰 수 있을 것이다.

<br>

## 핵심 정리
제네릭 타입과 마찬가지로, 클라이언트에서 입력 매개변수와 반환값을 명시적으로 형변환해야 하는 메서드보다 제네릭 메서드가 안전하며 사용하기도 쉽다. 타입과 마찬가지로, 메서드도 형변환 없이 사용할 수 있는 편이 좋으며, 많은 경우 그렇게 하려면 제네릭 메서드가 되어야 한다. 역시 타입과 마찬가지로, 형변환을 해줘야 하는 기존 메서드는 제네릭하게 만들자. 기존 클라이언트는 그대로 둔 채 새로운 사용자의 삶을 훨씬 편하게 만들어줄 것이다(아이템 26).

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/685) 에 있습니다.
