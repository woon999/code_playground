# 3. private 생성자나 열거 타입으로 싱글턴임을 보장하라

## public static final 필드 방식
- private 생성자는 public static final 필드인 Elvis.INSTANCE를 초기화할 때 딱 한 번만 호출된다. 
- public이나 protect 생성자가 없으므로 Elvis 클래스가 초기화될 때 만들어진 인스턴스가 전체 시스템에서 하나뿐임이 보장된다. 클라이언트는 손 쓸 방법이 없다.
- 예외는 단 한 가지, 권한이 있는 클라이언트는 리플렉션 API인 AccssibleObject.setAccessible을 사용해 private생성자를 호출할 수 있다.

### 싱글턴 리플렉션 공격
- 리플렉션을 사용하면 public static final 필드 방식 싱글톤에 있는 priavte 생성자에 접근할 수 있다. 
   - getDeclaredConstructors() : 해당 클래스에 선언된 모든 생성자를 배열의 형태로 반환한다. 여기에는 private으로 선언된 생성자도 포함된다.
   - setAccessible(true) : getDeclaredConstructor()는 private에 접근하기 위해서는 setAccessible(true)라는 메서드를 추가로 설정해줘야 한다.
   - newInstance(initargs) : 접근이 가능해지면 해당 메서드를 이용하여 객체를 생성해주면 된다.


~~~
  Constructor<Elvis> constructor = Elvis.class.getDeclaredConstructor(Access.class);
  constructor.setAccessible(true);
  Elvis obj2 = constructor.newInstance(Access.REFLECTION);
  constructor.setAccessible(false);
  obj1.leaveTheBuilding();
  obj2.leaveTheBuilding();
~~~


### 두 번째 객체 생성 예외던지기
- 해당 예외처리 방식이 최적인 것은 정확하지 않으나 Elvis 싱글턴 클래스에 static 변수들이 로드될 때 count도 증가하게 하여 두 번째 객체 생성이 접근할 때 예외를 던지도록 처리해봤다.

~~~
public class Elvis{
    public static final Elvis INSTANCE = new Elvis(Access.NORMAL);
    public static int count=0;
    static{ count++; }
    private Elvis(){
        if (count >0) {
            throw new RuntimeException("객체 생성은 1번만 가능합니다.");
        }
        System.out.print((count) + "private Elvis Constructor -");
    }
   private Elvis(Access who){
        this();
        System.out.println(who);
    }
    // ...
}
~~~

  <br>

## 정적 팩토리 방식의 싱글턴
Elvis.getInstacne는 항상 같은 객체의 참조를 반환하므로 제2의 Elvis 인스턴스란 결코 만들어지지 않는다. (역시 리플렉션을 통한 예외는 똑같이 적용된다)

public static final 필드 방식의 public 필드 방식의 큰 장점은
- 해당 클래스가 싱글턴임이 API에 명백히 드러난다는 것이다. public static 필드가 final이니 절대로 다른 객체를 참조할 수 없다.
- 두 번째 장점은 바로 간결함이다.

한편, 정적 팩토리 방식의 첫 번째 장점은
- (마음이 바뀌면)API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다는 점이다. 유일한 인스턴스를 반환하던 팩토리 메서드가 (예컨대) 호출하는 스레드별로 다른 인스턴스를 넘겨주게 할 수 있다.
- 두 번째 장점은 원한다면 정적 팩토리 제네릭 싱글턴 팩토리를 만들 수 있다는 점이다. (아이템 30)
- 세 번째 장점은 정적 팩토리 메서드 참조를 공급자(supplier)로 사용할 수 있다는 점이다. 가령 Elvis::getInstance를 Supplier<Elvis>로 사용하는 식이다. (아이템 43, 44)

이러한 장점이 필요하지 않다면 public 필드 방식이 좋다.

  
### 싱글턴 클래스 직렬화
둘 중 하나의 방식으로 직렬화하려면 단순히 Serializable을 구현한다고 선언하는 것만으로는 부족하다. 모든 인스턴스 필드를 일시적(transient)이라고 선언하고 readResolve 메서드를 제공해야 한다.(아이템 89)
- 이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화할 때 마다 새로운 인스턴스가 만들어진다.
- 정적 팩토리 방식으로 예를들면 가짜 Elivs가 탄생한다는 뜻이다. 이를 예방하고 싶다면 Elvis 클래스에 다음의 readResolve 메서드를 추가하자.
  
<br>

## 열거 타입 방식의 싱글턴
- public 필드 방식과 비슷하지만, 더 간결하고, 추가 노력 없이 직렬화할 수 있고,
- 심지어 아주 복잡한 직렬한 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.
- 조금 부자연스러워 보일 수 있으나 대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.
- 단, 만들려는 싱글턴이 Enum 외의 클래스를 상속해야 한다면 이 방법은 사용될 수 없다. (열거 타입이 다른 인터페이스를 구현하도록 선언할 수는 있다.)
  
---

💡 본문은 [개발 블로그](https://loosie.tistory.com/579) 에 있습니다.
 
  
