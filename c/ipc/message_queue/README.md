# Message Queue
- Queue(큐)는 FIFO의 자료구조를 가지는 통신설비로 커널에서 관리한다
- 메시지 큐는 프로세스들이 메시지를 전달하는 데 사용할 수 있는 데이터 구조이다
    - 각 메시지는 주소, 우선순위, 타임스탬프 등의 메타데이터를 가질 수 있다
- 메시지 큐는 비동기적으로 작동하며, 수신 프로세스가 사용 가능할 때 메시지를 받을 수 있다. 각 메시지는 발신자에 의해 대기열 또는 채널에 배치된다.
- 차이점은 PIPE는 메시지의 흐름, 큐는 메시지를 담는 메모리 공간을 나타낸다. 어디서나 접근할 수 있는 박스라고 보면 된다

장점
- 메시지 큐에 쓸 데이터 번호를 붙이면 여러 프로세스가 동시에 데이터를 쉽게 다룰 수 있음
- 수신 프로세스가 실행 중이 아니더라도 메시지가 대기열에 남아 있도록 하며, 우선순위 기반 메시지 지원
- 커널이 memory protection을 해주어서 동기화 문제 없음

단점
- 파이프보다 복잡. 더 많은 시스템 리소스가 필요하여 통신 속도 느려질 수 있음

## 1. 초기 메세지 큐 상태
```
> ipcs -q
IPC status from <running system> as of Thu Jul 20 18:40:49 KST 2023
T     ID     KEY        MODE       OWNER    GROUP
Message Queues:

```


## 2. 빌드 후 Message queue에 데이터 등록
```
> ./build.sh
+ clang -Wall -Wextra -o sender sender.c
+ clang -Wall -Wextra -o receiver receiver.c


> ./sender
========== messege queue info =============
 message queue info
 msg_lspid : 0
 msg_qnum : 0
 msg_stime : 0
========== messege queue info end =============
message sent
========== messege queue info =============
 message queue info
 msg_lspid : 84539
 msg_qnum : 1
 msg_stime : 1689846142
========== messege queue info end =============
```

## 3. 메시지 큐 상태 확인
```
> ipcs -q
IPC status from <running system> as of Thu Jul 20 18:42:34 KST 2023
T     ID     KEY        MODE       OWNER    GROUP
Message Queues:
q  65536 0x00003039 --rw-rw-rw-  .....    staff
```

## 4. receiver로 메세지 수신
```
> ./receiver
name : REAKWON, age :80
```