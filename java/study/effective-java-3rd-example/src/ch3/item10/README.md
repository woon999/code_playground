# 10. equals는 일반 규역을 지켜 재정의하라
equals 메서드는 재정의하기 쉬워 보이지만 곳곳에 함정이 도사리고 있어서 자칫하면 끔찍한 결과를 초래한다. 문제를 회피하는 가장 쉬운 길은 아예 재정의하지 않는 것이다. 그냥 두면 그 클래스의 인스턴스는 오직 자기 자신과만 같게 된다.

그러니 다음에서 열거한 상황 중 하나에 해당한다면 재정의하지 않는 것이 최선이다.

#### 1. 각 인스턴스가 본질적으로 고유하다.
- 값을 표현하는 게 아니라 동작하는 개체를 표현하는 클래스가 여기 해당한다. Thread가 좋은 예로, Object의 equals 메서드는 이러한 클래스에 딱 맞게 구현되었다.

#### 2. 인스턴스의 '논리적 동치성(logical equality)'을 검사할 일이 없다.
- java.util.regex.Pattern은 equals를 재정의해서 두 Pattern의 인스턴스가 같은 정규표현식을 나타내는지를 검사하는, 즉 논리적 동치성을 검사하는 방법도 있다.
- 하지만 설계자는 클라이언트가 이 방식을 원하지 않거나 애초에 필요하지 않다고 판단할 수 있다. 설계자가 후자로 판단했다면 Object의 기본 equals만으로 해결된다.

### 3.상위 클래스에서 재정의한 equals가 하위클래스에도 딱 들어맞는다.
- 대부분의 Set 구현체는 AbstractSet이 구현한 equals를 상속받아 쓰고, List 구현체들은 AbstractList로부터, Map 구현체들은 AbstractMap으로부터 상속받아 그대로 쓴다.

#### 4.클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.
- 본인이 위험을 철저히 회피하는 스타일이라 equals가 실수로라도 호출되는 걸 막고 싶다면 다음처럼 구현해두자.

<br>

## 그렇다면 equals를 재정의해야 할 때는 언제인가?
객체 식별성(object identity; 두 객체가 물리적으로 같은가)이 아니라 논리적 동치성을 확인해야 하는데, 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때다.
- 주로 Integer, String과 같은 값 클래스들이 여기 해당한다.
- equals가 논리적 동치성을 확인하도록 재정의해두면, 그 인스턴스는 값을 비교하길 원하는 프로그래머의 기대에 부응함은 물론 Map이 키와 Set의 원소로 사용할 수 있게 된다.


Enum과 같은 경우는 인스턴스가 둘 이상 만들어지지 않음이 보장되는 인스턴스 통제 클래스(아이템 1)이므로 equals를 재정의하지 않아도 된다.
- 어차피 논리적으로 같은 인스턴스가 2개 이상 만들어지지 않으니 논리적 동치성 = 객체 식별성이 된다. 따라서 Object의 equals가 논리적 동치성까지 확인해준다고 볼 수 있다.

<br>

## equals 메서드 재정의 일반 규약
equals 메서드를 재정의할 때는 반드시 일반 규약을 따라야 한다. 다음은 Object 명세에 적힌 규약이다

equals 메서드는 동치관계(equivalence relation)를 구현하며, 다음을 만족한다.
- 반사성(reflexivity) : null이 아닌 모든 참조 값 x에 대해, x.equals(x)는 true이다.
- 대칭성(symmetry) : null이 아닌 모든 참조 값 x, y에 대해, x.equals(y)가 true면 y.equals(x)도 true이다.
- 추이성(transitivity) : null이 아닌 모든 참조 값 x, y, z에 대해, x.equals(y)가 true이고 y.equals(z)도 true이면 x.equals(z)도 true이다.
- 일관성(consistency) : null이 아닌 모든 참조 값 x, y에 대해, x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.
- null-아님 : null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 false이다.

## 양질의 equals 메서드 구현 방법
양질의 equals 메서드 구현 방법을 단계별로 정리해보겠다.

#### == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
- 자기 자신이면 true를 반환한다. 이는 단순한 성능 최적화용으로, 비교 작업이 복잡한 상황일 때 값어치를 할 것이다.

