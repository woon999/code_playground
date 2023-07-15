# 33. 타입 안전 이종 컨테이너를 고려하라
제네릭은 Set<E>, Map<K, V> 등의 컬렉션과 ThreadLocal<T>, AtomicReference<T> 등의 단일원소 컨테이너에도 흔히 쓰인다. 이런 모든 쓰임에는 매개변수화 되는 대상은 원소가 아닌 컨테이너 자신이다. 따라서 하나의 컨테이너에서 매개변수화할 수 있는 타입의 수가 제한된다. 컨테이너의 일반적인 용도에 맞게 설계된 것이니 문제될 건 없다. 
- 예컨대 Set에는 원소의 타입을 뜻하는 단 하나의 타입 매개변수만 있으면 되며, Map에는 키와 값의 타입을 뜻하는 2개만 필요한 식이다.

<br>

## 타입 안전 이종 컨테이너 패턴(type safe heterogeneous container pattern)
하지만 더 유연한 수단이 필요할 때도 종종 있다. 예컨대 디비 행, 열이 있는데 행에 위치한 열들의 타입을 안전하게 이용할 수 있으면 좋을 것이다. 

이에 대한 해법은 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공하면 된다. 이렇게 하면 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해줄 것이다. 이러한 설계 방식을 타입 안전 이종 컨테이너 패턴(type safe heterogeneous container pattern)이라 한다.

### 예시) Favorites 클래스
타입별로 즐겨 찾는 인스턴스를 검색할 수 있는 Favorites 클래스를 생각해보자. 각 타입의 Class 객체를 매개변수화한 키 역할로 사용하면 되는데, 이 방식은 class의 클래스가 제네릭이기 때문에 가능하다.
- class 리터럴 타입은 Class가 아닌 Class<T>이다. 예컨대 String.class의 타입은 Class<String>이고 Integer.class의 타입은 Class<Integer>인식이다.
- 한편, 컴파일타임 타입 정보와 런타임 타입 정보를 알아내기 위해 메서드들이 주고받는 class 리터럴 타입 토큰(type token)이라 한다.
~~~
// [ 타입 안전 이종 컨테이너 패턴 - API ]
public class Favorites {
   public <T> void putFavorite(Class<T> type, T instance);
   public <T> T getFavorite(Class<T> type);
}
~~~

<br>
	
그리고 다음은 앞의 Favorites 클래스를 사용하고 있는 예시다. 즐겨 찾는 String, Integer, Class 인스턴스를 저장, 검색, 출력하고 있다.
~~~
// [ 타입 안전 이종 컨테이너 패턴 - 클라이언트 ]
public static void main(String[] args) {
   Favorites f = new Favorites();
   f.putFavorite(String.class, "Java");
   f.putFavorite(Integer.class, 0xcafebabe);
   f.putFavorite(Class.class, Favorites.class);

   String s = f.getFavorite(String.class);
   int i = f.getFavorite(Integer.class);
   Class<?> c = f.getFavorite(Class.class);

   System.out.printf("%s %x %s%n", s, i, c.getName());
}
~~~

<br>

기대한 대로 이 프로그램은 “Java cafebabe Favorites”를 출력한다. Favorites 인스턴스는 타입 안전하다. String을 요청했는데 Integer를 반환하는 일은 절대 없다. 또한 모든 키의 타입은 제각각이라, 일반적인 맵과 달리 여러 가지 타입의 원소를 담을 수 있다. 따라서 Favorites는 타입 안전 이종 컨테이너라 할 만 하다.

Favorites 구현은 매우 간단하다. 
~~~
// [ 타입 안전 이종 컨테이너 패턴 - 구현 ]
public class Favorites {
   private Map<Class<?>, Object> favorites = new HashMap<>();
   public <T> void putFavorite(Class<T> type, T instance){
      favorites.put(Objects.requireNonNull(type), instance);
   }

   public <T> T getFavorite(Class<T> type){
      return type.cast(favorites.get(type));
   }

   public static void main(String[] args) {
			// ...
   }
}
~~~

<br>

### Map<Class<?>, Object> favorites
Favorites가 사용하는 private 맵 변수인 favorites의 타입은  Map<Class<?>, Object>이다. 
- 비한정적 와일드카드 타입이라 이 맵 안에 아무것도 넣을 수 없다고 생각할 수 있지만, 사실은 그 반대다. 와일드카드 타입이 중첩(nested)되었다는 점을 깨달아야 한다. 맵이 아니라 키가 와일드카드 타입인 것이다.
- 이는 모든 키가 서로 다른 매개변수화 타입일 수 있다는 뜻으로, 첫 번째는 Class<String>, 두 번째는 Class<Integer>식으로 될 수 있다. 다양한 타입을 지원하는 힘이 여기서 나온다.

그 다음으로 맵의 값 타입은 단순히 Object라는 것이다. 
- 무슨 뜻인고 하니, 이 맵은 키와 값 사이의 타입 관계를 보증하지 않는다는 말이다. 즉, 모든 값이 키로 명시한 타입임을 보증하지 않는다. 사실 자바의 타입 시스템에서는 이 관계를 명시할 방법이 없다.
- 하지만 우린 이 관계가 성립함을 알고 있고, 즐겨찾기를 검색할 때 그 이점을 누리게 된다.

