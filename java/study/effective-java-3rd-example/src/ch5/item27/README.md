# 27. 비검사 경고를 제거하라
제네릭을 사용하기 시작하면 수많은 컴파일러 경고를 보게 될 것이다. 비검사 형변환 경고, 비검사 메서드 호출 경고, 비검사 매개변수화 가변인수 타입 경고, 비검사 변환 경고 등이다. 제네릭에 익숙해질수록 마주치는 경고 수는 줄겠지만 새로 작성한 코드가 한번에 깨끗하게 컴파일되리라 기대하지는 말자. 

대부분의 비검사 경고는 쉽게 제거할 수 있다. 코드를 다음처럼 잘못 작성했다고 해보자.

~~~
Set<Lark> exaltation = new HashSet();
~~~

그러면 컴파일러는 무엇이 잘못됐는지 친절히 설명해줄 것이다. (javac 명령줄 인수에 -Xlint:uncheck 옵션을 추가해야 한다.) 
컴파일러가 알려준대로 수정하면 경고가 사라진다. 자바 7부터는 다이아몬드 연산자(<>)만으로 해결할 수 있다. 그러면 컴파일러가 올바른 실제 타입 매개변수를 추론해준다.

~~~
Set<Lark> exaltation = new HashSet<>();
~~~

<br>

## 할 수 있는 한 모든 비검사 경고를 제거하라.
경고를 모두 제거한다면 그 코드는 타입 안정성이 보장된다. 즉, 런타임에 ClassCastException이 발생할 일이 없고, 의도한 대로 잘 동작하리라 확신할 수 있다.

<br>

## @SuppressWarnings(”unchecked”)
경고를 제거할 수 없지만 타입이 안전하다고 확실할 수 있다면 @SuppressWarnings(”unchecked”) 애너테이션을 달아 경고를 숨기자.
- 단, 타입 안전함을 검증하지 않은 채 경고를 숨기면 스스로에게 잘못된 보안 인식을 심어주는 꼴이다. 런타임에 여전히 ClassCastException을 던질 것이다.
- 한편, 안전하다고 검증된 비검사 경고를 그대로 두면, 진짜 문제를 알리는 경고가 묻힐 수도 있다.

### 항상 가능한 좁은 범위에 적용하자
보통은 변수 선언, 아주 짧은 메서드, 혹은 생성자가 될 것이다. 자칫 심각한 경고를 놓칠 수 있으니 절대 클래스 전체에 적용해서는 안 된다. 
- 한 줄이 넘는 메서드나 생성자에 달린 @SuppressWarnings 애너테이션을 발견하면 지역변수 선언 쪽으로 옮기자
- 이를 위해 지역변수를 새로 선언해야할 수도 있지만 그럴만한 값어치가 있을 것이다.

예로 ArrayList에 있는 toArray 메서드를 예로 생각해보자.
~~~
public <T> T[] toArray(T[] a){
   if(a.length < size){
      return (T[])Arrays.copyOf(elements, size, a.getClass());
   }
   System.arraycopy(elements, 0, a, 0, size);
   if(a.length > size){
      a[size] = null;
   }
   return a;
}
~~~


<br>

ArrayList를 컴파일하면 unchecked 경고가 발생한다.

애너테이션은 선언에만 달 수 있기 때문에 return 문에는 @SuppressWarnings를 다는 게 불가능하다. 그렇다면 메서드 전체에 달고 싶겠지만, 범위가 필요 이상으로 넓어지니 자제하자. 그 대신 반환값을 담을 지역변수를 하나 선언하고 그 변수에 애너테이션을 달아주자. 다음은 toArray를 이렇게 수정한 모습이다.

~~~
// 지역변수를 추가해 @SuppressWarnings의 범위를 좁힌다.
public static <T> T[] toArray(T[] a) {
   if (a.length <size) {
      // 생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[]로 같으므로 올바른 형변환이다.
      @SuppressWarnings("unchecked")
      T[] result = (T[])Arrays.copyOf(elements,size, a.getClass());
      return result;
   }
   System.arraycopy(elements, 0, a, 0,size);
   if (a.length >size) {
      a[size] = null;
   }
   return a;
}
~~~

@SuppressWarnings(”unchecked”) 애너테이션을 사용할 때면 그 경고를 무시해도 안전한 이유를 항상 주석으로 남겨야 한다. 다른 사람이 그 코드를 이해하는 데 도움이 되며, 더 중요하게는, 다른 사람이 그 코드를 잘못 수정하여 타입 안정성을 잃는 상황을 줄여준다. 코드가 안전한 근거가 쉽게 떠오르지 않더라도 끝까지 포기하지 말자. 근거를 찾는 중에 그 코드가 사실은 안전하지 않다는 것을 발견할 수도 있으니 말이다.

<br>

## 핵심 정리
비검사 경고는 중요하니 무시하지 말자. 모든 비검사 경고는 런타임에 ClassCastException을 일으킬 수 있는 잠재적 가능성을 뜻하니 최선을 다해 제거하라. 경고를 없앨 방법을 찾지 못하겠다면, 그 코드가 타입 안전함을 증명하고 가능한 한 범위를 좁혀 @SuppressWarnings(”unchecked”) 애너테이션으로 경고를 숨겨라. 그런 다음 경고를 숨기기로 한 근거를 주석으로 남겨라.

<br>

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/672) 에 있습니다.
