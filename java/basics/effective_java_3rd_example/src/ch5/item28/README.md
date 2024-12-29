# 28. 배열보다는 리스트를 사용하라
배열과 제네릭 타입에는 중요한 차이가 두 가지 있다.

## 1. 배열은 공변(covariant)이고 제네릭은 불공변(invariant)이다
첫 번째, 배열은 공변(covariant)이다. 공변의 뜻은 간단하다. Sub가 Super의 하위 타입이라면 배열 Sub[]는 배열 Super[]의 하위 타입이 된다(공변, 즉 함께 변한다는 뜻이다).

반면, 제네릭은 불공변(invariant)이다. 즉, 서로 다른 타입 Type1과 Type2가 있을 때, List<Type1>은 List<Type2>의 하위 타입도 아니고 상위 타입도 아니다.

이것만 보면 제네릭이 문제가 있을 수도 생각할 수 있지만, 사실 문제가 있는 것은 배열 쪽이다. 다음은 문법상 허용되는 코드이다.

~~~
[ 런타임에 실패한다. ]
@Test
public void runtime_fail(){
   Object[] objectArray = new Long[1];
   // ArrayStroeException을 던진다.
   Assertions.assertThrows(ArrayStoreException.class, () -> {objectArray[0] = "타입이 달라 넣을 수 없다.";});
}
~~~

<br>

하지만 다음 코드는 문법에 맞지 않는다.
~~~
[ 컴파일되지 않는다! ]
List<Object> ol = new ArrayList<Long>(); // 호환되지 않는 타입이다.
ol.add("타입이 달라 넣을 수 없다.");
~~~


<br>

## 2. 배열은 실체화(reify)된다
두 번째 주요 차이로, 배열은 실체화(reify)된다.
- 배열은 런타임에도 자신이 담기로 한 원소의 타입을 인지하고 확인한다. 그래서 위 런타임 실패 코드를 보듯 Long배열에 String을 넣으려하면 ArrayStoreException이 발생한다.
- 반면, 앞서 이야기했듯 제네릭은 타입 정보가 런타임에는 소거(erasure)된다. 원소 타입을 컴파일 타임에만 검사하며 런타임에는 알수조차 없다는 뜻이다.
- 소거는 제네릭이 지원되기 전의 레거시 코드와 제네릭 타입을 함께 사용할 수 있게 해주는 메커니즘으로, 자바 5가 제네릭으로 순조롭게 전환될 수 있도록 해줬다.

이상의 주요 차이로 인해 배열과 제네릭은 잘 어우러지지 못한다. 예컨대 배열은 제네릭 타입, 매개변수화 타입, 타입 매개변수로 사용할 수 없다. 즉, 코드를 new List<E>[], new List<String>[], new E[] 식으로 작성하면 컴파일할 때 제네릭 배열 생성 오류를 일으킨다.

<br>

## 제네릭 배열을 만들지 못하게 막은 이유
제네릭 배열을 만들지 못하게 막은 이유는 타입 안전하지 않기 때문이다. 이를 허용한다면 컴파일러가 자동 생성한 형변환 코드에서 런타임에 ClassCastException이 발생할 수 있다. 런타임에 ClassCastException이 발생하는 일을 막아주겠다는 제네릭 타입 시스템의 취지에 어긋나는 것이다.

~~~
[ 제네릭 배열 생성을 허용하지 않은 이유 - 컴파일 되지 않는다. ]
List<String>[] stringsList = new List<String>[1]; // (1)
List<Integer> intList = List.of(42); // (2)
Object[] objects = stringsList; // (3)
objects[0] = intList; // (4)
String s = stringsList[0].get(0); // (5)
~~~


제네릭 배열을 생성하는 (1)이 허용된다고 가정해보자. (List<String>[] stringsList = new List[1];)
- (2)는 원소가 하나인 List<Integer>를 생성한다.
- (3)은 (1)에서 생성한 List<String>의 배열을 Object 배열에 할당한다. 배열은 공변이니 아무 문제없다.
- (4)는 (2)에서 생성한 List<Integer>의 인스턴스를 Object 배열의 첫 원소로 저장한다.
- 제네릭은 소거 방식으로 구현되어서 이 역시 성공한다. 즉, 런타임에는 List<Integer> 인스턴스의 타입은 단순히 List가 되고, List<Integer>[] 인스턴스 타입은 List[]가 된다. 따라서 (4)에서도 ArrayStoreException을 일으키지 않는다.


이제부터가 문제다. List<String> 인스턴스만 담겠다고 선언한 stringLists 배열에는 지금 List<Integer> 인스턴스가 저장돼 있다. 그리고 (5)는 이 배열의 처음 리스트에서 첫 원소를 꺼내려한다. 컴파일러는 꺼낸 원소를 자동으로 String으로 형변환하는데, 이 원소는 Integer이므로 런타임에 ClassCastException이 발생한다.

정상적으로 동작하려면 (3)에서 List<Integer>가 아닌 List<String>을 넣어줘야 한다. 이런 일을 방지하려면 (제네릭 배열이 생성되지 않도록) (1)에서 컴파일 오류를 내야 한다.

~~~
List<String>[] stringsList = new List[1]; // (1)에서 컴파일 오류를 내야 함
List<Integer> intList = List.of(42); 
Object[] objects = stringsList;
objects[0] = List.of("abc");
String s = stringsList[0].get(0); 
~~~

<br>

## 핵심 정리
배열과 제네릭에는 매우 다른 타입 규칙이 있다. 배열은 공변이고 실체화되는 반면, 제네릭은 불공변이고 정보가 소거된다.
- 그 결과 배열은 런타임에는 타입 안전하지만 컴파일타임에는 그렇지 않다.
- 그래서 둘을 섞어 쓰기란 쉽지 않다. 둘을 섞어 쓰다가 컴파일 오류나 경고를 만나면, 가장 먼저 배열을 리스트로 대체하는 방법을 적용해보자.

<br>

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/674) 에 있습니다.
