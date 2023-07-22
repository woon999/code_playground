# 프로세스 생성 및 종료
- 프로세스 생성은 fork() 시스템 호출을 통해 이루어진다  
- 새로 생성된 프로세스를 자식 프로세스라고 하고 이를 시작한 프로세스(또는 실행이 시작된 프로세스)를 부모 프로세스라고 한다

fork() 시스템 호출 이후에 이제 부모 프로세스와 자식 프로세스라는 두 개의 프로세스가 있다. 반환 값을 통해서 그들을 쉽게 구별할 수 있다 

![](https://www.tutorialspoint.com/inter_process_communication/images/system_call.jpg)


##  fork(): 자식 프로세스 생성
fork() 시스템 호출은 다음 세 값 중 하나를 반환한다
- 오류를 나타내는 음수 값, 즉 하위 프로세스 생성 실패
- 자식 프로세스에 대해 0을 반환
- 부모 프로세스에 대해 양수 값을 반환. 이 값은 새로 생성된 자식 프로세스의 프로세스 ID이다
```c
#include <sys/types.h>
#include <unistd.h>

pid_t fork(void);
```

<br>

# basic fork
- 일반적으로 fork() 호출 후 자식 프로세스와 부모 프로세스는 서로 다른 작업을 수행한다
- 동일한 작업을 실행해야 하는 경우 각 fork() 호출에 대해 2의 거듭제곱 n번 실행된다
- 여기서 n 은 fork()가 호출되는 횟수이다

## basicfork.c 실행 결과
```zsh
> ./basicfork
Called fork() system call
Called fork() system call
```


위의 경우 fork()가 한 번 호출되므로 출력이 두 번 인쇄된다(2 power 1). fork()가 3번 호출되면 출력은 8번 인쇄된다(2 거듭제곱 3). 5번 호출되면 32번 등을 인쇄한다.

## pids_after_fork.c 실행 결과
```zsh
> ./pids_after_fork
Before fork: Process id is 77306

// fork()후 자식, 부모 프로세스 서로 다른 작업 수행 

// child 프로세스
This is child process
Process id is 77308 and PPID is 77306

// parent 프로세스(sleep(2))
This is parent process
Process id is 77306 and PPID is 67074
Newly created process id or child pid is 77308
```

<br>

# 프로세스 종료
프로세스는 두 가지 방법 중 하나로 종료할 수 있다
- 비정상적으로 종료 신호와 같은 특정 신호가 전달될 때 발생한다
- 일반적으로 _exit() 시스템 호출(또는 _Exit() 시스템 호출) 또는 exit() 라이브러리 함수를 사용한다 

_exit()와 exit()의 차이점은 주로 정리 작업이다. 
- exit ()는 컨트롤을 커널로 다시 반환하기 전에 일부 정리를 수행하는 반면
- _exit() (또는 _Exit())는 컨트롤을 커널로 즉시 다시 반환한다
 
## atexit_sample.c 실행 결과 (exit)
```zsh
> ./atexit_sample
Hello, World!
Called cleanup function - exitfunc()
```

## at_exit_sample.c 실행 결과 (_exit)
```zsh
> ./at_exit_sample
Hello, World!
```

---

refs
- https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_process_creation_termination.htm