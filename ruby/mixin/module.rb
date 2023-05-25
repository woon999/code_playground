=begin

루비에서 모듈은 “메서드와 상수의 집합”을 뜻합니다.

모듈은 클래스와 달리 초기화 instantiate될 수 없으며, 모듈의 주 목적은 그 안에 정의한 메서드를 다양한 클래스에 `include`,`prepend`,`extend`를 통해 mixin해서 재사용하는 것입니다.
이렇게 mixin해서 사용하는 메서드를 인스턴스 메서드라고 부르고, 객체 생성 없이 모듈 자체에서 호출하는 메서드를 모듈 메서드라고 부릅니다. 모듈 메서드는 mixin해도 사용할 수 없습니다.

=end

module MyModule
  CONST = "My Const"

  def self.module_method
    "moule_method is called"
  end

  def instance_method
    "instance_method is called"
  end
end

p MyModule::CONST
# "My Const"

p MyModule.module_method
# "moule_method is called"

# p MyModule.new.instance_method
# /ruby/mixin/module.rb:15:in `<main>': undefined method `new' for MyModule:Module (NoMethodError)


class My
  include MyModule
end

p My::CONST
# "My Const"

p My.new.instance_method
# "instance_method is called"

# p My.module_method
# /ruby/mixin/module.rb:38:in `<main>': undefined method `module_method' for My:Class (NoMethodError)