### putFavorite
putFavorite 구현은 아주 쉽다. 주어진 Class 객체와 즐겨찾기 인스턴스를 favorites에 추가해 관계를 지으면 끝이다. 말했듯이, 키와 값 사이의 ‘타입 링크(type linkage)’ 정보는 버려진다. 
- 즉, 그 값이 그 키 타입의 인스턴스라는 정보가 사라진다. 하지만 getFavorite 메서드에서 이 관계를 되살릴 수 있으니 상관없다.

### getFavorite
getFavorite 코드는 먼저, 주어진 Class 객체에 해당하는 값을 favorites 맵에서 꺼낸다. 이 객체가 바로 반환해야 할 객체가 맞지만, 잘못된 컴파일타임 타입을 가지고 있다. 이 객체의 타입은 (favorites 맵의 값 타입인)Objcet이나, 우리는 이를 T로 바꿔 반환해야 한다. 
- 따라서 getFavorite 구현은 Class와 cast 메서드를 사용해 이 객체 참조를 Class 객체가 가리키는 타입으로 동적 형변환한다.

### cast 메서드 사용 이유
cast 메서드는 형변환 연산자의 동적 버전이다. 이 메서드는 단순히 주어진 인수가 Class 객체가 알려주는 타입의 인스턴스인지를 검사한 다음, 맞다면 그 인수를 그대로 반환하고, 아니면 ClassCaastException을 던진다. 그런데, favorites 맵 안의 값은 해당 키의 타입과 항상 일치하기 때문에 클라이언트 코드가 항상 깔끔히 컴파일됨을 알 수 있다. 따라서 cast메서드가 ClassCaastException 예외를 던질 일은 없다.

그러면 cast는 단지 인수를 그대로 반환하기만 하는데 왜 사용하는 걸까? 이유는 **cast 메서드의 시그니처가 Class 클래스가 제네릭이라는 이점을 활용하기 위해서이다.** 

다음 코드에서 보듯 cast의 반환 타입은 Class 객체의 타입 매개변수와 같다. 이것이 바로 getFavorite 메서드에 필요한 기능으로, T로 비검사 형변환하는 손실 없이도 Favorites를 타입 안전하게 만드는 비결이다. 

~~~
public final class Class<T>{
		public T cast(Object obj) {
		    if (obj != null && !isInstance(obj))
		        throw new ClassCastException(cannotCastMsg(obj));
		    return (T) obj;
		}
}
~~~

<br> 

지금이 Favorites 클래스에는 알아두어야 할 제약이 두 가지 있다. 

### 제약 1. 악의적인 클라이언트가 Class 객체를 (제네릭이 아닌) 로 타입(아이템 26)으로 넘기면 Favorites 인스턴스의 타입 안전성이 쉽게 깨진다
하지만 이렇게 짜여진 클라이언트 코드에서는 컴파일할 때 비검사 경고가 뜰 것이다.
- HashSet과 HashMap 등의 일반 컬렉션 구현체에도 똑같은 문제가 있다. 예컨대 HashSet의 로 타입을 사용하면 HashSet<Integer>에 String을 넣는 건 아주 쉬운 일이다. 그렇기는 하지만, 이 정도의 문제를 감수하겠다면 런타임 타입 안전성을 얻을 수 있다.

~~~
HashSet set = new HashSet();
set.add("1"); // String
set.add(1); // Integer
~~~



Favorites가 타입 불변식을 어기는 일이 없도록 보장하려면 putFavorites 메서드에서 인수로 주어진 instance의 타입이 type으로 명시한 타입과 같은지 확인하면 된다.
~~~
// [ 동적 형변환으로 런타임 타입 안전성 확보 ]
public <T> void putFavorite(Class<T> type, T instance){
   favorites.put(Objects.requireNonNull(type), type.cast(instance));
}
~~~

Collections에는 이러한 방식을 적용한 checkedSet, checkedList, checkedMap 같은 메서드가 있다.

<br>

### 제약 2.  실체화 불가 타입(아이템 28)에는 사용할 수 없다.
다시 말해, 즐겨 찾는 String이나 String[]은 저장할 수 있어도 즐겨 찾는 List<String>은 저장할 수 없다. List<String>을 저장하려는 코드는 컴파일되지 않을 것이다. List<String>용 Class 객체를 얻을 수 없기 때문이다.

List<String>과 List<Integer>는 List.class라는 같은 Class 객체를 공유하므로, 만약 List<String>.class와 List<Integer>.class를 허용해서 둘 다 똑같은 타입의 객체 참조를 반환한다면 Favorites 객체의 내부는 아수라장이 될 것이다. 이 두 번째 제약에 대한 완벽히 만족스러운 우회로는 없다.

<br>
	
---

### 슈퍼 타입 토큰

