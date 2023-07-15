# 32. 제네릭과 가변인수를 함께 쓸 때는 신중하라


## 가변인수 구현방식의 허점
가변인수(varargs) 메서드(아이템 53)와 제네릭은 자바 5때 함께 추가되었으니 서로 잘 어우러지리라 기대하겠지만, 슬프게도 그렇지 않다.

가변인수는 메서드에 넘기는 인수의 개수를 클라이언트가 조절할 수 있게 해주는데, 구현 방식에 허점이 있다.

1. 가변인수 메서드를 호출하면 가변인수를 담기 위한 배열이 자동으로 하나 만들어진다.
2. 그런데 내부로 감춰야 했을 이 배열을 그만 클라이언트에 노출하는 문제가 생겼다.
3. 그 결과 varargs 매개변수에 제네릭이나 매개변수화 타입이 포함되면 알기 어려운 컴파일 경고가 발생한다.

아이템 28에서 실체화 불가 타입은 런타임에는 컴파일타임보다 타입 관련 정보를 적게 담고 있음을 배웠다. 그리고 거의 모든 제네릭과 매개변수화 타입은 실체화되지 않는다. 

메서드를 선언할 때 실체화 불가 타입으로 varargs 매개변수를 선언하면 컴파일러가 경고를 보낸다. 가변인수 메서드를 호출할 때도 varargs 매개변수가 실체화 불가 타입으로 추론되면, 그 호출에 대해서도 경고를 낸다.

<br>

### 제네릭과 varargs을 혼용하면 타입 안전성이 깨진다
매개변수화 타입의 변수가 타입이 다른 객체를 참조하면 힙 오염이 발생한다. 이렇게 다른 타입 객체를 참조하는 상황에서는 컴파일러가 자동 생성한 형변환이 실패할 수 있으니, 제네릭 타입 시스템이 약속한 타입 안전성의 근간이 흔들려버린다.
~~~
// [ 제네릭과 varargs를 혼용하면 타입 안전성이 깨진다! ]
static void dangerous(List<String>... stringLists){
   List<Integer> intList = List.of(42);
   Object[] objects = stringLists;
   objects[0] = intList; // 힙 오염 발생
   String s = stringLists[0].get(0); // ClassCastException
}
~~~

이 메서드에서는 형변환하는 곳이 보이지 않는데도 인수를 건네 호출하면 ClassCastException을 던진다. 마지막 줄에 컴파일러가 생성한 (보이지 않는) 형변환이 숨어 있기 때문이다. 이처럼 타입 안전성이 깨지니 **제네릭 varargs 배열 매개변수에 값을 저장하는 것은 안전하지 않다.**

<br>

### 제네릭 varargs 매개변수를 받는 메서드를 선언할 수 있게 한 이유는?
아이템 28에서 다룬 코드는 컴파일 에러를 내는데, 위의 코드는 그냥 경고로 끝난다.

~~~
// 제네릭 배열 생성 불가 컴파일 에러
static void dangerous_item28(List<String>[] stringLists){
   List<Integer> intList = List.of(42);
   // ...
}

// heap pollution 경고
static void dangerous(List<String>... stringLists){
   List<Integer> intList = List.of(42);
   // ...
}
~~~

제네릭 배열을 직접 생성(아이템 28)하는 것은 허용하지 않는데 제네릭 varargs 매개변수를 받는 메서드를 선언할 수 있게 한 이유는 무엇일까? 그 답은 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 메서드가 실무에서 매우 유용하기 때문이다. 그래서 언어 설계자는 이 모순을 수용하기로 했다.

실제로 자바 라이브러리도 이런 메서드를 여럿 제공하는데, Arrays.asList(T... a), Collections.addAll(Collection<? super T> c, T... elements), EnumSet.of(E first, E... rest)가 대표적이다. 다행히 이 메서드들은 타입 안전하다.

<br>

## @SafeVarargs 애너테이션 - 메서드 타입 안전함을 보장하는 장치

자바 7전에는 @SuppressWarnings(”unchecked”) 애너테이션을 달아 경고를 숨겨야했다. 지루한 작업이고, 가독성을 떨어뜨리고, 때로는 진짜 문제를 알려주는 경고마저 숨기는 안 좋은 결과로 이어졌다.

