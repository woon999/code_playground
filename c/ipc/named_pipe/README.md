# Named Pipe 
- 파이프는 두 프로세스를 연결
- 한 쪽은 전송만하고 다른 쪽은 읽기만 하는 반이중통신(한방향)

장점
- 단순함
- 익명 파이프(Pipe)와는 달리 통신을 할 프로세스를 모르더라도 오픈 파일을 통해서 통신 가능

단점 
- 익명 파이프와 동일. 양방향 통신을 위해서는 2개의 파이프가 필요하다
```c
// cmd1
> ./server
FIFOSERVER: Received string: "123" and length is 3
FIFOSERVER: Sending Reversed String: "321" and length is 3
FIFOSERVER: Received string: "abc" and length is 3
FIFOSERVER: Sending Reversed String: "cba" and length is 3
FIFOSERVER: Received string: "hello" and length is 5
FIFOSERVER: Sending Reversed String: "olleh" and length is 5

// cmd2
> ./client
Enter string: 123
FIFOCLIENT: Sent string: "123" and string length is 3
FIFOCLIENT: Received string: "321" and length is 3
Enter string: abc
FIFOCLIENT: Sent string: "abc" and string length is 3
FIFOCLIENT: Received string: "cba" and length is 3
Enter string: hello
FIFOCLIENT: Sent string: "hello" and string length is 5
FIFOCLIENT: Received string: "hello" and length is 5
```