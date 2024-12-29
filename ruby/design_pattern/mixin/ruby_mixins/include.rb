=begin

# Include

`include`는 모듈에 정의된 메서드를 클래스에서 재사용하는 가장 쉽고 널리 알려진 방법이다.
클래스를 정의할 때 어떤 모듈을 include하면 그 모듈은 ancestors 배열상에서 부모 클래스(`superclass`) 앞에 위치하게 된다.
따라서 메서드 이름이 같다면 include된 모듈이 부모 클래스보다 우선순위를 가진다.

=end

module MyModule
  def log
    "log by MyModule"
  end
end

class BaseClass
  def log
    "log by BaseClass"
  end
end

class MyClass < BaseClass
  include MyModule
end

p MyClass.ancestors
# [MyClass, MyModule, BaseClass, Object, Kernel, BasicObject]

p MyClass.instance_method(:log)
# #<UnboundMethod: MyModule#log() /mixin/ruby_mixins/include.rb:12>

p MyClass.new.log
# log by MyModule

