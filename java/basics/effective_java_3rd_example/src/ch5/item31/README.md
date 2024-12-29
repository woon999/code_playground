# 31. 한정적 와일드카드를 사용해 API 유연성을 높이라
아이템 28에서 얘기했듯 매개변수화 타입은 불공변(invariant)이다. 즉, 서로 다른 타입 Type1과 Type2가 있을 때 List<Type1>은 List<Type2>의 하위 타입도 상위 타입도 아니다. 직관적이지 않겠지만 List<String>은 List<Object>의 하위 타입이 아니라는 뜻인데, 곰곰이 따져보면 사실 이쪽이 말이 된다.
	
List<Object>에는 어떤 객체든 넣을 수 있지만 List<String>에는 문자열만 넣을 수 있다. 즉, List<String>은 List<Object>가 하는 일을 제대로 수해하지 못하니 하위 타입이 될 수 없다.(리스코프 치환 원칙 위배)	
	
<br>

## PECS 공식
다음 공식을 워외두면 어떤 와일드카드 타입을 써야하는지 기억하는 데 도움이 된다. 나프탈린(Naftalin)과 와들러(Wadler)는 겟풋원칙(Get and Put Principle)으로 부른다.

#### 펙스(PECS): producer-extends, consumer-super
- 매개변수화 타입 T가 생성자라면 <? extends T>
- 매개변수화 타입 T가 소비자라면 <? super T>

<br>

## 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체하라.
와일드카드와 관련해 논의해야 할 주제가 하나 더 남았다. 타입 매개변수와 와일드카드에는 공통되는 부분이 있어서, 메서드를 정의할 때 둘 중 어느 것을 사용해도 괜찮을 때가 많다.

<br>

예를 들어 주어진 리스트에서 명시한 두 인덱스의 아이템들을 교환(swap)하는 정적 메서드를 두 방식 모두로 정의해보자. 다음 코드에서 첫 번째는 비한정적 타입 매개변수(아이템 30)를 사용했고 두 번째는 비한정적 와일드카드를 사용했다.

~~~
public static <E> void swap(List<E> list, int i, int j)
public static void swap2(List list, int i, int j)
~~~

<br>

어떤 선언이 나을까? 더 나은 이유가 무엇일까? public API라면 간단한 두 번째가 낫다. 
- 어떤 리스트든 이 메서드에 넘기면 명시한 인덱스의 원소들을 교환해 줄 것이다. 신경 써야 할 타입 매개변수도 없다.

기본 규칙은 이렇다. 이때 비한정적 타입 매개변수라면 → 비한정적 와일드카드로 바꾸고, 한정적 타입 매개변수라면 → 한정적 와일드카드로 바꾸면 된다.

하지만 두 번째 swap 선언에는 문제가 하나 있는데, 다음과 같이 아주 직관적으로 구현한 코드가 컴파일되지 않는다는 것이다.

~~~
public static void swap(List<?> list, int i, int j){
   list.set(i, list.get(j, list.get(i)));
}
~~~

이 코드를 컴파일하면 그다지 도움이 되지 않는 오류 메시지가 나온다. 방금 꺼낸 원소를 리스트에 다시 넣을 수 없다고 한다. 원인은 리스트 타입이 List<?>인데, List<?>에는 null 외에는 어떤 값도 넣을 수 없다는 데 있다. 

<br>

### private 도우미 메서드로 따로 작성하여 해결하기
다행히 형변환이나 리스트의 로 타입을 사용하지 않고도 해결할 길이 있다. 바로 와일드카드 타입의 실제 타입을 알려주는 메서드를 private 도우미 메서드로 따로 작성하여 활용하는 방법이다. 실제 타입을 알아내려면 이 도우미 메서드는 제네릭 메서드여야 한다. 다음 코드를 보자.

~~~
public static void swap(List<?> list, int i, int j){
	 swapHelper(list, i, j);
}

// private 도우미 메서드 (와일드카드 타입 -> 실제 타입)
private static <E> void swapHelper(List<E> list, int i, int j){
   list.set(i, list.set(j, list.get(i)));
}
~~~

swapHelper 메서드는 리스트가 List<E>임을 알고 있다. 즉, 이 리스트에서 꺼낸 값이 항상 E이고, E 타입의 값이라면 이 리스트에 넣어도 안전함을 알고 있다. 다소 복잡하게 구현했지만 이제 깔끔히 컴파일된다. 이상으로 swap 메서드 내부에서는 더 복잡한 제네릭 메서드를 이용했지만, 덕분에 외부에서는 와일드카드 기반의 멋진 선언을 유지할 수 있었다. 즉, swap 메서드를 호출하는 클라이언트는 복잡한 swapHelper의 존재를 모른 채 그 혜택을 누리는 것이다.

<br>

## 핵심 정리
조금 복잡하더라도 와일드카드 타입을 적용하면 API가 훨씬 유연해진다. 그러니 널리 쓰일 라이브러리를 작성한다면 반드시 와일드카드 타입을 적절히 사용해줘야 한다. 
- PECS 공식을 기억하자. 즉, 생산자(producer)는 extends를 소비자(consumer)는 super를 사용한다.
- Comparable과 Comparator는 모두 소비자라는 사실도 잊지 말자.

<br>

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/689) 에 있습니다.
