=begin

# Extend

`extend`는 다른 두 mixin(include, prepend)과 동작방식이 다르다.

include와 prepend가 클래스의 ancestors 배열에 관여하여 **인스턴스 메서드를 확장**하는 개념이었다면,
extend는 클래스의 **클래스 메서드를 확장**한다.

=end

module MyModule
  def log
    "log by MyModule"
  end
end

class MyClass
  extend MyModule
end

p MyClass.log
# => log by MyModule

p MyClass.ancestors
# => [MyClass, Object, Kernel, BasicObject]

=begin

그런데 위 스니펫에서 보듯이 extend해도 `MyClass`의 ancestors에는 변화가 없다.
그러면 extend는 어떻게 클래스가 모듈의 메서드에 접근할 수 있게 해주는 것일까? 애초에 클래스 메서드는 어떻게 실행되는 걸까?

사실 루비에서 진정한 의미의 클래스 메서드는 존재하지 않는다.
루비에서는 모든 것이 오브젝트이고, **클래스**도 다른 무언가의 인스턴스이며, 클래스 메서드도 결국은 인스턴스 메서드이기 때문이다.

이에 대해 확실하게 이해하려면 싱글톤 클래스와 오브젝트 모델에 대해 알아야 한다.
- 클래스 메서드는 싱글톤 클래스 안에 정의되고, 모듈을 extend하면 싱글톤 클래스가 확장된다.

=end


p MyClass.singleton_class
# #<Class:MyClass>

p MyClass.singleton_class.ancestors
# [#<Class:MyClass>, MyModule, #<Class:Object>, #<Class:BasicObject>, Class, Module, Object, Kernel, BasicObject]