# 6. 컨테이너 만들기 (without docker)
지금까지 학습한 내용을 토대로 Dokcer 없이 각 RED, BLUE 컨테이너를 만든 다음 1대1 통신을 해보자. 먼저 이미지를 준비를 할 것이다. 이전에 사용했던 myroot와 tools를 lower 레이어로 사용할 것이다. 

<img width="671" alt="스크린샷 2023-07-06 오후 10 02 21" src="https://github.com/loosie/code_playground/assets/54282927/728865b0-18ff-4a6e-bc85-bd2e056fcc57">


<br>

# 1. 이미지 준비: myroot

[1_process_isolation](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/1_process_isolation.md)에서 만들어둔 myroot가 제대로 있는지 확인해보자 
```zsh
root@ubuntu1804:~# tree /tmp/myroot
/tmp/myroot
├── bin
│   ├── ls
│   ├── mkdir
│   ├── mount
│   ├── ps
│   └── sh
├── escape_chroot
├── lib
│   └── x86_64-linux-gnu
│       ├── libblkid.so.1
│       ├── libc.so.6
│       ├── libdl.so.2
│       ├── libgcrypt.so.20
│       ├── libgpg-error.so.0
│       ├── liblzma.so.5
│       ├── libmount.so.1
│       ├── libpcre.so.3
│       ├── libprocps.so.6
│       ├── libpthread.so.0
│       ├── librt.so.1
│       ├── libselinux.so.1
│       ├── libsystemd.so.0
│       └── libuuid.so.1
├── lib64
│   └── ld-linux-x86-64.so.2
├── proc
└── usr
    └── lib
        └── x86_64-linux-gnu
            └── liblz4.so.1

8 directories, 22 files
```

<br>

# 2. 이미지 준비: tools 
tools에는 다음 명령어들이 포함된다. /tmp 디렉토리로 이동해서 미리 만들어둔 다음 스크립트를 다운받아 실행해준다.
- ping: 컨테이너 통신 테스트
- stress: 컨테이너 부하 테스트
- hostname: 호스트네임 변경
- umount: put_old 제거
```
wget https://raw.githubusercontent.com/sam0kim/container-internal/main/scripts/copy_tools.sh;
bash copy_tools.sh;
```

tree를 통해서 확인 
```
root@ubuntu1804:/tmp# tree tools
tools
├── bin
│   ├── hostname
│   ├── ping
│   ├── rm
│   └── umount
├── lib
│   └── x86_64-linux-gnu
│       ├── libblkid.so.1
│       ├── libcap.so.2
│       ├── libc.so.6
│       ├── libdl.so.2
│       ├── libidn.so.11
│       ├── libmount.so.1
│       ├── libm.so.6
│       ├── libpcre.so.3
│       ├── libpthread.so.0
│       ├── libresolv.so.2
│       ├── librt.so.1
│       ├── libselinux.so.1
│       └── libuuid.so.1
├── lib64
│   └── ld-linux-x86-64.so.2
└── usr
    ├── bin
    │   ├── stress
    │   └── which
    └── lib
        └── x86_64-linux-gnu
            └── libnettle.so.6

8 directories, 21 files
```

<br>

