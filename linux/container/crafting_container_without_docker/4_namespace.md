# 4. 컨테이너 만들기 - namespace
# 초기 컨테이너
'전용 루트 파일 시스템'으로 충분하다고 생각 (pivot_root)

## 문제점
- 컨테이너에서 호스트의 다른 프로세스 보임
- 컨테이너에서 호스트의 포트 사용 (이전에 1번 container nginx 에서 prot 80 접근)
- 컨테이너에 루트 권한 있음

<br>

# 네임스페이스 
- 프로세스에 격리된 환경 제공
- 리눅스 커널 기능(2002, v2.4.19)
- 컨테이너 레디(2013, v3.8)

## 특징
- 모든 프로세스는 타입별로 네임스페이스에 속함
- 자식 프로세스는 부모의 네임스페이스를 상속함 

## 네임스페이스 종류
마운트 네임스페이스만으로는 격리가 부족해서 다양한 종류의 네임스페이스가 이후에 많이 생겨났다
- 마운트 네임스페이스: 마운트 포인트 격리 (2002)
- UTS 네임스페이스: hostname, domain name 격리 (2006)
- IPC 네임스페이스: IPC 격리 (2006)
- PID 네임스페이스: pid 네임스페이스 격리 (2008)
- 네트워크 네임스페이스: 네트워크 스택 가상화 및 격리 (2009)
- USER 네임스페이스: UID/GID 넘버스페이스 격리 (2009)


## 사용방법
```
unshare [옵션] [프로그램 [arguments ... ]]
(옵션)
-m, --mount
-u, --uts
-i, --ipc
-p, --pid
-n, --net
-U, --user
```


## 네임스페이스 확인 방법 #1
```
ls -al /proc/$$/ns;
readlink /proc/$$/ns/mnt;

root@ubuntu1804:/tmp# ls -al /proc/$$/ns
total 0
dr-x--x--x 2 root root 0 Jul  4 11:14 .
dr-xr-xr-x 9 root root 0 Jul  4 11:14 ..
lrwxrwxrwx 1 root root 0 Jul  4 11:14 cgroup -> 'cgroup:[4026531835]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 ipc -> 'ipc:[4026531839]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 mnt -> 'mnt:[4026531840]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 net -> 'net:[4026531993]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 pid -> 'pid:[4026531836]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 pid_for_children -> 'pid:[4026531836]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 user -> 'user:[4026531837]'
lrwxrwxrwx 1 root root 0 Jul  4 11:14 uts -> 'uts:[4026531838]'

root@ubuntu1804:/tmp# readlink /proc/$$/ns/mnt
mnt:[4026531840]
```


## 네임스페이스 확인 방법 #2
lsns(list namespace)
- -t: 네임스페이스 타입 ex) pid, mnt, uts, ...
- -p: 조회할 process id
```
lsns -p 1;
lsns -t mnt p 1;

root@ubuntu1804:/tmp# lsns -p 1;
        NS TYPE   NPROCS PID USER COMMAND
4026531835 cgroup    120   1 root /sbin/init
4026531836 pid       120   1 root /sbin/init
4026531837 user      120   1 root /sbin/init
4026531838 uts       120   1 root /sbin/init
4026531839 ipc       120   1 root /sbin/init
4026531840 mnt       116   1 root /sbin/init
4026531993 net       120   1 root /sbin/init

root@ubuntu1804:/tmp# lsns -t mnt -p 1
        NS TYPE NPROCS PID USER COMMAND
4026531840 mnt     116   1 root /sbin/init
```

<br>

# 1. 마운트 네임스페이스
```zsh
unshare -m
lsns -p $$
```

