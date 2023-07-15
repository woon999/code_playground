# 2. 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

- 많은 클래스가 하나 이상의 자원에 의존한다. 
- 가령 맞춤법 검사기는 사전(dictionary)에 의존하는데, 이런 클래스를 정적 유틸리티 클래스(아이템 4)로 구현한 모습을 드물지 않게 볼 수 있다.

- 정적 유틸리티를 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
~~~
public class SpellChecker {
   private static final Lexicon dictionary= new Lexicon();

   private SpellChecker() {}

   public boolean isValid(String word){
      return Pattern.matches("^[a-zA-Z]*$",word);
   }
}
~~~

- 비슷하게, 싱글턴(아이템 3)으로 구현하는 경우도 흔하다.
~~~
public class SpellChecker {
   private static final Lexicon dictionary= new Lexicon();

   private SpellChecker() {}

	 public static SpellChecker INSTANCE = new SpellChecker();

   public boolean isValid(String word){
      return Pattern.matches("^[a-zA-Z]*$",word);
   }
}
~~~

- SpellChecker가 여러 사전을 사용할 수 있도록 만들어보자. 
- 간단히 dictionary 필드에서 final 한정자를 제거하고 다른 사전으로 교체하는 메서드를 추가할 수 있지만, 아쉽게도 이 방식은 어색하고 오류를 내기 쉬우며 멀티쓰레드 환경에서는 쓸 수 없다.
 
 <br>
 
## 인스턴스를 생성자에 필요한 자원 넘겨주기
- 대신 클래스(SpellChecker)가 여러 자원 인스턴스를 지원해야 하며, 클라이언트가 원하는 자원(dictionary)을 사용해야 한다. 
- 이 조건을 만족하는 간단한 패턴이 있으니, 바로 인스턴스를 생성자에 필요한 자원을 넘겨주는 방식이다. 
- 이는 의존 객체 주입의 한 형태로, 맞춤법 검사기를 생성할 때 의존 객체인 사전을 주입해주면 된다.

~~~
public class SpellChecker {
   private final Lexicon dictionary;

   public SpellChecker(Lexicon dictionary) {
      this.dictionary = Objects.requireNonNull(dictionary);
   }

   public boolean isValid(String word){
      return Pattern.matches("^[a-zA-Z]*$",word);
   }
}
~~~

<br>

## 팩토리 메서드 패턴(Factory Method Pattern)
- 팩토리 메서드 패턴(Factory method pattern)은 객체지향 디자인 패턴이다.
- Factory method는 부모(상위) 클래스에 알려지지 않은 구체 클래스를 생성하는 패턴이며, 자식(하위) 클래스가 어떤 객체를 생성할지를 결정하도록 하는 패턴이기도 하다. 
- 부모(상위) 클래스 코드에 구체 클래스 이름을 감추기 위한 방법으로도 사용한다. [(위키 참고)](https://ko.wikipedia.org/wiki/%ED%8C%A9%ED%86%A0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C_%ED%8C%A8%ED%84%B4)

<br>

자바 8에서 소개한 Supplier<T> 인터페이스가 팩토리를 표현한 완벽한 예다.
- Supplier<T>를 입력으로 받는 메서드는 일반적으로 한정적 와일드카드 타입(bounded wildcard type, 아 이템 31)을 사용해 팩토리의 타입 매개변수를 제한해야 한다.
- 이 방식을 사용해 클라이언트는 자신이 명시한 타입의 하위 타입이라면 무엇이든 생성할 수 있는 팩토리를 넘길 수 있다.


## 핵심 정리
- 클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다. 
- 이 자원들을 클래스가 직접 만들게 해서도 안된다. 대신 필요한 자원을(혹은 그 자원을 만들어주는 팩토리를) 생성자에 (혹은 정적 팩토리나 빌더에) 넘겨주자. 
- 의존 객체 주입이라 하는 이 기법은 클래스의 유연성, 재사용성, 테스트 용이성을 기믹하게 개선해준다.


---

💡 본문은 [개발 블로그](https://loosie.tistory.com/583) 에 있습니다.