# 3. 컨테이너 네트워크
[4_namespace#network_namespace](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/4_namespace.md#5-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EB%84%A4%EC%9E%84%EC%8A%A4%ED%8E%98%EC%9D%B4%EC%8A%A4)에서 했던 내용 그대로이다. RED, BLUE 컨테이너를 만들어줄 것이다

## 1) 네트워크 네임스페이스 생성
```
ip netns add RED;
ip netns add BLUE;
```

## 2) RED(veth0), BLUE(veth1) 네트워크 연결
```zsh
ip link add veth0 netns RED type veth peer name veth1 netns BLUE;
```

각 RED, BLUE에 veth0, veth1가 제대로 추가되었는지 확인
```
ip netns exec RED ip l;
ip netns exec BLUE ip l;

---
root@ubuntu1804:/tmp# ip netns exec RED ip l;
1: lo: <LOOPBACK> mtu 65536 qdisc noop state DOWN mode DEFAULT group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
2: veth0@if2: <BROADCAST,MULTICAST> mtu 1500 qdisc noop state DOWN mode DEFAULT group default qlen 1000
    link/ether fe:44:db:b0:1c:0f brd ff:ff:ff:ff:ff:ff link-netnsid 0
    
root@ubuntu1804:/tmp# ip netns exec BLUE ip l;
1: lo: <LOOPBACK> mtu 65536 qdisc noop state DOWN mode DEFAULT group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
2: veth1@if2: <BROADCAST,MULTICAST> mtu 1500 qdisc noop state DOWN mode DEFAULT group default qlen 1000
    link/ether 82:a5:7f:c0:69:f1 brd ff:ff:ff:ff:ff:ff link-netnsid 0
```

## 3) IP 할당 및 전원 켜기
```
ip netns exec RED ip addr add dev veth0 11.11.11.2/24;
ip netns exec RED ip link set veth0 up;

ip netns exec BLUE ip addr add dev veth1 11.11.11.3/24;
ip netns exec BLUE ip link set veth1 up;
```


## 4) network namespace 이름 정의된 공간 확인
``` 
ls /var/run/netns 
#=> RED BLUE
```

이제 컨테이너 네트워크 구성은 완료되었다

<img width="632" alt="스크린샷 2023-07-06 오후 10 03 14" src="https://github.com/loosie/code_playground/assets/54282927/540b401d-cf41-471d-b45f-d485bb10f6af">

<br> 

# 4-1. RED Cgroup 생성
네트워크 자원 격리를 해주었으니 이젠 [5_cgorup](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/5_cgroup.md)에서 다룬 내용을 그대로 적용해서 자원을 할당해주자


## 1) cpu, memory Cgroups 생성
``` 
mkdir /sys/fs/cgroup/cpu/red;
mkdir /sys/fs/cgroup/memory/red;
```

다음과 같이 디렉토리를 생성하면 커널이 알아서 cgroup 관련 파일들을 생성해준다
```zsh
ls /sys/fs/cgroup/cpu/red;

---
root@ubuntu1804:/tmp# ls /sys/fs/cgroup/cpu/red;
cgroup.clone_children  cpuacct.usage_percpu_sys   cpu.shares
cgroup.procs           cpuacct.usage_percpu_user  cpu.stat
cpuacct.stat           cpuacct.usage_sys          notify_on_release
cpuacct.usage          cpuacct.usage_user         tasks
cpuacct.usage_all      cpu.cfs_period_us
cpuacct.usage_percpu   cpu.cfs_quota_us
```

##  2) RED Cgroups 설정
- cpu 최대 사용률 40% 제한
- 메모리 최대 200MB 제한
- swap off: 메모리 부족할 경우 메모리 내용을 Disk를 사용하지않도록 설정
```
echo 40000 > /sys/fs/cgroup/cpu/red/cpu.cfs_quota_us;
echo 209715200 > /sys/fs/cgroup/memory/red/memory.limit_in_bytes;
echo 0 > /sys/fs/cgroup/memory/red/memory.swappiness;
```

## 3) RED 네임스페이스 격리
지금까지 격리에 필요했던 모든 네임스페이스를 옵션에 추가하여 다음과 같이 격리한다
- -m: mount
- -u: uts
- -i: ipc
- -p: pid namespace
- -f: fork
- network: /var/run/netns/RED
```
unshare -m -u -i -fp nsenter --net=/var/run/netns/RED /bin/sh;
```


## 4) RED Cgroup 할당 
아직 Cgroup은 할당되지 않았다. 현재 3) 과정을 거쳐서 pid 네임스페이스를 격리된 환경이기 때문에 pid = 1로 할당해준다. 그러면 이젠 cgroup에 설정한대로 cpu 40%, memory 200MB, swap off한 상태로 전환된다 
```
echo "1" > /sys/fs/cgroup/cpu/red/cgroup.procs;
echo "1" > /sys/fs/cgroup/memory/red/cgroup.procs;
```

