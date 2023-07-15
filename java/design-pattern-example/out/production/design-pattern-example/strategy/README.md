# Strategy Pattern
전락 패턴(Strategy Pattern)은 알고리즘군을 정의하고 캡슐화해서 각각의 알고리즘군을 수정해서 쓸 수 있게 해줍니다.
전략 패턴을 사용하면 클라이언트로부터 알고리즘을 분리해서 독립적으로 변경할 수 있습니다. 

- Duck <- (Mallard Duck, Redhead Duck, ...) 각종 Duck이 'Duck'을 상속하여 기능 사용. 
- Duck에 fly()기능 추가. -> 의도하지 않은 상속 클래스까지 fly()가 전염됨
- 인터페이스로 분리? Flyable. -> 그러다 만약 fly()의 구현사항이 변경되면? Flyable을 구현한 모든 코드를 수정해야줘야 됨.

### strategy pattern
    1. 바뀌는 부분과 그렇지 않은 부분 분리하기
    2. 바뀌는 부분은 모듈화 - FlyBehavior, QuackBehavior
    3. Fly 종류별로 구현 - FlyNoWay, FlyWithWings, ...
    4. Quack 종류별로 구현 - Quack, Squeak, ... 

- 행동 통합하기 - Duck 추상 클래스 안에 FlyBehavior, QuackBehavior 변수 선언 
- Duck 성질에 따라 Fly, Quack 기능 동적으로 설정 (MallardDuck, MiniDuckSimulator, ...)