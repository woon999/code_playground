# Unnamed Pipe 
- 파이프는 두 프로세스를 연결
- 한 쪽은 전송만하고 다른 쪽은 읽기만 하는 반이중통신(한방향)
- 익명 파이프(Pipe)는 통신을 할 프로세스가 명확하게 알 수 있는 경우 사용 (ex. 부모, 자식 프로세스)


장점
- 단순함

단점 
- 양방향 통신을 위해서는 2개의 파이프가 필요하다
- PPID(같은 부모 프로세스)를 가지는 프로세스들 사이에서만 통신이 가능
```c
> ./build.sh
+ clang -Wall -Wextra -o pipe pipe.c

> ./pipe
My process ID is 61183, My parent process ID is 56700
My process ID is 61185, My parent process ID is 61183
자식 프로세스 -> 부모 메시지: 0
부모 프로세스 -> 자식 메시지: 0

My process ID is 61185, My parent process ID is 61183
My process ID is 61183, My parent process ID is 56700
부모 프로세스 -> 자식 메시지: 1
자식 프로세스 -> 부모 메시지: -1

My process ID is 61185, My parent process ID is 61183
My process ID is 61183, My parent process ID is 56700
부모 프로세스 -> 자식 메시지: 2
자식 프로세스 -> 부모 메시지: -2

My process ID is 61185, My parent process ID is 61183
My process ID is 61183, My parent process ID is 56700
부모 프로세스 -> 자식 메시지: 3
자식 프로세스 -> 부모 메시지: -3

My process ID is 61185, My parent process ID is 61183
My process ID is 61183, My parent process ID is 56700
부모 프로세스 -> 자식 메시지: 4
자식 프로세스 -> 부모 메시지: -4
```