## 5) 오버레이 마운트
이제 [3_solve_dup_problem](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/3_solve_dup_problem.md)에서 다룬 중복 문제를 해결하기 위해 파일시스템을 만들어 줄 단계이다. 오버레이마운트를 하기 위해 디렉토리 만들자
```
mkdir /redfs;
mkdir /redfs/container;
mkdir /redfs/work;
mkdir /redfs/merge;
```

오버레이마운트
- lower_dir: tools, myroot
- upper_dir: work
- merge: merge
```
mount -t overlay overlay -o lowerdir=/tmp/tools:/tmp/myroot,upperdir=/redfs/container,workdir=/redfs/work /redfs/merge
```

merge 뷰에 채워져있으면 제대로 오버레이마운트가 된 것이다
```zsh
# tree /redfs/merge
/redfs/merge
├── bin
│   ├── hostname
│   ├── ls
│   ├── mkdir
│   ├── mount
│   ├── ping
│   ├── ps
│   ├── rm
│   ├── sh
│   └── umount
├── escape_chroot
├── lib
│   └── x86_64-linux-gnu
│       ├── libblkid.so.1
│       ├── libcap.so.2
│       ├── libc.so.6
│       ├── libdl.so.2
│       ├── libgcrypt.so.20
│       ├── libgpg-error.so.0
│       ├── libidn.so.11
│       ├── liblzma.so.5
│       ├── libmount.so.1
│       ├── libm.so.6
│       ├── libpcre.so.3
│       ├── libprocps.so.6
│       ├── libpthread.so.0
│       ├── libresolv.so.2
│       ├── librt.so.1
│       ├── libselinux.so.1
│       ├── libsystemd.so.0
│       └── libuuid.so.1
├── lib64
│   └── ld-linux-x86-64.so.2
├── proc
└── usr
    ├── bin
    │   ├── stress
    │   └── which
    └── lib
        └── x86_64-linux-gnu
            ├── liblz4.so.1
            └── libnettle.so.6

9 directories, 33 files
```

<img width="668" alt="스크린샷 2023-07-06 오후 9 32 16" src="https://github.com/loosie/code_playground/assets/54282927/0deec4b4-3e9a-4918-b6f3-2182746d9763">

<br>

# 4-2. RED pivot_root
이제 [2_prcoess_isolation](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/2_prevent_container_escape.md)에서 다룬 탈옥 문제를 막기위해 pivot_root를 할 차례이다

## 1) put_old 생성
```
mkdir -p /redfs/merge/put_old;
```

## 2) pivot_root
```
cd /redfs/merge;
pivot_root . put_old;
```

## 3) pivot_root 확인
```
cd /
ls /
#=> bin  escape_chroot  lib  lib64	proc  put_old  usr

ls put_old
#=> bin   dev  home        initrd.img.old  lib64	   media  opt	redfs  run   srv  tmp  vagrant	vmlinuz
boot  etc  initrd.img  lib	       lost+found  mnt	  proc	root   sbin  sys  usr  var	vmlinuz.old
```

## 4) put_old 제거
현재 컨테이너가 속해있는 host의 root filesystem이 put_old 밑에 부착되어 있기 때문에 이 경로를 남겨두게 되면 보안상 위험하다. 경로 제거하기 위해 umount를 해줘야한다. 그러면 먼저 proxy 파일시스템을 mount해줘야한다.
```
// mount해주기 위해 proc 파일시스템 마운트
mount -t proc proc /proc; 

// put_old 제거
umount -l put_old;
rm -rf put_old;
```

