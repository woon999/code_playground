# 1. 생성자 대신 정적 팩토리 메서드를 고려하라.

## 사전공부
### 불변(immutable) 클래스
- 변경이 불가능한 클래스이며, 가변적이지 않는 클래스
- String, Integer, Boolean, Float, Long 등등 heap영역에서 변경불가능한 것이지 재할당을 못하는 것은 아니다.
- String a = "aa"로 생성한 a 객체는 a= "bb"로 재할당이 가능하다.  "aa"에서 "bb"로 값이 변경되는 것이 아니라 새로운 객체를 생성해서 a가 그 객체를 참조하게 하는 것이다.
- 장점
    1. 생성자, 접근 메소드에 대한 방어 복사가 필요 없다.
    2. 멀티쓰레드 환경에서 동기화 처리없이 객체를 공유할 수 있다.
    3. 불변이기 때문에 Thread-safe 하다.
- 단점
    1. 객체가 가지는 값마다 새로운 객체가 필요하다.
    2. 따라서 메모리 누수와 새로운 객체를 계속 생성해야하기 때문에 사용빈도가 많아지게 되면 성능저하를 야기한다.

<br> 

### 플라이웨이트(Flyweight) 패턴
- 플라이웨이트는 이름 그대로 객체를 가볍게 사용하기 위한 패턴으로, 어떤 클래스의 인스턴스를 한 개만 가지고 여러 개의 "가상 인스턴스"를 제공하고 싶을 때 사용한다.
- 즉, 인스턴스를 가능한 대로 공유시켜 쓸데없이 `new` 연산자를 통한 메모리 낭비를 줄이는 방식이다.

#### 역할
1. Flyweight 역할 : 공유에 사용할 클래스들의 인터페이스(API)를 선언한다.
2. ConcreteFlyWeight 역할 : Flyweight의 내용물을 정의한다. 실제 공유될 객체이다.
3. FlyweightFactory 역할 : 해당 공장을 사용해서 Flyweight의 인스턴스를 생성 또는 공유해준다.
4. Client 역할 : 해당 패턴을 사용하는 고객이다.

<img width="519" alt="스크린샷 2021-11-20 오후 11 48 34" src="https://user-images.githubusercontent.com/54282927/142730675-65cc7458-a081-4075-8f99-b3fa671f119c.png">

- 장점
    - 실행시 객체 인스턴스 갯수를 줄여서 메모리를 절약할 수 있다.
    - 여러 "가상" 객체 상태를 한 곳에 집중시켜놓을 수 있다.
- 단점
    - 특정 인스턴스만 다른 인스턴스처럼 동작하도록 하는 것이 불가능
    - 객체의 값을 변경하면 공유받은 "가상" 객체를 사용하는 곳에 영향을 줄 수 있다.

<br>


### Type 인터페이스 - Types 동반 클래스 (Pros 3)
자바 8전에는 인터페이스를 정적 메서드를 선언할 수 없었다. 그렇기 때문에 이름이 "Type"인 인터페이스를 반환하는 정적 메서드가 필요하면, "Types"라는 (인스턴스화 불가인) 동반 클래스(companion class)를 만들어 그 안에 정의하는 것이 관례였다.
- 예컨데, JCF는 핵심 인터페이스들에 수정 불가나 동기화 등의 기능을 덧붙인 총 45개의 유틸리티 구현체를 제공하는데, 이 구현체 대부분을 단 하나의 인스턴스화 불가 클래스인 java.util.Collections에서 정적 팩토리 메서드를 통해 얻도록 했다.
    - JCF는 이 45개 클래스를 공개하지 않기 때문에 API 외견을 훨씬 작게 만들 수 있었다.
    - API가 작아진 것은 물론 개념적인 무게, 즉 프로그래머가 API를 사용하기 위해 익혀야 하는 개념의 수와 난이도도 낮췄다.
    - 프로그래머는 명시한 인터페이스대로 동작하는 객체를 얻을 것임을 알기에 굳이 별도 문서를 찾아가며 실제 구현 클래스가 무엇인지 알아보지 않아도 된다.
    - 나아가 정적 팩토리 메서드를 사용하는 클라이언트는 얻은 객체를 인터페이스만으로 다루게 된다.

