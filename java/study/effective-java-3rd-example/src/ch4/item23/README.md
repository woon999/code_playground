# 23. 태그 달린 클래스보다는 클래스 계층구조를 활용하라
두 가지 이상의 의미를 표현할 수 있으며, 그중 현재 표현하는 의미를 태그 값으로 알려주는 클래스를 본 적이 있을 것이다. 다음 코드는 원과 사각형을 표현할 수 있는 클래스다.

<br>

## 태그 달린 클래스의 단점 - 장황하고, 오류 내기 쉽고, 비효율적이다.
태그 달린 클래스에는 단점이 한가득이다. 우선 열거 타입 선언, 태그 필드, switch 문 등 쓸데없는 코드가 많다. 여러 구현이 한 클래스에 혼합돼 있어서 가독성도 나쁘다.

다른 의미를 위한 코드도 언제나 함께 하니 메모리도 많이 사용한다. 필드들을 final로 선언하려면 해당 의미에 쓰이지 않는 필드들까지 생성자에서 초기화해야 한다. 
- 원을 사용하려고 인스턴스를 생성하면 쓸모없는 사각형과 관련된 필드도 같이 생성된다.

또 다른 의미를 추가하려면 계속해서 코드를 수정해야 한다. switch문에서 타입을 하나라도 빠뜨리면 역시 런타임에 문제가 불거져 나올 것이다. 

마지막으로, 인스턴스 타입만으로 현재 나타내는 의미를 잘 알길이 없다. 이게 원인지 사각형인지 말이다.

객체 지향 언어는 타입 하나로 다양한 의미의 객체를 표현하는 훨씬 나은 수단을 제공한다. 바로 클래스 계층구조를 활용하는 서브타이핑이다. 태그 달린 클래스는 클래스 계층구조를 어설프게 흉내내는 아류일 뿐이다.
~~~
public class Figure {
   enum Shape {RECTANGLE,CIRCLE}

   // 태그 필드 - 현재 모양을 나타낸다.
   private final Shape shape;

   // RECTANGLE 필드
   private double length;
   private double width;

   // CIRCLE 필드
   private double radius;

   // CIRCLE 생성자
   public Figure(double radius) {
      shape = Shape.RECTANGLE;
      this.radius = radius;
   }

   // RECTANGLE 생성자
   public Figure(double length, double width) {
      shape = Shape.CIRCLE;
      this.length = length;
      this.width = width;
   }

   public double area() {
      switch (shape) {
         caseRECTANGLE:
            return length * width;
         caseCIRCLE:
            return Math.PI* (radius * radius);
         default:
            throw new AssertionError(shape);
      }
   }
}
~~~

<br>

## 클래스 계층 구조로 변환하기
가장 먼저 계층구조의 루트가 될 추상 클래스를 정의하고, 태그 값에 따라 동작이 달라지는 메서드들을 루트 클래스의 추상 메서드로 선언한다.
- area()가 이에 해당한다.

그런 다음 태그 값에 상관없이 동작이 일정한 메서드와 하위 클래스에서 공통으로 사용하는 데이터 필드들을 루트 클래스에 올려준다.
- Figure 클래스에서는 태그 값에 상관없는 메서드랑 공통 데이터 필드가 하나도 없어서 area()만 선언해주면 된다.

다음으로, 루트 클래스를 확장한 구체 클래스를 의미별로 하나씩 정의한다. 
- 우리 예에서는 Figure를 확장한 원(Circle) 클래스와 사각형(Rectangle) 클래스를 만들면 된다.
- 각 하위 클래스에는 각자의 의미에 해당하는 데이터 필드들을 넣는다. 원에는 반지름(radius)을, 사각형에는 길이(length)와 너비(width)를 넣으면 된다.

그런 다음 루트 클래스가 정의한 추상 메서드를 각자의 의미에 맞게 구현해주면 된다.

~~~
abstract class Figure {
   abstract double area();
}

class Circle extends Figure {
   private final double radius;

   public Circle(double radius) {
      this.radius = radius;
   }

   @Override
   double area() {
      return Math.PI* (radius * radius);
   }
}

class Rectangle extends Figure {
   private final double length;
   private final double width;

   public Rectangle(double length, double width) {
      this.length = length;
      this.width = width;
   }

   @Override
   double area() {
      return length * width;
   }

   public static void main(String[] args) {

   }
}
~~~

<br>

## 핵심 정리
태그 달린 클래스를 써야 하는 상황은 거의 없다. 새로운 클래스를 작성하는 데 태그 필드가 등장한다면 태그를 없애고 계층구조로 대체하는 방법을 생각해보자. 기존 클래스가 태그 필드를 사용하고 있다면 계층구로 리팩토링하는 걸 고민해보자

<br>

--- 

💡 본문은 [개발 블로그](https://loosie.tistory.com/664) 에 있습니다.