#### instanceof 연산자로 입력이 올바른 타입인지 확인한다.
- 그렇지 않다면 false를 반환한다. 이때의 올바른 타입은 equals가 정의된 클래스인 것이 보통이지만, 가끔은 그 클래스가 구현한 특정 인터페이스가 될 수도 있다.
- Set, List, Map, Map.entry 등의 컬렉션 인터페이스들이 여기에 해당한다.

#### 입력을 올바른 타입으로 형변환한다.
- 앞서 2번에서 instanceof 검사를 했기 때문에 이 단계는 100% 성공했다.

#### 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.
- 모든 필드가 일치하면 true를, 하나라도 다르면 false를 반환한다.
- 2단계에서 인터페이스를 사용했다면 입력의 필드 갑을 가져올 때도 그 인터페이스의 메서드를 사용해야 한다. 타입이 클래스라면 (접근 권한에 따라) 해당 필드에 직접 접근할 수 있다.

<br>

float과 double은 compare메서드로 비교하자. 이를 제외한 기본 타입은 모두 == 연산자로 비교하고 참조 타입은 equals로 비교해주면 된다.
- float과 double은 특수한 부동 소수값을 다뤄야하기 때문이다.
- Float, Double의 equals는 오토박싱을 수반할 수 있어 성능상 좋지 않다.

<br>

복잡한 필드를 가진 클래스인 경우 그 필드의 표준형(canonical form)을 저장해둔 후 표준형끼리 비교하면 훨씬 경제적이다.
- 이 기법은 특히 불변 클래스(아이템 17)에 제격이다.
- 가변 객체라면 값이 바뀔 때마다 표준형을 최신 상태로 갱신해줘야 한다.
 
<br>

어떤 필드를 먼저 비교하냐느가 equals의 성능을 좌우하기도 한다. 최상의 성능을 바란다면 다를 가능성이 더 크거나 비교하는 비용이 싼 (혹은 둘 다 해당하는) 필드를 먼저 비교하자.
- 동기화용 락(lock) 필드 같이 객체의 논리적 상태와 관련없는 필드는 비교하면 안 된다.
- 핵심 필드로부터 계산해낼 수 있는 파생 필드 역시 굳이 비교할 필요는 없지만, 파생 필드를 비교하는 쪽이 더 빠를 때도 있다. 파생 필드가 객체 전체의 상태를 대표하는 상황이 그렇다.
- 자신의 영역을 캐시해두는 Polygon 클래스가 있다고 하자. 그러면 모든 변과 정점을 일일이 비교할 필요 없이 캐시해둔 영역만 비교하면 결과를 곧바로 알 수 있다.

<br>

## AutoValue 프레임워크
quals(hashcode도 마찬가지)를 작성하고 테스트하는 일은 지루하고 이를 테스트하는 코드도 항상 뻔하다. 다행히 구글이 만든 AutoValue 프레임워크라는 오픈소스가 이 작업을 대신해줄 수 있다. 클래스에 애너테이션 하나만 추가하면 AutoValue가 이 메서드들을 알아서 작성해주며, 직접 작성하는 것과 근본적으로 똑같은 코드를 만들어준다.
- 대다수의 IDE도 같은 기능을 제공하지만 생성된 코드가 AutoValue만큼 깔끔하거나 읽기 좋지는 않다.
- 또한 IDE는 나중에 클래스가 수정된 걸 자동으로 알아채지는 못하나 테스트 코드를 작성해둬야 한다.
- 이런 단점을 감안하더라도 사람이 직접 작성하는 것보다는 IDE에 맡기는 편이 낫다.
~~~
@AutoValue
public abstract class AutoValueMoney {
   public abstract String getCurrency();
   public abstract long getAmount();

   public static AutoValueMoney create(String currency, long amount) {
      return new AutoValue_AutoValueMoney(currency, amount);
   }

   public static void main(String[] args) {
      AutoValueMoney won =create("won", 100);
      AutoValueMoney won2 =create("won", 100);
      System.out.println(won.equals(won2));
   }
}
~~~


<br>

## 핵심 정리
꼭 필요한 경우가 아니면 equals를 재정의하지 말자. 많은 경우에 Object의 equals가 여러분이 원하는 비교를 정확히 수행해준다. 재정의해야 할 떄는 그 클래스의 핵심 필드를 모두 빠짐없이, 다섯 가지 규약을 확실히 지켜가며 비교해야 한다.
    
     
--- 


💡 본문은 [개발 블로그](https://loosie.tistory.com/604) 에 있습니다.