자바 7에서는 @SafeVarargs 애너테이션이 추가되어 제네릭 가변인수 메서드 작성자가 클라이언트 측에서 발생하는 경고를 숨길 수 있게 되었다. **@SafeVarargs 애너테이션은 메서드 작성자가 그 메서드가 타입 안전함을 보장하는 장치다.**

<br> 

### 메서드가 안전한지 확신하는 방법
가변인수 메서드를 호출할 때 vararags 매개변수를 담는 제네릭 배열이 만들어진다는 사실을 기억하자. 메서드가 이 배열에 아무것도 저장하지 않고(그 매개변수들을 덮어쓰지 않고) 그 배열의 참조가 밖으로 노출되지 않는다면(신뢰할 수 없는 코드가 배열에 접근할 수 없다면) 타입 안전한다.

달리 말하면, 이 varargs 매개변수 배열이 호출자로부터 그 메서드로 순수하게 인수들을 전달하는 일만 한다면(varargs의 목적대로만 쓰인다면) 그 메서드는 안전하다.

<br>

## 힙 오염 전염 - 타입 안전성 깨짐 주의
이때, varargs 매개변수 배열에 아무것도 저장하지 않고도 타입 안정성을 깰 수도 있으니 주의해야 한다.

다음 코드는 가변인수로 넘어온 매개변수들을 배열에 담아 반환하는 제네릭 메서드다. 얼핏 보면 편리한 유틸리티로 보이지만, 보기와 달리 위험하다.
~~~
[ 자신의 제네릭 매개변수 배열의 참조를 노출한다. - 안전하지 않다! ]
static <T> T[] toArray(T... args){
   return args;
}
~~~

이 메서드가 반환하는 배열의 타입은 이 메서드에 인수를 넘기는 컴파일타임에 결정되는데, 그 시점에는 컴파일러에게 충분한 정보가 주어지지 않아 타입을 잘못 판단할 수 있다. 따라서 자신의 varargs 매개변수 배열을 그대로 반환하면 힙 오염을 이 메서드를 호출한 쪽의 콜스택으로까지 전이하는 결과를 낳을 수 있다.

구체적인 예를 보자. 다음 메서드는 T 타입 인수 3개를 받아 그중 2개를 무작위로 골라 담은 배열을 반환한다. 이 메서드는 제네릭 가변인수를 받는 toArray 메서드를 호출한다는 점만 빼면 위험하지 않고 경고도 내지 않을 것이다.

- 이 메서드를 본 컴파일러는 toArray에 넘길 T 인스턴스 2개를 담을 varargs 매개변수 배열을 만드는 코드를 생성한다.
- pickTwo에 어떤 타입의 객체를 넘기더라도 담을 수 있는 가중 구체적인 타입이기 때문에 이 코드가 만드는 배열의 타입은 Object[]이다.
- 그리고 toArray가 돌려준 이 배열이 그대로 pickTwo를 호출한 클라이언트까지 전달된다.
- 즉, pickTwo는 항상 Object[] 타입 배열을 반환한다.

~~~
static <T> T[] pickTwo(T a, T b, T c){
   switch (ThreadLocalRandom.current().nextInt(3)){
      case 0: returntoArray(a, b);
      case 1: returntoArray(a, c);
      case 2: returntoArray(b, c);
   }
   throw new AssertionError(); // 도달할 수 없다.
}
~~~

이 코드는 컴파일 경고는 발생하지 않지만 실행하면 ClassCastException을 던진다. 이유는 바로 pickTwo 반환값을 attributes에 저장하기 위해 String[]로 형변환하는 코드를 컴파일러가 자동 생성한다는 점을 놓쳤다.  Object[]는 String[]의 하위 타입이 아니므로 이 형변환은 실패한다.

~~~
public static void main(String[] args) {
   String[] attributes =pickTwo("good", "fast", "cheap");
}
~~~

이 실패가 다소 황당하게 느껴질 수도 있다. 힙 오염을 발생시킨 진짜 원인은 toArray로부터 두 단계나 떨어져 있고, varargs 매개변수 배열은 실제 매개변수가 저장된 후 변경된 적도 없으니 말이다.

<br>
 
