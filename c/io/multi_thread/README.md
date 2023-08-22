# 멀티스레딩 기반 다중 접속 통신
멀티스레딩 기반의 다중 접속 서버는 다수의 스레드를 생성하는 방식으로 서비스를 제공한다
![Alt text](image.png)

## 동작 과정 
1. 메인 스레드는 리스닝 소켓으로 accept 함수를 호출해서 연결 요청을 수락한다.
2. 이때 얻는 소켓의 파일 디스크립터(클라이언트와 연결된 연결 소켓)를 별도 워커 스레드를 생성해 넘겨준다.
3. 워커 스레드는 전달받은 파일 디스크립터를 바탕으로 서비스를 제공한다.

## 장점
- 프로세스 복사 비용보다 스레드 생성 비용이 적음
- 스레드간 서로 공유하는 메모리 영역이 있기 때문에 정보 교환이 쉬움 

## 단점	
- 하나의 스레드에서 발생한 문제가 프로세스 전체에 영향을 미쳐 나머지 다수의 스레드에 영향을 끼침
- 멀티프로세싱 방식보다는 비용이 적게 들지만 스레드 관리에 여전히 많은 리소스가 필요
- 스레드 풀로 관리하여 운영할 수 있지만 많은 수의 요청을 동시에 처리할 수 없다(C10k problem)


<br>

# server 
```
> ./echo_multi_thread_server 3000
# 연결시
new client connected...
new client connected...

# 연결 종료시 
client disconnected...
client disconnected...
```

<br>

# client1
```
> ./echo_client 127.0.01 3000
Connected...........
Input message(Q to quit): 123123
Message from server: 123123
Input message(Q to quit):
```

<br>

# client2
```
> ./echo_client 127.0.0.1 3000
Connected...........
Input message(Q to quit): 456456
Message from server: 456456
Input message(Q to quit):

```

---
https://engineering.linecorp.com/ko/blog/do-not-block-the-event-loop-part1#mcetoc_1gdcaies0s