4026532201(마운트 네임스페이스)만 격리되어 있어서 NPROCS(프로세스 개수)도 다르고 PID(프로세스 ID)도 1이 아닌 다른 것을 확인할 수 있다.
```
root@ubuntu1804:/tmp# unshare -m
root@ubuntu1804:/tmp# lsns -p $$
        NS TYPE   NPROCS   PID USER COMMAND
4026531835 cgroup    121     1 root /sbin/init
4026531836 pid       121     1 root /sbin/init
4026531837 user      121     1 root /sbin/init
4026531838 uts       121     1 root /sbin/init
4026531839 ipc       121     1 root /sbin/init
4026531993 net       121     1 root /sbin/init
4026532201 mnt         2 18671 root -bash

// exit하여 해당 격리 환경에서 나올 수 있다
root@ubuntu1804:/tmp# exit
logout
```

<br>

# 2. UTS 네임스페이스
- UTS, Unix Time Sharing 서버 나눠쓰기
- 호스트명, 도메인명 격리

<img width="500" alt="스크린샷 2023-07-04 오후 8 28 21" src="https://github.com/loosie/code_playground/assets/54282927/fc7aa204-c757-42ea-930a-27c00c346ad4">

```zsh
unshare -u
lsns -p $$
```

4026532201 네임스페이스가 UTS 타입으로 격리된 것을 확인할 수 있다
```zsh
root@ubuntu1804:/tmp# unshare -u
root@ubuntu1804:/tmp# lsns -p $$
        NS TYPE   NPROCS   PID USER COMMAND
4026531835 cgroup    120     1 root /sbin/init
4026531836 pid       120     1 root /sbin/init
4026531837 user      120     1 root /sbin/init
4026531839 ipc       120     1 root /sbin/init
4026531840 mnt       116     1 root /sbin/init
4026531993 net       120     1 root /sbin/init
4026532201 uts         2 18684 root -bash


root@ubuntu1804:/tmp# hostname
ubuntu1804
root@ubuntu1804:/tmp# hostname Sam
root@ubuntu1804:/tmp# hostname
Sam
```

<br>

# 3. IPC 네임스페이스
- IPC(Inter-Process Communication) 격리
- Shared Memory, Pipe, Message Queue 등

```zsh
unshare -i
lsns -p $$
```

4026532201 네임스페이스가 IPC 타입으로 격리된 것을 확인할 수 있다. (IPC 네임스페이스를 공유하는 프로세스끼리만 IPC 가능)
```zsh
root@ubuntu1804:/tmp# unshare -i
root@ubuntu1804:/tmp# lsns -p $$
        NS TYPE   NPROCS   PID USER COMMAND
4026531835 cgroup    120     1 root /sbin/init
4026531836 pid       120     1 root /sbin/init
4026531837 user      120     1 root /sbin/init
4026531838 uts       120     1 root /sbin/init
4026531840 mnt       116     1 root /sbin/init
4026531993 net       120     1 root /sbin/init
4026532201 ipc         2 18699 root -bash
```

<br>

# 4. PID 네임스페이스
- PID(Process ID) 넘버 스페이스를 격리
- 부모-자식 네임스페이스 중첩 구조
- 부모 네임스페이스 - (see) -> 자식네임스페이스

<img width="500" alt="스크린샷 2023-07-04 오후 8 28 02" src="https://github.com/loosie/code_playground/assets/54282927/e8f44273-0409-4b6e-bfaf-782291f778a5">


## pid 1
- init 프로세스 (커널이 생성, 커널은 0번 프로세스)
- 시그널 처리
- 좀비, 고아 프로세스 처리
- 죽으면 시스템 패닉(reboot)

<img width="500" alt="스크린샷 2023-07-04 오후 8 30 00" src="https://github.com/loosie/code_playground/assets/54282927/4cf4d357-9b31-46fc-be0a-80de70e505cd">

## 컨테이너 pid1
- unshare 할 때 fork하여 자식 PID 네임스페이스의 pid1로 실행
- 시그널 처리
- 좀비, 고아프로세스 처리
- 죽으면 컨테이너 종료