## 제네릭 varargs 매개변수 배열에 다른 메서드가 접근해도 안전한 경우
이 예는 제네릭 varargs 매개변수 배열에 다른 메서드가 접근하도록 허용하면 안전하지 않다는 점을 다시 한번 상시킨다. 단 예외가 두 가지있다.
1. @SafeVarargs로 제대로 에노테이트된 또 다른 varargs 메서드에 넘기는 것은 안전하다.
2. 그저 이 배열 내용의 일부 함수를 호출만 하는 (varargs를 받지 않는) 일반 메서드에 넘기는 것도 안전하다.
~~~
// [ 제네릭 varargs 매개변수를 안전하게 사용하는 메서드 ]
@SafeVarargs
static <T> List<T> flatten(List<? extends T>... lists){
   List<T> result = new ArrayList<>();
   for(List<? extends T> list : lists){
      result.addAll(list);
   }
   return result;
}
~~~

## @SafeVarargs 애너테이션 사용 규칙
@SafeVarargs 애너테이션을 사용해야 할 때를 정하는 규칙은 간단하다. 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 모든 메서드에 @SafeVarargs를 달라. 그래야 사용자를 헷갈리게 하는 컴파일러 경고를 없앨 수 있다.

이 말은 안전하지 않은 varargs 메서드는 절대 작성해서는 안 된다는 뜻이기도 하다. 통제할 수 있는 메서드 중 제네릭 varargs 매개변수를 사용하며 힙 오염 경고가 뜨는 메서드가 있다면, 그 메서드가 진짜 안전하지 점검하라. 정리하자면, 다음 두 조건을 모두 만족하는 제네릭 varargs 메서드는 안전하다. 둘 중 하나라도 어겼다면 수정하라!
- varargs 매개변수 배열에 아무것도 저장하지 않는다.
- 그 배열(혹은 복제본)을 신뢰할 수 없는 코드에 노출하지 않는다.

<br>

## @SafeVarargs가 유일한 답은 아니다 - 타입 교체하기
@SafeVarargs 애너테이션이 유일한 정답은 아니다. 아이템 28의 조언을 따라 (실체는 배열인) varargs 매개변수를 List 매개변수로 바꿀 수도 있다. 이 방식을 앞서의 flatten 메서드에 적용하면 다음처럼 된다. 매개변수 선언만 수정했음에 주목하자.
~~~
[ 제네릭 varargs 매개변수를 List로 대채한 예 - 타입 안전하다. ]
static <T> List<T> flatten_typesafe(List<List<? extends T>> lists){
   List<T> result = new ArrayList<>();
   for(List<? extends T> list : lists){
      result.addAll(list);
   }
   return result;
}

List<String> friends = List.of("friend");
List<String> romans = List.of("abc");
List<String> countrymen = List.of("zzz");
List<List<String>> audience = flatten(List.of(friends, romans, countrymen));
~~~

위 코드는 List.of에도 @SafeVarargs가 달려있기 떄문에 가능하다. 
- 이 방식의 장점은 컴파일러가 이 메서드의 타입 안전성을 검증할 수 있다는 데 있다. @SafeVarargs 애너테이션을 우리가 직접 달지 않아도 되며, 실수로 안전하다고 판단할 걱정도 없다.
- 단점이라면 클라이언트 코드가 살짝 지저분해지고 속도가 조금 느려질 수도 있다는 정도다.

이 방식은 toArray처럼 varargs 메서드를 안전하게 작성하는 게 불가능한 상황에서도 쓸 수 있다. 결과 코드는 배열 없이 제네릭만 사용하므로 타입 안전하다.

~~~
static <T> List<T> pickTwo(T a, T b, T c){
		switch (ThreadLocalRandom.current().nextInt(3)){
			case 0: return List.of(a, b);
			case 1: return List.of(a, c);
			case 2: return List.of(b, c);
		}
		throw new AssertionError(); 
	}

List<String> attributes =pickTwo("good", "fast", "cheap");
~~~

## 핵심 정리
가변인수와 제네릭은 궁합이 좋지 않다. 가변인수 기능은 배열을 노출하여 추상화가 완벽하지 못하고, 배열과 제네릭의 타입 규칙이 서로 다르기 때문이다.

제네릭 varargs 매개변수는 타입 안전하지는 않지만, 허용된다. 메서드에 제네릭(혹은 매개변수화된) varargs 매개변수를 사용하고자 한다면, 먼저 그 메서드가 타입 안전한지 확인한 다음 @SafeVarargs 애너테이션을 달아 사용하는 데 불편함이 없게끔 하자.

<br>

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/695) 에 있습니다.