<img width="635" alt="스크린샷 2023-07-06 오후 9 35 35" src="https://github.com/loosie/code_playground/assets/54282927/ef83982b-bae3-42fd-a23b-98b8764399b4">


## 5) hostname 변경
마지막으로 hostname 변경해주면 RED 컨테이너 완성이다!
```zsh
hostname RED;
```

```zsh
# hostname
ubuntu1804
# hostname RED
# hostname
RED
```

<img width="635" alt="스크린샷 2023-07-06 오후 9 38 58" src="https://github.com/loosie/code_playground/assets/54282927/c19bd97e-2cba-4e07-a52f-142a0513b4bd">


<br>


# 5. BLUE 컨테이너 만들기
BLUE 컨테이너는 4-1, 4-2 내용을 그대로 이름만 바꿔서 만들어주면 된다. 이번에는 설명없이 바로 빠르게 만들어보자.
## 1) BLUE Cgroups 생성
``` 
mkdir /sys/fs/cgroup/cpu/blue;
mkdir /sys/fs/cgroup/memory/blue;
```

##  2) BLUE Cgroups 설정
```
echo 40000 > /sys/fs/cgroup/cpu/blue/cpu.cfs_quota_us;
echo 209715200 > /sys/fs/cgroup/memory/blue/memory.limit_in_bytes;
echo 0 > /sys/fs/cgroup/memory/blue/memory.swappiness;
```

## 3) BLUE 네임스페이스 격리
```
unshare -m -u -i -fp nsenter --net=/var/run/netns/BLUE /bin/sh;
```


## 4) BLUE Cgroup 할당 
pid 네임스페이스를 격리한 상황이라서 id = 1로 할당
```
echo "1" > /sys/fs/cgroup/cpu/blue/cgroup.procs;
echo "1" > /sys/fs/cgroup/memory/blue/cgroup.procs;
```

## 5) 오버레이 마운트
```
mkdir /bluefs;
mkdir /bluefs/container;
mkdir /bluefs/work;
mkdir /bluefs/merge;

mount -t overlay overlay -o lowerdir=/tmp/tools:/tmp/myroot,upperdir=/bluefs/container,workdir=/bluefs/work /bluefs/merge;
```

## 6) put_old 생성
```
mkdir -p /bluefs/merge/put_old
```

## 7) pivot_root
```
cd /bluefs/merge;
pivot_root . put_old;
```

pivot_root 확인
```
cd /
ls /
#=> bin  escape_chroot  lib  lib64	proc  put_old  usr

ls put_old
#=> bin   dev  home        initrd.img.old  lib64	   media  opt	redfs  run   srv  tmp  vagrant	vmlinuz
boot  etc  initrd.img  lib	       lost+found  mnt	  proc	root   sbin  sys  usr  var	vmlinuz.old
```

## 8) put_old 제거
```
mount -t proc proc /proc;
umount -l put_old;
rm -rf put_old;
```

## 10) hostname 변경
```zsh
hostname BLUE;
```


<img width="400" alt="스크린샷 2023-07-06 오후 9 46 38" src="https://github.com/loosie/code_playground/assets/54282927/3751c0bc-4bb5-4797-b15e-c25c9540524d">

<br>

# 6. RED, BLUE 컨테이너 테스트
RED, BLUE 컨테이너를 모두 만들었다. 이제 컨테이너가 제대로 동작하는지 확인해보자

## 1) ping 테스트
### terminal(RED)
```
# ping 11.11.11.3
PING 11.11.11.3 (11.11.11.3) 56(84) bytes of data.
64 bytes from 11.11.11.3: icmp_seq=1 ttl=64 time=1.52 ms
64 bytes from 11.11.11.3: icmp_seq=2 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=3 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=4 ttl=64 time=0.036 ms
64 bytes from 11.11.11.3: icmp_seq=5 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=6 ttl=64 time=0.031 ms
64 bytes from 11.11.11.3: icmp_seq=7 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=8 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=9 ttl=64 time=0.025 ms
64 bytes from 11.11.11.3: icmp_seq=10 ttl=64 time=0.024 ms
...
```