<img width="500" alt="스크린샷 2023-07-04 오후 8 31 11" src="https://github.com/loosie/code_playground/assets/54282927/c3c45d56-a322-47cd-90b5-a91b41961779">

```zsh
unshare -fp --mount-proc /bin/sh
```
- -p: pid namespace
- -f: fork
- --mount-proc: proc 파일시스템 마운트

## /proc
- 메모리 기반 가상 파일시스템
- 커널이 관리하는 시스템 정보 제공
- 시스템 모니터링과 분석에 활용


## terminal1(container)
```zsh
root@ubuntu1804:/tmp# unshare -fp --mount-proc /bin/sh
# ps -ef
UID        PID  PPID  C STIME TTY          TIME CMD
root         1     0  0 11:33 pts/0    00:00:00 /bin/sh
root         2     1  0 11:33 pts/0    00:00:00 ps -ef
# lsns -t pid -p 1
        NS TYPE NPROCS PID USER COMMAND
4026532202 pid       2   1 root /bin/sh

```

## terminal2(host)
```zsh
root@ubuntu1804:/tmp# ps -ef | grep "/bin/sh"
root     18739 18652  0 11:33 pts/0    00:00:00 unshare -fp --mount-proc /bin/sh
root     18740 18739  0 11:33 pts/0    00:00:00 /bin/sh
root     18756 18743  0 11:34 pts/1    00:00:00 grep --color=auto /bin/sh
root@ubuntu1804:/tmp# lsns -t pid -p 18740
        NS TYPE NPROCS   PID USER COMMAND
4026532202 pid       1 18740 root /bin/sh
```

pid 네임스페이스 생성 후 컨테이너 환경과 host 환경에서 각 프로세스를 확인해보면 host에서 unshare의 자식 프로세스로 pid 네임스페이스가 할당되어 있는 것을 확인할 수 있다. (ns 비교해보면 4026532202로 일치함)
> root     18739 18652  0 11:33 pts/0    00:00:00 unshare -fp --mount-proc /bin/sh
> root     **18740** 18739  0 11:33 pts/0    00:00:00 /bin/sh


호스트에서 해당 프로세스를 KILL하면 컨테이너 프로세스가 종료된다

## terminal2(host)
```zsh
root@ubuntu1804:/tmp# kill -SIGKILL 18740
```

## terminal1(container)
```zsh
# Killed
```


<br>

# 5. 네트워크 네임스페이스
- 네트워크 스택 경리
- 네트워크 가상화, 가상 인터페이스(장치) 사용

<img width="550" alt="스크린샷 2023-07-04 오후 8 40 37" src="https://github.com/loosie/code_playground/assets/54282927/8568c021-4b20-4d0f-a6a7-367e4c75520f">


## 네트워크 네임스페이스의 가상장치
- 여러 네트워크 네임스페이스에 걸쳐 있을 수 없음
- 다른 네트워크 네임스페이스로 이동할 수 있음 
- ex) veth, bridge, vxlan, ...

<img width="550" alt="스크린샷 2023-07-04 오후 8 41 05" src="https://github.com/loosie/code_playground/assets/54282927/1b6dbff8-8589-4080-ac32-feaef4b967f2">


## 네트워크 네임스페이스 삭제
- 가상 인터페이스: 삭제됨
- 물리 인터페이스: 기존 네임스페이스로 복원됨 

<img width="550" alt="스크린샷 2023-07-04 오후 8 41 55" src="https://github.com/loosie/code_playground/assets/54282927/d5079e56-73b0-4cf6-bf8f-b04f781e3076">

<br>

# 네트워크 네임스페이스 1:1 통신
네트워크 네임스페이스를 격리하여 RED/BLUE 통신을 진행해보자

<img width="550" alt="스크린샷 2023-07-04 오후 8 43 42" src="https://github.com/loosie/code_playground/assets/54282927/731d6fff-0af2-4067-beaa-29b269d47b88">


