# Unamed Pipe 
- 파이프는 두 프로세스를 연결
- 한 쪽은 전송만하고 다른 쪽은 읽기만 하는 반이중통신(한방향)

장점
- 단순함
- 익명 파이프(Pipe)와는 달리 통신을 할 프로세스를 모르더라도 오픈 파일을 통해서 통신 가능

단점 
- 익명 파이프와 동일. 양방향 통신을 위해서는 2개의 파이프가 필요하다
```c
> ./build.sh
+ clang -Wall -Wextra -o writer ./named/writer.c
+ clang -Wall -Wextra -o reader ./named/reader.c

// cmd1
> ./writer

// cmd2
> ./reader
Received: Hello, world!
```