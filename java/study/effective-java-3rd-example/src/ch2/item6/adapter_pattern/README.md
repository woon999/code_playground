# 어댑터 패턴

- 어댑터 패턴(Adapter pattern)은 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 
패턴으로, 호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해준다. ([wiki 참고](https://ko.wikipedia.org/wiki/%EC%96%B4%EB%8C%91%ED%84%B0_%ED%8C%A8%ED%84%B4))

<br>

### InputStreamReader
- JDK에서 대표적으로 `InputStreamReader`가 어댑터 패턴의 예시이다.
~~~
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
~~~

<br>

BufferedReader 클래스의 생성자는 Reader 타입을 받고 System.in은 InputStream타입을 받는다.
- BufferedReader
~~~
public BufferedReader(Reader in) {
    this(in, defaultCharBufferSize);
}
~~~

- System
~~~
public final static InputStream in = null;
~~~    

InputStreamReader를 어댑터로 사용하여 System을 읽어서 BufferedReader에게 Reader 값으로 전해준다.
- InputStreamReader : Adapter
- System : Adaptee
- BufferedReader : Target

<br>

## 장점
- 어댑터를 사용하면 구현되어 있는 코드 변경없이 관계가 없던 인터페이스간 호환이 가능해진다.
- 코드 재활용성 및 검사가 용이해진다.

<br>

## 어댑터 예제
- MP3, MP4, MKV 확장자를 재생하는 소스 코드 ([참고](https://www.tutorialspoint.com/design_pattern/adapter_pattern.htm))



#### 시나리오
- MediaPlayer 인터페이스와 이를 구현한 구현체 AudioPlayer가 있다. AudioPlayer는 기본값으로 MP3파일이 실행 가능하다.
- AdvancedMediaPlayer 인터페이스와 이 구현체는 MP4와 MKV 파일 실행이 가능하다고 한다.
- 우리는 AudioPlayer에서 MP4, MKV 또한 실행이 가능하도록 해주고 싶다. 
- 이를 적용하기 위해서는 Adapter 클래스를 생성해주면 된다.
    1. MediaPlayer를 구현한 MediaAdapter 클래스를 생성한다.
    2. 그리고 추가 확장자 파일 사용을 위해 AdvancedMediaPlayer 객체를 주입하여 사용한다.   
- AudioPlayer는 추가로 원하는 형식(MKV, MP4)을 재생할 수 있는 실제 클래스(AdvancedMediaPlayer)를 모른채 MediaAdapter를 통해서만 실행할 수 있게 된다.

<img width="608" alt="스크린샷 2021-12-01 오전 12 08 56" src="https://user-images.githubusercontent.com/54282927/144072974-dbcd66be-312e-41e2-9f35-6c6a64f870f3.png">




