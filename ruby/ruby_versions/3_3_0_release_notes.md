# Ruby 3.3.0-preview1 Released
2023년 5월 12일

## 업데이트 사항
- Ruby 3.3에는 RJIT라는 새로운 순수 Ruby JIT 컴파일러가 추가됨
- Lrama를 구문 분석기 생성기로 사용
- 특히 YJIT의 성능이 많이 개선됨

## RJIT
pure-Ruby JIT 컴파일러 RJIT를 도입하여 MJIT를 대체했다.
- RJIT는 Unix 플랫폼에서 x86_64 아키텍처만 지원한다.
- MJIT와 달리 런타임에 C 컴파일러가 필요하지 않는다.

RJIT는 실험적인 목적으로만 존재한다.
- 프로덕션 환경에서는 YJIT를 계속 사용해야 한다.

Ruby용 JIT 개발에 관심이 있다면 [RubyKaigi 3일차 k0kubun의 프레젠테이션을 확인](https://rubykaigi.org/2023/presentations/k0kubun.html#day3)
- [ruby-jit-challenge](https://github.com/k0kubun/ruby-jit-challenge)
- [slide](https://speakerdeck.com/k0kubun/rubykaigi-2023)


### Ruby RJIT PR [#7448](https://github.com/ruby/ruby/pull/7448)
현재의 MJIT을 대체하는 PR

Pure-Ruby 어셈블러를 사용하여 네이티브 코드를 생성
- MJIT는 런타임에 C컴파일러를, YJIT는 빌드시에 Rust 컴파일러가 필요

생성된 코드는 YJIT가 만든 것과 비슷
- 실제로 많은 메소드들이 Rust코드를 Ruby로 그대로 번역
- MJIT용 구현들을 제거해서 Ruby VM을 간소화

벤치마크에서 YJIT보다는 엄청 느리지만, MJIT보다는 빠름


## Use Lrama instead of Bison
Bison을 [Lrama LALR parser generator로 대체](https://github.com/yui-knk/lrama) [Feature #19637](https://bugs.ruby-lang.org/issues/19637)
- 관심이 있다면 Ruby Parser의 미래 비전을 기대!

## YJIT
3.2에 비해 성능 크게 개선
- Splat 및 나머지 인수 지원 개선
- 가상 머신의 스택 연산을 위해 레지스터가 할당됨
- optional arguments를 사용하는 호출이 더 많이 컴파일된다.
- Integer#!=, String#!=, Kernel#block_given?, Kernel#is_a?, Kernel#instance_of?, Module#=== 가 특별히 최적화 됨

컴파일된 코드의 메타데이터가 훨씬 적은 메모리를 사용한다.

ARM64에서 코드 생성 개선

일시 중지 모드에서 YJIT를 시작한 다음 나중에 수동으로 활성화하는 옵션
- --yjit-pause 및 RubyVM::YJIT.resume
- 애플리케이션 부팅이 완료된 후에만 YJIT를 활성화하는 데 사용할 수 있다.

이제 Exit tracing 옵션이 샘플링을 지원한다.
- --trace-exits-sample-rate=N


