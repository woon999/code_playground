=begin

# Prepend

`prepend`는 루비 2.0부터 도입된 mixin으로, include와 동작은 유사하나 용도는 다르다.

include가 모듈의 메서드를 그대로 사용하기 위함이라면,
prepend는 클래스의 기존 메서드를 꾸며주는 역할을 한다.

이게 가능한 이유는, prepend된 모듈이 ancestors 배열상에서 원 클래스의 앞에 위치하기 때문이다.
메서드 호출은 ancesotrs의 앞에서부터 정의를 찾아나가기 때문에, prepend된 모듈의 메서드는 원 클래스의 메서드보다 우선순위가 높다.
그리고 여기에 다음 ancestor에서 메서드를 찾는 `super`키워드를 조합하면, 해당 메서드의 앞이나 뒤에 우리가 원하는 동작을 추가할 수 있다.

=end

module MyModule
  def sum_of(numbers)
    result = super # MyClass#sum_of 호출
    "sum_of(#{numbers.inspect}) finished: #{result.inspect}"
  end
end

class MyClass
  prepend MyModule

  def sum_of(numbers)
    numbers.sum
  end
end

p MyClass.ancestors
# => [MyModule, MyClass, Object, Kernel, BasicObject]

p MyClass.new.sum_of([1, 2, 3])
# => sum_of([1, 2, 3]) finished: 6