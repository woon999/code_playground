# JVM GC
https://loosie.tistory.com/848


Garbage Collector는 이 문제를 현재 사용하고 있지 않는 Object로 Garbage를 판단한다. 그리고 현재 사용 여부는 바로 Root Set과의 관계로 판단한다. 다시 말해 Root Set에서 어떤 식으로든 Reference 관계가 있다면 Reachable Object라고 한다. 이것을 현재 사용하고 있는 Object로 간주하게 된다. Root set이 참조하는 정보는 다음과 같다.
- Stack의 참조(Ref) 정보
- Constant Pool에 있는 참조(Ref) 정보
- Native Method로 넘겨진 객체 참조(Ref) 정보


이 세 가지 Ref 정보에 의해 직, 간접적으로 참조되고 있다면 모두 Reachable 객체이고 아니면 Garbage 취급을 한다. 그리고 Reachable 객체에서도 Live 여부에 따라 둘로 나뉘어진다. Live는 실제로 해당 객체를 사용하는 지에 대한 여부이다. 