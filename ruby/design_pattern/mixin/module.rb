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

=begin

루비에서는 클래스가 생성될 때 **ancestors** 배열에 클래스 조상의 목록을 저장해둡니다.
ancestors에는 이 클래스가 상속받는 모든 클래스, 자기 자신, 그리고 `include`와 `prepend`를 통해 mixin된 모듈들이 포함됩니다.

클래스의 인스턴스 메서드를 호출하면 ancestors 배열의 앞에서부터 메서드 정의를 찾습니다.
상속 개념과 유사하게, 메서드 정의를 찾지 못하면 다음 조상에게서 찾는 식입니다.
즉 둘 이상의 선조들이 같은 이름의 메서드를 정의하고 있다면 더 가까운 선조에 정의된 메서드를 실행합니다.
참고로 `BasicObject`까지 거슬러 올라갔는데도 메서드를 찾지 못하면
  [BasicObject#method_missing](https://ruby-doc.org/core-2.5.0/BasicObject.html#method-i-method_missing)이 실행되는데, 몇몇 루비 gem들은 이를 이용해 이해하기 쉽지 않은 흑마술을 부리기도 합니다.

=end

p String.ancestors
# [String, Comparable, Object, Kernel, BasicObject]

p My.ancestors
# [My, MyModule, Object, Kernel, BasicObject]

p "foo".upcase
# "FOO"

p "foo".object_id
# 60

p String.instance_method(:upcase)
# #<UnboundMethod: String#upcase(*)>

p String.instance_method(:object_id)
# #<UnboundMethod: Kernel#object_id()>
