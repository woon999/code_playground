# Monad 
Monad는 값과 계산을 캡슐화하는 컨테이너 또는 구조체이다. 

## Monad 기본 함수
- 단위(unit): T -> M<T> 
  - Monad는 주어진 값을 감싸는 유형을 나타내며, 이 연산은 값의 래핑을 담당한다.
  - 예를 들어, Java에서 이 연산은 제네릭을 활용하여 다양한 유형의 값을 받아들일 수 있다. 
- Bind(join, flat): M<M<T>> -> M<T>
  - 이 연산은 보유된 값을 사용하여 변환을 실행하고 새로운 모나드 값(모나드 타입의 값 래핑)을 반환한다.

### Java Optional
flatMap: Optional<T> -> (T -> Optional<U>) -> Optional<U>
- M<M<T>> -> M<T>을 Java 언어에 맞게 M<T> -> (T -> M<U>) -> M<U>로 적용한 것이다.
```java
<U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
```

# Monad 기본 속성
## 1. naturality
Monad 동작이 여러 변환에서 일관되고 예측 가능하게 유지되도록 하는 속성을 의미한다.
- unit, bind 모두 해당한다.
![](https://i.imgur.com/9imhuos.png)

## 2. identity
- unit(값을 모나드에 넣는 연산)과 bind 연산(= java flatMap)이 동일성 함수와 일치하는 방식으로 동작해야 한다
- 이는 unit, bind는 모나드 구조에서 연산자 역할을 하지 않아야 함을 의미한다.
- 방향을 어떻게 연산했든 결과는 동일해야 한다.
![](https://i.imgur.com/hUNFTLS.png)

## 3. associativity
- 모나드 컨텍스트 내에서 함수를 구성하는 방식이 최종 결과에 영향을 미치지 않아야 한다.
- 변환을 bind할 때, 변환이 중첩되는 방식은 중요하지 않다.
- M<M<M<T>>>를 M<T>로 어떻게 변환하든 결과는 동일해야 한다.
- `M<T>.flatMap(f).flatMap(g) == M<T>.flatMap(x -> f(x).flatMap(g))`
![](https://i.imgur.com/w7CEN7X.png)
