# Container History
- 1979년, 파일시스템 chroot 
- 2002년, 본격적으로 container 개발이 이루어지면서 mount namespace 개발
- 2006년, 파일 시스템만으로 컨테이너를 운영하려다 보니 격리가 더 필요함. 그래서 uts , ipc 네임스페이스개발함
- 2008년, pid, cgroups 개발
- 2009년, 중요한 net 네임스페이스 개발. 이로 인해 host로 부터 독립적인 네트워크 사용 가능. 그와 더불어 가상 네트워크 사용도 가능해짐.
- 2012년, root 권한 문제를 해결하는 user 네임스페이스 개발
- 2013년, docker 등장
<img width="739" alt="스크린샷 2023-06-23 오전 1 16 39" src="https://github.com/loosie/code_playground/assets/54282927/aeb3f5df-be5f-4357-ab43-d14d37702dc3">

# chroot
chroot 명령은 루트 사용자 권한을 조작하는 사용자만 사용 가능하다. 
루트 사용자 권한이 있으면 chroot 명령은 Command를 수행할 때 Directory 매개변수로 지정된 디렉토리로 루트 디렉토리를 변경한다. 
경로 이름의 첫 번째 /(슬래시)는 지정된 Command 및 하위에 대한 Directory로 변경한다.

![image](https://github.com/loosie/code_playground/assets/54282927/9b57df72-e904-4a5f-979b-e2ba27c2ba1f)

<br>

# namespace
## 마운트 네임스페이스 (2002)
마운트 포인트 격리

## UTS 네임스페이스 (2006)
hostname, domain 네임 격리

## IPC 네임스페이스 (2006)
IPC 격리 

## PID 네임스페이스 (2008)
pid 넘버스페이스 격리

## 네트워크 네임스페이스 (2009)
네트워크 스택 가상화 및 격리 

## USER 네임스페이스 (2012)
UID/GID 넘버스페이스 격리

<br>

# Cgroups
Cgroups를 이용해서 자원 할당 및 제어





--- 

https://naleejang.tistory.com/228