두 번째 제약을 슈퍼 타입 토큰(super type tokeN)으로 해결하려는 시도도 있다. 슈퍼 타입 토큰은 자바 업계 거장인 닐 개프터가 고안한 방식으로, 실제로 아주 유용하여 스프링 프레임워크에서는 아예 ParameterizedTypeReference라는 클래스로 미리 구현해놓았다. 본문 예제의 Favorites에 슈퍼 타입 토큰을 적용하면 다음 코드처럼 제네릭 타입도 문제없이 저장할 수 있다.

~~~
Favorites f = new Favorites();
List<String> pets = Arrays.asList("개", "고양이", "앵무");
f.putFavorite(new TypeRef<List<String>>(){}, pets);
List<String> listOfStrings = f.getFavorite(new TypeRef<List<String>>(){});
```

닐 개프터의 슈퍼타입 토큰 글 : [http://gafter.blogspot.com/2006/12/super-type-tokens.html](http://gafter.blogspot.com/2006/12/super-type-tokens.html)

토비님이 다룬 슈퍼 타입 토큰: [https://www.youtube.com/watch?v=01sdXvZSjcI](https://www.youtube.com/watch?v=01sdXvZSjcI)
~~~
---

<br>

## 한정적 타입 토큰
Favorites가 사용하는 타입 토큰은 비한정적이다. 즉, getFavorite과 putFavorite은 어떤 Class 객체든 받아들인다. 때로는 이 메서드들이 허용하는 타입을 제한하고 싶을 수 있는데, 한정적 타입 토큰을 활용하면 가능하다.
- **한정적 타입 토큰**이란 단순히 한정적 타입 매개변수나 한정적 와일드카드를 사용하여 표현 가능한 타입을 제한하는 타입 토큰이다.

애너테이션 API는 한정적 타입 토큰을 적극적으로 사용한다. 예를 들어 다음은 AnnotatedElement 인터페이스에 선언된 메서드로, 대상 요소에 달려 있는 애너테이션을 런타임에 읽어 오는 기능을 한다. 이 메서드는 리플렉션의 대상이 되는 타입들, 즉 클래스(java.lang.Class<T>), 메서드(java.lang.refelct.Method), 필드(java.lang.reflect.Field) 같이 프로그램 요소를 표현하는 타입을 구현한다.

~~~
public interface AnnotatedElement {
		<T extends Annotation> T getAnnotation(Class<T> annotationType);
}
~~~

여기서 annotationType 인수는 애너테이션 타입을 뜻하는 한정적 타입 토큰이다. 이 메서드는 토큰으로 명시한 타입의 애너테이션이 대상 요소에 달려 있다면 그 애너테이션을 반환하고, 없다면 null을 반환한다. 즉, 애너테이션된 요소는 그 키가 애너테이션 타입인, 타입 안전 이종 컨테이너인 것이다.

### 형변환 안전하게 수행해주는 asSubclass 메서드
Class<?> 타입의 객체가 있고, 이를 getAnnotation처럼 한정적 타입 토큰을 받는 메서드에 넘기려면 어떻게 해야 할까? 객체를 Class<? extends Annotation>으로 형변환할 수 있지만, 이 형변환은 비검사이므로 컴파일하면 경고가 뜰 것이다(아이템 27).

운 좋게도 Class 클래스가 이런 형변환을 안전하게 그리고 동적으로 수행해주는 인스턴스 메서드를 제공한다. 바로 asSubclass 메서드로, 호출된 인스턴스 자신의 Class 객체를 인수가 명시한 클래스로 형변환한다. 형변환에 성공하면 인수로 받은 클래스 객체를 반환하고, 아니면 ClassCastException을 던진다. 다음은 컴파일 시점에 타입을 알 수 없는 애너테이션을 asSubclass 메서드를 사용해 런타임에 읽어내는 예다. 이 메서드는 오류나 경고 없이 컴파일된다.
~~~
// [ asSubclass를 사용해 한정적 타입 토큰을 안전하게 형변환한다. ]
static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName){
   Class<?> annotationType = null; // 비한정적 타입 토큰
   try{
      annotationType = Class.forName(annotationTypeName);
   } catch (Exception e){
      throw new IllegalArgumentException(e);
   }

   return element.getAnnotation(annotationType.asSubclass(Annotation.class));
}
~~~

<br> 

## 핵심 정리
- 컬렉션 API로 대표되는 일반적인 제네릭 형태에는 한 컨테이너가 다룰 수 있는 타입 매개변수의 수가 고정되어 있다.
- 하지만 컨테이너 자체가 아닌 키를 타입 매개변수로 바꾸면 이런 제약이 없는 타입 안전 이종 컨테이너를 만들 수 있다.
- 타입 안전 이종 컨테이너는 Class를 키로 쓰며, 이런 식으로 쓰이는 Class 객체를 타입 토큰이라 한다. 또한, 직접 구현한 키 타입도 쓸 수 있다.
- 예컨대 데이터베이스의 행(컨테이너)를 표현한 DatabaseRow 타입에는 제네릭 타입인 Column<T>를 키로 사용할 수 있다.


--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/699) 에 있습니다.
