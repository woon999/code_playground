# IPC 
## 프로세스
프로세스는 실행중인 프로그램(프로그램 + PCB)이다.

각 프로세스는 프로세스 ID 또는 단순히 PID(Process Identification number)라고 하는 고유한 양의 정수로 식별된다. 
- 커널은 일반적으로 프로세스 ID를 구성 가능한 32767로 제한함 
- 프로세스 ID가 이 제한에 도달하면 시스템 프로세스 범위는 다시 재설정됨
- 해당 카운터에서 사용되지 않은 프로세스 ID는 새로 생성된 프로세스에 할당됨

```c
#include <sys/types.h>
#include <unistd.h>

pid_t getpid(void);
pid_t getppid(void);
```
- getpid(): 호출 프로세스의 프로세스 ID를 반환
- getppid(): 호출 프로세스의 부모 PID를 반환


./processinfo.c
- 호출 프로세스의 PID와 PPID를 알 수 있는 프로그램





---
refs 
- https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_process_information.htm