## 1) veth pair 설정
```zsh
ip link add veth0 type veth peer name veth1
```

<img width="550" alt="스크린샷 2023-07-04 오후 8 44 38" src="https://github.com/loosie/code_playground/assets/54282927/503951f4-f502-4dbf-aba1-79d76d1a7e3a">

ip link 명령어로 새롭게 생성된 veth 확인할 수 있다 (7,8)
```zsh
root@ubuntu1804:/tmp# ip link add veth0 type veth peer name veth1
root@ubuntu1804:/tmp# ip link
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN mode DEFAULT group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP mode DEFAULT group default qlen 1000
    link/ether 08:00:27:4b:a1:76 brd ff:ff:ff:ff:ff:ff
3: eth1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP mode DEFAULT group default qlen 1000
    link/ether 08:00:27:a4:c2:79 brd ff:ff:ff:ff:ff:ff
4: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN mode DEFAULT group default
    link/ether 02:42:d7:cc:8a:7c brd ff:ff:ff:ff:ff:ff
7: veth1@veth0: <BROADCAST,MULTICAST,M-DOWN> mtu 1500 qdisc noop state DOWN mode DEFAULT group default qlen 1000
    link/ether fe:ad:19:84:cb:03 brd ff:ff:ff:ff:ff:ff
8: veth0@veth1: <BROADCAST,MULTICAST,M-DOWN> mtu 1500 qdisc noop state DOWN mode DEFAULT group default qlen 1000
    link/ether 6a:9f:e0:67:9f:ad brd ff:ff:ff:ff:ff:ff
```

## 2) RED/BLUE 각 네트워크 네임스페이스
```zsh
ip netns add RED
ip netns add BLUE
```

<img width="550" alt="스크린샷 2023-07-04 오후 8 46 35" src="https://github.com/loosie/code_playground/assets/54282927/1ca796a4-4d8e-4305-be96-6b87db0de96f">


## 3) veth0 -> RED, veth1 -> BLUE

```zsh
ip link set veth0 netns RED
ip link set veth1 netns BLUE
```

<img width="550" alt="스크린샷 2023-07-04 오후 8 48 45" src="https://github.com/loosie/code_playground/assets/54282927/8b7041bc-7b74-455f-a18e-55caa6ab162f">


## 4) veth up
```
ip netns exec RED ip link set veth0 up
ip netns exec BLUE ip link set veth1 up
```

<img width="550" alt="스크린샷 2023-07-04 오후 8 49 57" src="https://github.com/loosie/code_playground/assets/54282927/2eece5c1-7d21-4ab5-aaa7-cfd2b16869dd">



## 5) ip 설정
```
ip netns exec RED ip addr add 11.11.11.2/24 dev veth0
ip netns exec BLUE ip addr add 11.11.11.3/24 dev veth1
```

여기까지 1:1 통신 준비완료! 

<img width="550" alt="스크린샷 2023-07-04 오후 8 50 56" src="https://github.com/loosie/code_playground/assets/54282927/794731f0-5704-4a47-8ae3-fcdfb7e749aa">


## 6-1) RED 접속
네트워크 네임스페이스를 생성하면 /var/run/netns에 해당 파일을 생성해준다 
```zsh
root@ubuntu1804:/tmp# ls /var/run/netns
BLUE  RED
```

그래서 다음과 같이 경로를 지정해서 nsenter 한다
```
nsenter --net=/var/run/netns/RED
ip a
ip route
```


### terminal(RED)
```zsh
root@ubuntu1804:/tmp# nsenter --net=/var/run/netns/RED
root@ubuntu1804:/tmp# ip a
1: lo: <LOOPBACK> mtu 65536 qdisc noop state DOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
8: veth0@if7: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default qlen 1000
    link/ether 6a:9f:e0:67:9f:ad brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet 11.11.11.2/24 scope global veth0
       valid_lft forever preferred_lft forever
    inet6 fe80::689f:e0ff:fe67:9fad/64 scope link
       valid_lft forever preferred_lft forever
root@ubuntu1804:/tmp# ip route
11.11.11.0/24 dev veth0 proto kernel scope link src 11.11.11.2
```