### terminal(BLUE)
```
# ping 11.11.11.2
PING 11.11.11.2 (11.11.11.2) 56(84) bytes of data.
64 bytes from 11.11.11.2: icmp_seq=1 ttl=64 time=0.015 ms
64 bytes from 11.11.11.2: icmp_seq=2 ttl=64 time=0.025 ms
64 bytes from 11.11.11.2: icmp_seq=3 ttl=64 time=0.024 ms
64 bytes from 11.11.11.2: icmp_seq=4 ttl=64 time=0.037 ms
64 bytes from 11.11.11.2: icmp_seq=5 ttl=64 time=0.030 ms
64 bytes from 11.11.11.2: icmp_seq=6 ttl=64 time=0.024 ms
64 bytes from 11.11.11.2: icmp_seq=7 ttl=64 time=0.024 ms
64 bytes from 11.11.11.2: icmp_seq=8 ttl=64 time=0.024 ms
64 bytes from 11.11.11.2: icmp_seq=9 ttl=64 time=0.032 ms
64 bytes from 11.11.11.2: icmp_seq=10 ttl=64 time=0.031 ms
```


## 2) RED CPU 리소스 확인
### terminal(RED)
```
stress -c 1
```

### terminal(host)
top 명령어로 확인해보면 40% 밑으로 CPU가 사용되는 것을 확인할 수 있다

<img width="549" alt="스크린샷 2023-07-06 오후 9 53 30" src="https://github.com/loosie/code_playground/assets/54282927/fd03baef-0258-4094-81ee-d9b039569fdb">


## 3) RED 메모리 리소스 확인
### terminal(RED)
memory 크기 제한 200MB라서 어느정도 한계에 도달하면 Fail이 뜬다
```
stress --vm 1 --vm-bytes 195M;
stress --vm 1 --vm-bytes 196M;
stress --vm 1 --vm-bytes 200M; # fail!

---
# stress --vm 1 --vm-bytes 195M;
stress: info: [17] dispatching hogs: 0 cpu, 0 io, 1 vm, 0 hdd

# stress --vm 1 --vm-bytes 196M;
stress: info: [19] dispatching hogs: 0 cpu, 0 io, 1 vm, 0 hdd

# stress --vm 1 --vm-bytes 200M;
stress: info: [21] dispatching hogs: 0 cpu, 0 io, 1 vm, 0 hdd
stress: FAIL: [21] (415) <-- worker 22 got signal 9
stress: WARN: [21] (417) now reaping child worker processes
stress: FAIL: [21] (451) failed run completed in 0s
```


### terminal(host)
host에서 dmesg를 하면 해당 로그를 확인할 수 있다
```
[52234.313798] Memory cgroup out of memory: Kill process 27144 (stress) score 967 or sacrifice child
[52234.315324] Killed process 27144 (stress) total-vm:213052kB, anon-rss:203592kB, file-rss:276kB, shmem-rss:0kB
[52234.321968] oom_reaper: reaped process 27144 (stress), now anon-rss:0kB, file-rss:0kB, shmem-rss:0kB
```

<img width="628" alt="스크린샷 2023-07-06 오후 10 07 10" src="https://github.com/loosie/code_playground/assets/54282927/d2d0c072-5301-4979-832b-b293d60ac3c4">

<br>

이로써 컨테이너를 직접 만들어보고 네트워크 ping 테스트, cpu, memory 자원 테스트까지 모두 완료하였다! 

<br>

# 추후 더 공부해야할 리스트
- 컨테이너 네트워크
- 컨테이너 표준화
- 컨테이너 오케스트레이션
- WASM

<br>

# refs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/
