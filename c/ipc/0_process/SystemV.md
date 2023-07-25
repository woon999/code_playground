# Related System Calls (System V)
| Category              | System Call | Description                                                                                         |
|-----------------------|-------------|-----------------------------------------------------------------------------------------------------|
| General                  | open()      | 이 시스템 콜은 이미 존재하는 파일을 열거나 새로운 파일을 생성하여 엽니다.                          |
| General                  | creat()     | 새로운 파일을 생성하고 엽니다.                                                                     |
| General                  | read()      | 파일의 내용을 필요한 버퍼로 읽어옵니다.                                                            |
| General                  | write()     | 버퍼의 내용을 파일에 씁니다.                                                                       |
| General                  | close()     | 파일 디스크립터를 닫습니다.                                                                        |
| General                  | stat()      | 파일에 대한 정보를 제공합니다.                                                                     |
| Pipes                | pipe()      | 통신용 파이프를 생성하고, 읽기와 쓰기를 위한 두 개의 파일 디스크립터를 반환합니다.             |
| Named Pipes 또는 FIFO | mknod()     | FIFO를 생성하기 위해 메모리 장치 파일 또는 특수 파일을 생성합니다.                                 |
| Named Pipes 또는 FIFO | mkfifo()    | 새로운 FIFO를 생성합니다.                                                                          |
| Shared Memory           | shmget()    | 새로운 공유 메모리 세그먼트를 생성하거나 기존 세그먼트의 식별자를 가져옵니다.                       |
| Shared Memory           | shmat()     | 공유 메모리 세그먼트를 첨부하여 호출하는 프로세스의 가상 메모리에 세그먼트를 추가합니다.          |
| Shared Memory           | shmdt()     | 공유 메모리 세그먼트를 분리합니다.                                                                 |
| Shared Memory           | shmctl()    | 공유 메모리에 대한 제어 작업을 수행합니다.                                                         |
| Message Queues            | msgget()    | 새로운 메시지 큐를 생성하거나 기존의 메시지 큐에 접근하여 핸들 또는 식별자를 가져옵니다.            |
| Message Queues            | msgsnd()    | 필요한 메시지 큐에 메시지를 전송합니다.                                                             |
| Message Queues            | msgrcv()    | 메시지 큐로부터 메시지를 수신합니다.                                                               |
| Message Queues            | msgctl()    | 메시지 큐에 대한 제어 작업을 수행합니다.                                                            |
| Semaphores              | semget()    | 새로운 세마포어를 생성하거나 기존 세마포어의 식별자를 가져옵니다.                                  |
| Semaphores              | semop()     | 세마포어 값에 대한 세마포어 연산을 수행합니다.                                                      |
| Semaphores              | semctl()    | 세마포어에 대한 제어 작업을 수행합니다.                                                             |
| Signals                | signal()    | 시그널의 처리 방식과 시그널 핸들러를 설정합니다.                                                   |
| Signals                | sigaction() | signal()과 동일하지만, 더 세부적인 제어가 가능하며, 시그널 핸들러 수신 후 특정 작업을 수행할 수 있습니다. |
| Memory Mapping           | mmap()      | 파일을 메모리에 매핑합니다. 이후 파일 접근은 메모리 주소를 통해 직접 접근할 수 있으며, 시스템 콜이 비용이 적습니다. |
| Memory Mapping           | munmap()    | 메모리에 매핑된 파일을 언매핑하여 메모리에서 해제합니다.                                            |
