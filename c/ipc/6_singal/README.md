# signal
- signal은 이벤트 발생을 나타내는 프로세스에 대한 알림이다. 
- signal은 소프트웨어 인터럽트라고도 하며 발생을 예측할 수 없으므로 비동기 이벤트라고도 한다.
- signal은 숫자나 이름으로 지정할 수 있으며, 일반적으로 신호 이름은 SIG로 시작한다. 사용 가능한 신호는 다음과 같이 kill -l(신호 이름 나열의 경우 l) 명령으로 확인할 수 있다.
- 기본 동작은 다음과 같다.
    - Default Action
    - Handle the signal
    - Ignore the signal

장점
- 단순, 가벼움, 비동기 알림. 메시지 전송 없음
단점
- 제한된 정보, 순서 및 신뢰성 부족

<br>

# 명령어 
## signal()
signal()은 시그널 생성 시 signum에 언급된 대로 등록된 핸들러를 호출한다. 
- 성공하면 정수 값을 받고 반환값이 없는 함수의 주소를 반환한다. 오류가 발생하면 이 호출은 SIG_ERR을 반환한다.
```c
#include <signal.h>

typedef void (*sighandler_t) (int);
sighandler_t signal(int signum, sighandler_t handler);
```
-  handler: SIG_IGN(신호 무시), SIG_DFL(신호를 기본 메커니즘으로 되돌리기) 또는 사용자 정의 신호 핸들러 또는 함수 주소 중 하나
- signal()을 사용하면 사용자가 등록한 각 signal 처리기를 호출할 수 있지만 차단해야 하는 신호를 마스킹하거나 signal의 동작을 수정하는 등의 미세 조정 및 기타 기능은 불가능하다. 이는 sigaction() 시스템 호출을 사용하면 가능하다.

## sigaction()
 signal 액션을 검사하거나 변경하는 데 사용된다. 
 - act가 null이 아닌 경우, 신호 시그널에 대한 새 액션이 act로부터 설치된다. 
 - oldact가 null이 아닌 경우, oldact가 이전 oldact에 저장된다.
```c
#include <signal.h>

int sigaction(int signum, const struct sigaction *act, struct sigaction *oldact)
```

<br>

# fpe handler signal 등록 후 실행 
```zsh
> ./signal_fpe_handler
Received SIGFPE, Divide by Zero Exception
```

<br>

# signal raising 하기 
- raise() 함수를 사용하여 SIGSTOP 신호를 발생시키는 데 사용
- 사용자가 CTRL + Z (Control + Z) 키를 눌러 SIGSTOP 신호를 생성할 수도 있다
- 이 신호를 발생시키면 프로그램 실행이 중지된다. 실행을 계속하려면 신호(SIGCONT)를 전송한다.
```zsh
> ./signal_raising
Testing SIGSTOP
zsh: suspended (signal)  ./signal_raising
```

---
refs
- https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_signals.htm