### terminal(host)
```zsh
root@ubuntu1804:/tmp# ip a
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP group default qlen 1000
    link/ether 08:00:27:4b:a1:76 brd ff:ff:ff:ff:ff:ff
    inet 10.0.2.15/24 brd 10.0.2.255 scope global dynamic eth0
       valid_lft 79839sec preferred_lft 79839sec
    inet6 fe80::a00:27ff:fe4b:a176/64 scope link
       valid_lft forever preferred_lft forever
3: eth1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP group default qlen 1000
    link/ether 08:00:27:a4:c2:79 brd ff:ff:ff:ff:ff:ff
    inet 192.168.104.2/24 brd 192.168.104.255 scope global eth1
       valid_lft forever preferred_lft forever
    inet6 fe80::a00:27ff:fea4:c279/64 scope link
       valid_lft forever preferred_lft forever
4: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default
    link/ether 02:42:d7:cc:8a:7c brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
    inet6 fe80::42:d7ff:fecc:8a7c/64 scope link
       valid_lft forever preferred_lft forever
root@ubuntu1804:/tmp# ip route
default via 10.0.2.2 dev eth0 proto dhcp src 10.0.2.15 metric 100
10.0.2.0/24 dev eth0 proto kernel scope link src 10.0.2.15
10.0.2.2 dev eth0 proto dhcp scope link src 10.0.2.15 metric 100
172.17.0.0/16 dev docker0 proto kernel scope link src 172.17.0.1 linkdown
192.168.104.0/24 dev eth1 proto kernel scope link src 192.168.104.2
```

RED에 접속한 후 host와 테이블을 비교해보면 RED는 loopback과 veth0만 존재하는 것을 확인할 수 있다

## 6-2) BLUE 접속
```
nsenter --net=/var/run/netns/BLUE
ip a
ip route
```


## 7-1) ping 테스트

### terminal(RED)
BLUE 네임스페이스 IP로 핑 테스트를 보내면 정상적으로 처리된다 
```zsh
root@ubuntu1804:/tmp# ping 11.11.11.3
PING 11.11.11.3 (11.11.11.3) 56(84) bytes of data.
64 bytes from 11.11.11.3: icmp_seq=1 ttl=64 time=1.47 ms
64 bytes from 11.11.11.3: icmp_seq=2 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=3 ttl=64 time=0.027 ms
64 bytes from 11.11.11.3: icmp_seq=4 ttl=64 time=0.027 ms
64 bytes from 11.11.11.3: icmp_seq=5 ttl=64 time=0.025 ms
...
```

### terminal(BLUE)
반대로 RED 네임스페이스 IP로 핑 테스트를 보내도 정상적으로 처리되는 것을 확인할 수 있다
```zsh
root@ubuntu1804:/tmp# ping 11.11.11.2
PING 11.11.11.2 (11.11.11.2) 56(84) bytes of data.
64 bytes from 11.11.11.2: icmp_seq=1 ttl=64 time=0.016 ms
64 bytes from 11.11.11.2: icmp_seq=2 ttl=64 time=0.027 ms
64 bytes from 11.11.11.2: icmp_seq=3 ttl=64 time=0.026 ms
64 bytes from 11.11.11.2: icmp_seq=4 ttl=64 time=0.026 ms
64 bytes from 11.11.11.2: icmp_seq=5 ttl=64 time=0.029 ms
...
```

<br>

이를 통해 RED/BLUE 네트워크 격리와 격리된 환경에서의 통신까지 확인해보았다. 이제 cgroups를 통해 자원을 제어해보자. exit후에 실습환경 초기화를 하자.
```
ip netns del RED;
ip netns del BLUE;
```

<br>

# refs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/
