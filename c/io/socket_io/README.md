# Socket을 활용한 통신
- ip, port 정보를 통해 server와 client 통신 
![Alt text](image.png)

단점 
- 다수 연결 요청 처리 X: 큐 크기를 초과한 다수의 연결 요청이 오는 경우 listen 큐에서 대기해야 함 

<br>

# server 
- socket 생성 후 연결 요청 대기(listen)함 
- client에서 연결 요청이 오는 경우 accept하여 연결 
- read(), write()로 데이터 송수신 
```
> ./echo_server 3000
Connected client 1 
```

<br>

# client 
- connet 함수를 통해 server에게 연결 요청을 보냄 
- read(), write()로 데이터 송수신 
```
> ./echo_client 127.0.0.1 3000
Connected...........
Input message(Q to quit): 1
Message from server: 1
Input message(Q to quit): 2
Message from server: 2
Input message(Q to quit): 3
Message from server: 3
Input message(Q to quit): 4
Message from server: 4
```

---
https://engineering.linecorp.com/ko/blog/do-not-block-the-event-loop-part1#mcetoc_1gdcaies0s