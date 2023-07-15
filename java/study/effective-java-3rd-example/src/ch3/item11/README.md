# 11. equals를 재정의하려거든 hashCode도 재정의해라
equals를 재정의한 클래스 모두에서 hashCode도 재정의해야 한다. 그렇지 않으면 hashCode 일반 규약을 어기게 되어 해당 클래스의 인스턴스를 HashMap이나 HashSet 같은 컬렉션의 원소로 사용할 때 문제를 일으킬 것이다. 다음은 Object 명세에서 발췌한 규약이다.
그러니 다음에서 열거한 상황 중 하나에 해당한다면 재정의하지 않는 것이 최선이다.
- equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다. 단, 애플리케이션을 다시 실행한다면 이 값이 달라져도 상관없다.
- equals(Object)가 두 객체를 같다고 판단했다면, 두 객채의 hashCode는 똑같은 값을 반환해야 한다.
- equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다.


## 최악의 (하지만 적법한) hashCode 구현 - 사용 금지!
 ~~~
@Override public int hashCode() {
   return 42;
}
~~~
- 모든 객체에 같은 값만 내어주기 때문에 해시테이블 버킷 하나에 담겨 마치 연결리스트(linked list)처럼 동작한다. 그 결과 평균 수행 시간이 O(1)인 해시테이블이 O(n)으로 느려져서, 객체가 많아지면 도저히 쓸 수 없게 된다.
- 좋은 해시 함수라면 서로 다른 인스턴스에 다른 해시코드를 반환한다. 이것이 바로 hashCode의 세번 째 규약이 요구하는 속성이다.
    - 이상적인 해시 함수는 주어진 인스턴스들을 32비트 정수 범위에 균일하게 분배해야 한다.
    - 이상을 완벽히 실현하기는 어렵지만 비슷하게 만들기는 그다지 어렵지 않다.
    
## 좋은 hashCode를 작성하는 방법
다음은 좋은 hashCode를 작성하는 간단 요령이다.
1. int 변수 result를 선언한 후 값 c로 초기화한다. 이때 c는 해당 객체의 첫 번째 핵심 필드를 단계 2.a 방식으로 계산한 해시코드다.(여기서 핵심 필드란 equals 비교에 사용되는 필드를 말한다. 아이템 10)
2. 해당 객체의 나머지 핵심 필드 f 각각에 대해 다음 작업을 수행한다.
   1. 해당 필드의 해시코드 c를 계산한다.
       1. 기본 타입 필드라면, Type.hashCode(f)를 수행한다. 여기서 Type은 해당 기본 타입의 박싱 클래스다.
       2. 참조 타입 필드면서 이 클래스의 equals 메서드가 이 필드의 equals를 재귀적으로 호출해 비교한다면, 이 필드의 hashCode를 재귀적으로 호출한다. 계산이 더 복잡해질 것 같으면, 이 필드의 표준형(canonical represntation)을 만들어 그 표준형의 hashCode를 호출한다. 필드의 값이 null이면 0을 사용한다(다른 상수도 괜찮지만 전통적으로 0을 사용한다).
       3. 필드가 배열이라면, 핵심 원소 각각을 별도 필드처럼 다룬다. 이상의 규칙을 재귀적으로 적용해 각 핵심 원소의 해시코드를 계산한 다음, 단계 2.b 방식으로 갱신한다. 배열에 핵심 원소가 하나도 없다면 단순히 상수(0을 추천한다)를 사용한다. 모든 원소가 핵심 원소라면 Arrays.hashCode를 사용한다.
   2. 단계 2.a에서 계산한 해시코드 c로 result를 갱신한다. 코드로는 다음과 같다. 
       1. result = 31 * result + c;
3. result를 반환한다.
~~~
@Override public int hashCode() {
   int result = Short.hashCode(areaCode);
   result = 31 * result + Short.hashCode(prefix);
   result = 31 * result + Short.hashCode(lineNum);
   return result;
}
~~~ 


## Objects 클래스의 hashCode
Objects 클래스는 임의의 개수만큼 객체를 받아 해시코드를 계산해주는 정적 메서드인 hash를 제공한다. 이 메서드를 활용하면 앞서의 요령대로 구현한 코드와 비슷한 수준의 hashCode 함수를 단 한 줄로 작성할 수 있다. 
- 하지만 아쉽게도 속도는 더 느리다. 입력 인수를 담기 위한 배열이 만들어지고, 입력 중 기본 타입이 있다면 박싱과 언박싱도 거쳐야 하기 때문이다.
~~~
@Override public int hashCode() {
   return Objects.hash(areaCode, prefix, lineNum);
}
~~~

### 해시코드를 지연 초기화하는 hashCode 메서드 - 스레드 안전성까지 고려해야 한다.
~~~
private int hashCode;
@Override public int hashCode() {
   int result = hashCode;
   if(result == 0){
      result = Short.hashCode(areaCode);
      result = 31 * result + Short.hashCode(prefix);
      result = 31 * result + Short.hashCode(lineNum);
      hashCode = result;
   }
   return result;
}
~~~
성능을 높인답시고 해시코드를 계산할 때 핵심 필드를 생략해서는 안 된다.
- 속도야 빨라지겠지만, 해시 품질이 나빠져 해시테이블의 성능을 심각하게 떨어뜨릴 수도 있다.
- 특히 어떤 필드는 특정 영역에 몰린 인스턴스들의 해시코드를 넓은 범위로 고르게 퍼트려주는 효과가 있을지도 모른다.
- 하필 이런 필드를 생략한다면 해당 영역의 수많은 인스턴스가 단 몇 개의 해시코드로 집중되어 해시테이블의 속도가 선형으로 느려질 것이다.

## 핵심 정리
- equals를 재정의할 때는 hashCode도 반드시 재정의해야 한다. 그렇지 않으면 프로그램이 제대로 동작하지 않을 것이다. 재정의한 hashCode는 Object의 API 문서에 기술된 일반 규약을 따라야 하며, 서로 다른 인스턴스라면 되도록 해시코드도 서로 다르게 구현해야 한다. 
- 이렇게 구현하기가 어렵지는 않지만 조금 따분한 일이긴 하다. 하지만 AutoValue 프레임워크를 사용하면 멋진 equals와 hashCode를 자동으로 만들어 주기도 하고 IDE들도 이런 기능을 일부 제공해주기 때문에 걱정안해도 된다.


--- 


💡 본문은 [개발 블로그](https://loosie.tistory.com/610) 에 있습니다.

``