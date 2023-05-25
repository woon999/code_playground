# Ruby Mixin - include, prepend, extend
루비는 다른 객체지향 언어와 달리 클래스가 여러 부모로부터 상속받을 수 없습니다. 
하지만 `module`을 mixin하면 다중 상속과 비슷한, 또는 더 풍부한 효과를 낼 수 있습니다. 

어떤 언어에서든 mixin이 지나치면 코드를 이해하기 어려워지지만, 잘 사용하면 중복이 줄어들고 깔끔해진다.
루비의 mixin, 그리고 레일즈에서 더 편리하게 mixin을 사용할 수 있게 해주는 `ActiveSupport::Concern`까지 알아보자.



