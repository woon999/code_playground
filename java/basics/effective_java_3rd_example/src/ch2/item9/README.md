# 9. try-finally보다는 try-with-resources를 사용하라
- 자바 라이브러리에서는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 많다. InputStream, OutputStream, java.sql.Connection 등이 좋은 예다. 자원 닫기는 클라이언트가 놓치기 쉬워서 예측할 수 없는 성능 문제로 이어지기도 한다.   
- 이런 자원 중 상당수가 안전망으로 finalizer를 활용하고 있지만 finalizer는 그리 믿을만하지 못하다. (아이템 8)

<br>  

## try-finally를 사용하지 마라.
- 전통적으로 자원이 제대로 닫힘을 보장하는 수단으로 try-finally가 쓰였다. 예외가 발생하거나 메서드에서 반환되는 경우를 포함해서 말이다.

~~~
static String firstLineOfFile(String path) throws IOException{
	BufferedReader br = new BufferedReader(new FileReader(path));
	try{
		return br.readLine();
	} catch (Exception e){
		return e.getMessage();
	} finally{
		br.close();
	}
}
~~~

try-finally 문을 제대로 사용한 앞의 두 코드 예제에서는 미묘한 결점이 있다.
- 예외는 try 블록과 finally 블록 모두에서 발생할 수 있는데, 예컨대 기기에 물리적인 문제가 생긴다면 firstLineOfFile 메서드 안의 readLine 메서드가 예외를 던지고, 같은 이유로 close 메서드도 실패할 것이다.
- 이런 상황이라면 두 번째 예외가 첫 번째 예외를 완전히 집어삼켜 버린다.
- 그러면 스택 추적 내역에 첫 번째 예외에 관한 정보는 남지 않게 되어, 실제 시스템에서의 디버깅을 몹시 어렵게 한다. (일반적으로 처음에 발생한 예외부터 추적한다.)


<br> 
 
## try-with-resources를 사용하라. 
- 이러한 문제들을 자바 7이 투척한 try-with-resources 덕에 모두 해결되었다. 이 구조를 사용하려면 해당 자원이 AutoCloseable 인터페이스를 구현해야 한다. 단순히 void를 반환하는 close 메서드 하나만 덩그러니 정의한 인터페이스다.
- 자바 라이브러리와 서드파티 라이브러리들의 수많은 클래스와 인터페이스가 이미 AutoCloseable을 구현하거나 확장해뒀다.

~~~
static String firstLineOfFile(String path) throws IOException {
   try(BufferedReader br = new BufferedReader(new FileReader(path))){
      return br.readLine();
   }
}
~~~

- try-with-resources 버전이 짧고 읽기 수월할 뿐 아니라 문제를 진단하기도 훨씬 좋다.
- firstLineOfFile 메서드를 생각해보자. readLine과 close 호출 양쪽에서 예외가 발생하면, close에서 발생한 예외는 숨겨지고 readLine에서 발생한 예외가 기록된다.

<br>
  
보통의 try-finally에서처럼 try-with-resources에서도 catch 절을 쓸 수 있다. catch절 덕분에 try 문을 더 중첩하지 않고도 다수의 예외를 처리할 수 있다. 
- 다음 코드에서는 firstLineOfFile 메서드를 살짝 수정하여 파일을 열거나 데이터를 읽지 못했을 때 예외를 던지는 대신 기본값을 반환하도록 해봤다.
~~~
static String firstLineOfFile(String path) {
   try(BufferedReader br = new BufferedReader(new FileReader(path))){
      return br.readLine();
   } catch (IOException e){
      returndefaultVal;
   }
}
~~~ 

--- 


💡 본문은 [개발 블로그](https://loosie.tistory.com/601) 에 있습니다.