자바 8부터는 인터페이스가 정적 메서드를 가질 수 없다는 제한이 풀렸기 때문에 인스턴스화 불가 동반 클래스를 둘 이유가 별로 없다. 동반 클래스에 두었던 public 정적 멤버들 상당수를 그냥 인터페이스 자체에 두면 되는 것이다.
- 하지만 정적 메서드들을 구현하기 위한 코드 중 많은 부분은 여전히 별도의 package-private 클래스에 두어야 할 수 있다.
- 자바 8에서도 인터페이스에는 허락하지만 정적 필드와 정적 멤버 클래스는 여전히 public이어야 한다.

<br>

### EnumSet (Pros 4)
가령 EnumSet 클래스는 public 생성자 없이 오직 정적 팩토리만 제공하는데, OpenJDK에서는 원소의 수에 따라 두 가지 하위 클래스 중 하나의 인스턴스를 반환한다.
- 원소가 64개 이하면 원소들을 long 변수 하나로 관리하는 RegularEnumSet 인스턴스 반환
- 65개 이상이면 long 배열로 관리하는 JumboEnumSet의 인스턴스 반환

<br>

### 서비스 제공자 프레임워크, JDBC (Pros 5)
해당 내용은 본문에 있는 글이지만 먼저 학습하는 것이 좋을 것 같아 사전 공부에 넣었다.

서비스 제공자 프레임워크는 3개의 핵심 컴포넌트로 이뤄진다. 
1. 구현체의 동작을 정의하는 서비스 인터페이스(service interface)
2. 제공자 구현체를 등록할 때 사용하는 제공자 등록 API(provider registration API)
3. 클라이언트가 서비스의 인스턴스를 얻을 때 사용하는 서비스 접근 API(service access API)
    - 클라이언트는 서비스 접근 API를 사용할 때 원하는 구현체의 조건을 명시할 수 있다. 조건을 명시하지 않으면 기본 구현체를 반환하거나 지원하는 구현체들을 하나씩 돌아가며 반환한다. 
    - 이 서비스 접근 API가 바로 서비스 제공자 프레임워크의 근간이라고 한 '유연한 정적 팩토리'의 실체다.  


3가지 핵심 컴포넌트와 더불어 종종 서비스 제공자 인터페이스(service provider interface)라는 네 번째 컴포넌트가 쓰이기도 한다. 이 컴포넌트는 서비스 인터페이스의 인스턴스를 생성하는 팩토리 객체를 설명해준다.

- 서비스 제공자 인터페이스가 없다면 각 구현체를 인스턴스로 만들 때 리플렉션을 사용해야 한다.
- JDBC를 예로 들면 각 역할은 다음과 같다.
    - Connection이 서비스 인터페이스 역할
    - DriverManager.registerDriver가 제공자 등록 API 역할
    - DriverManager.getConnection이 서비스 접근 API 역할
    - Driver가 서비스 제공자 인터페이스 역할

#### JDBC 예제
Class.forName으로 드라이버 이름을 호출하여 DriverManager 객체가 로드된다.
1. Class.forName(driverName); 메소드에 의해 전달되는 "com.mysql.jdbc.Driver"가 메모리에 로드된다. 
2. Class.forName("com.mysql.jdbc.Driver");가 실행되면 JDBC Driver가 동적 로딩 된다.
3. 2번에서 Driver가 로딩되면서 [DriverManager.registerDriver()](http://www.docjar.com/html/api/com/mysql/jdbc/Driver.java.html)의 메소드가 실행되어 DriverManger도 자동으로 로딩된다.
4. 등록한 JDBC Driver는 DB Connction을 생성하는 시점에 사용되어진다.
~~~
public static void main(String[] args) {
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "1234";
    
	try {
		Class.forName(driverName);

        // [서비스 인터페이스 : Connection] <---getConnection()--- [서비스 접근 API : DriverManager.getConnection]
		Connection connection = DriverManager.getConnection(url, user, password);

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
~~~


서비스 제공자 프레임워크 패턴에는 여러 변형이 있다. 

- 브리지 패턴 : 서비스 접근 API는 공급자가 제공하는 것보다 더 풍부한 서비스 인터페이스를 클라이언트에 반환할 수 있다.
- 의존 객체 주입, 프레임워크도 강력한 서비스 제공자라 생각할 수 있다.
- 자바 6부터는 ServiceLoader라는 범용 서비스 제공자 프레임워크가 제공되어 프레임워크를 직접 만들 필요가 거의 없어졌다.
- 한편, JDBC는 자바 6 전에 등장한 개념이라 ServiceLoader를 사용하지 않는다.


---

💡 본문(pros_and_cons)은 [개발 블로그](https://loosie.tistory.com/569)에 있습니다.
