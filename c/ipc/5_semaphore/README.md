# Semaphores
- 여러 프로세스가 공유하는 critical/common 영역을 보호하기 위해서 사용한다
- 예를 들어 여러 사용자가 하나의 프린터(critical/common 영역)만 사용한다고 가정할 때, 3명의 사용자가 동시에 3개의 작업을 수행할 때 모든 작업이 동시에 시작되면 한 사용자의 출력물이 다른 사용자의 출력물과 겹치게 된다 
- 따라서 한 프로세스가 실행 중일 때 중요 섹션을 잠그고 프로세스가 완료되면 잠금을 해제하는 세마포어를 사용하여 이를 보호해야 한다. 이 작업은 각 사용자/프로세스에 대해 반복되어 한 작업이 다른 작업과 겹치지 않도록 한다

장점
- 동기화 및 경합 상태와 같은 문제 방지에 효과적
단점
- 교착 상태를 방지하려면 신중한 관리가 필요하며 올바르게 구현하기 어려울 수 있음

## Semaphores 종류 
기본적으로 세마포어는 두 가지 유형으로 분류
- Binary Semaphores - 0과 1의 두 가지 상태만 있다 (잠금/잠금 해제 or 사용 가능/사용 불가능). 이는 Mutex로 구현한다
- Counting Semaphores - 임의의 리소스 카운트를 허용하는 세마포어를 카운팅 세마포어라고 한다 


### 예시
프린터 5대가 있고(프린터 1대는 1개의 작업만 수용한다고 가정하면 이해가 쉽다) 인쇄할 작업이 3개 있다고 가정해 보자. 이제 3개의 프린터(각각 1개씩)에 3개의 작업이 주어진다. 이 작업이 진행되는 동안 다시 4개의 작업이 들어왔다. 이제 사용 가능한 프린터 2대 중 2대의 작업이 예약되었고 리소스/프린터 중 한 대를 사용할 수 있어야 완료되는 작업이 2대 더 남았다. 리소스 가용성에 따른 이러한 종류의 스케줄링은 세마포어를 계산하여 해결 할 수 있다.

세마포어를 사용하여 동기화를 수행하려면 다음 단계를 따르자
- 1단계 - 세마포어를 생성하거나 이미 존재하는 세마포어에 연결(semget())
- 2단계 - 세마포어에 대한 작업 수행, 즉 리소스를 할당 또는 해제하거나 대기(semop()).
- 3단계 - 메시지 큐에 대한 제어 작업을 수행(semctl())


<br>

# 명령어 
## semget()
이 시스템 호출은 SystemV 세마포어 집합을 생성하거나 할당한다
- 성공 시 유효한 세마포어 식별자(세마포어의 추가 호출에 사용)를 반환하고 실패 시 -1을 반환
```c
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

int semget(key_t key, int nsems, int semflg)
```
- key: 메시지 큐를 인식한다. 키는 임의의 값일 수도 있고 라이브러리 함수 ftok()에서 파생할 수 있는 값일 수도 있다.
- nsems: 세마포어의 수를 지정한다. 2진수인 경우 1이면 세마포어 세트가 1개 필요함을 의미하며, 그렇지 않으면 필요한 세마포어 세트 수에 따라 다르다.
- semflg: IPC_CREAT(세마포어가 존재하지 않는 경우 세마포어 생성) 또는 IPC_EXCL(세마포어가 이미 존재하는 경우 세마포어를 생성하기 위해 IPC_CREAT와 함께 사용되며 호출이 실패함)과 같은 필요한 세마포어 플래그를 지정한다. 권한도 전달해야 한다.



## semop()
이 시스템 호출은 리소스 할당, 리소스 대기 또는 리소스 해제와 같은 SystemV 세마포어 집합에 대한 작업을 수행한다
```c
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

int semop(int semid, struct sembuf *semops, size_t nsemops)
```
- semid: semget()에 의해 생성된 세마포어 집합 식별자를 나타낸다
- semops: 세마포어 집합에서 수행될 연산 배열에 대한 포인터이다. 

구조는 다음과 같다.
```c
struct sembuf {
   unsigned short sem_num; /* Semaphore set num */
   short sem_op; /* Semaphore operation */
   short sem_flg; /* Operation flags, IPC_NOWAIT, SEM_UNDO */
};
```
sem_op는 수행해야 하는 연산을 나타낸다 
- sem_op가 -ve이면 리소스를 할당하거나 획득한다. 다른 프로세스에서 충분한 리소스를 확보할 때까지 호출 프로세스를 차단하여 이 프로세스가 할당할 수 있도록 한다
- sem_op가 0이면 호출 프로세스는 세마포어 값이 0에 도달할 때까지 대기하거나 절전 모드로 전환한다
- sem_op가 +ve이면 리소스를 해제합니다.
- ex) struct sembuf sem_lock = { 0, -1, SEM_UNDO };
- ex) struct sembuf sem_unlock = {0, 1, SEM_UNDO };



## semctl()
```c
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

int semctl(int semid, int semnum, int cmd, …)
```
이 시스템 호출은 SystemV 세마포어에 대한 제어 연산을 수행한다
- semid: 세마포어의 식별자이다. 이 id는 세마포어 식별자이며, semget() 시스템 호출의 반환 값이다.
- semnum: 세마포어의 수이다. 세마포어는 0부터 번호가 매겨진다.
- cmd: 세마포어에 필요한 제어 연산을 수행하는 명령이다.
- 네 번째 인수는 유니온 세미언 유형으로, cmd에 따라 달라진다. 네 번째 인수는 적용되지 않는 경우가 거의 없다.



<br>


# 세마포어를 통해 공유 메모리 cnt write
```c
> ./shm_write_with_semaphore
Total Count is 10000
First Process
SHM_WRITE: PARENT: Now writing
Second Process
SHM_WRITE: PARENT: Writing Done
SHM_WRITE: CHILD: Now writing
SHM_WRITE: CHILD: Writing Done
Writing Process: Complete
```

# 공유 메모리 cnt 읽기
- 세마포어를 사용하여 공유 자원 접근 제한
- 세마포어를 제거할 경우 20,000보다 낮은 숫자가 카운트됨 
```zsh
> ./shm_read_cntr
Reading Process: Shared Memory: Counter is 20000
Reading Process: Reading Done, Detaching Shared Memory
Reading Process: Complete
```

---
refs
- https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_semaphores.htm