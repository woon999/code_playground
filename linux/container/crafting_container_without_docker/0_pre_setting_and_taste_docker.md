# 0. 실습환경 준비, docker 격리 환경 맛보기

# 가상 Ubuntu 서버 구동
Mac Intel, Window: Vagrant + Virtualbox

기타 환경: 별도의 Ubuntu 18.04 준비
- 자세한 내용 참고 https://github.com/sam0kim/container-internal/blob/main/README.md

<br>

# docker 격리 환경 맛보기
## 컨테이너
간단하게 컨테이너는 격리하는 환경을 말한다.

컨테이너는 데스크탑, 기존 IT, 클라우드 등 어디서나 실행할 수 있도록 애플리케이션 코드를 해당 라이브러리 및 종속 항목과 함께 패키징한 소프트웨어 실행 유닛이다.

이를 위해 컨테이너는 일종의 운영 체제(OS) 가상화를 활용한다. 이러한 가상화를 수행할 경우 프로세스를 격리하고 이러한 프로세스가 액세스할 수 있는 CPU, 메모리, 디스크의 양을 제어하기 위해 OS 커널의 특성(예: Linux 네임스페이스 및 cgroup, Windows 사일로 및 작업 오브젝트)을 활용할 수 있다. 

컨테이너는 작고 빠르며 휴대 용이하다. VM 가상 머신과는 달리 컨테이너는 모든 인스턴스에 게스트 OS를 포함할 필요가 없기 때문이다. 그 대신 호스트 OS의 기능과 리소스를 활용한다. 컨테이너는 수십 년 전에 처음 등장했다. 당시는 FreeBSD Jail, AIX Workload Partition 등과 같은 버전이었지만, 현재 개발자들 대부분은 Docker가 첫선을 보인 2013년에 현대식 컨테이너의 시대가 시작되었다고 말한다.

[컨테이너란? IBM](https://www.ibm.com/kr-ko/topics/containers)

<br>

## 1. 루트 디렉토리 비교
두 개의 터미널에서 두 가상 환경 구동 vagrant ssh
- terminal1: Ubuntu 18.04 
- terminal2: Ubuntu 18.04 환경에서 docker 접속 (docker run -it busybox)

### terminal1

```zsh
sudo -Es
ls /

vagrant@ubuntu1804:~$ sudo -Es
root@ubuntu1804:~# ls /
bin   home            lib64       opt   sbin  usr      vmlinuz.old
boot  initrd.img      lost+found  proc  srv   vagrant
dev   initrd.img.old  media       root  sys   var
etc   lib             mnt         run   tmp   vmlinuz
```

### terminal2

```zsh
sudo -Es
cd /tmp
docker run -it busybox
ls /

vagrant@ubuntu1804:~$ sudo -Es
root@ubuntu1804:~# cd /tmp
root@ubuntu1804:/tmp# docker run -it busybox
Unable to find image 'busybox:latest' locally
latest: Pulling from library/busybox
809d8e20e203: Pull complete
Digest: sha256:2376a0c12759aa1214ba83e771ff252c7b1663216b192fbe5e0fb364e952f85c
Status: Downloaded newer image for busybox:latest
/ # ls /
bin    etc    lib    proc   sys    usr
dev    home   lib64  root   tmp    var
```

terminal1, terminal2의 루트 디렉토리가 다르다 (루트 디렉토리가 다르면 다른 시스템이다)

<br>


## 2. 파일 시스템 비교
### terminal1
### terminal2

<br>

## 2. 파일 시스템 비교
df -h: 현재 마운트된 모든 파일 시스템에서 사용 가능한 공간 출력
- -h: human readable


### terminal1
```zsh
df -h 

root@ubuntu1804:~# df -h
Filesystem                    Size  Used Avail Use% Mounted on
udev                          713M     0  713M   0% /dev
tmpfs                         149M  5.5M  144M   4% /run
/dev/mapper/vagrant--vg-root   62G  2.0G   57G   4% /
tmpfs                         745M     0  745M   0% /dev/shm
tmpfs                         5.0M     0  5.0M   0% /run/lock
tmpfs                         745M     0  745M   0% /sys/fs/cgroup
vagrant                       466G  225G  242G  49% /vagrant
tmpfs                         149M     0  149M   0% /run/user/1000
overlay                        62G  2.0G   57G   4% /var/lib/docker/overlay2/241fa521fd32316bb46bc68c7c6979d328d99b8beb533571ba39071324a2a18a/merged
```

### terminal2
```zsh
df -h 

/ # df -h
Filesystem                Size      Used Available Use% Mounted on
overlay                  61.7G      2.0G     56.6G   3% /
tmpfs                    64.0M         0     64.0M   0% /dev
tmpfs                   744.4M         0    744.4M   0% /sys/fs/cgroup
shm                      64.0M         0     64.0M   0% /dev/shm
/dev/mapper/vagrant--vg-root
                         61.7G      2.0G     56.6G   3% /etc/resolv.conf
/dev/mapper/vagrant--vg-root
                         61.7G      2.0G     56.6G   3% /etc/hostname
/dev/mapper/vagrant--vg-root
                         61.7G      2.0G     56.6G   3% /etc/hosts
tmpfs                   744.4M         0    744.4M   0% /proc/acpi
tmpfs                    64.0M         0     64.0M   0% /proc/kcore
tmpfs                    64.0M         0     64.0M   0% /proc/keys
tmpfs                    64.0M         0     64.0M   0% /proc/timer_list
tmpfs                    64.0M         0     64.0M   0% /proc/sched_debug
tmpfs                   744.4M         0    744.4M   0% /proc/scsi
tmpfs                   744.4M         0    744.4M   0% /sys/firmware
```


terminal1에서는 루트 디렉토리가 dev(device)에 있는 파일로 보인다.
```zsh
Filesystem                    Size  Used Avail Use% 
/dev/mapper/vagrant--vg-root   62G  2.0G   57G   4% /
```

terminal2(docker)에서는 루트 디렉토리가 overlay로 보인다. 
```zsh
Filesystem                Size      Used Available Use% Mounted on
overlay                  61.7G      2.0G     56.6G   3% /
```

기존 환경과 docker 컨테이너 환경에서 루트 디렉토리와 루트 디렉토리에 마운트되어있는 루트 파일 시스템이 다른 것을 확인해보았다

<br>

## 3. 프로세스 비교
ps aux: 사용자가 시스템에서 실행 중인 프로세스의 현재 상태를 이해하는 데 필요한 대부분의 정보를 표시

https://www.linode.com/docs/guides/use-the-ps-aux-command-in-linux/#the-aux-shortcut


### terminal1
```zsh
ps aux

root@ubuntu1804:~# ps aux
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.1  0.5  77464  8500 ?        Ss   10:33   0:01 /sbin/init
root         2  0.0  0.0      0     0 ?        S    10:33   0:00 [kthreadd]
root         4  0.0  0.0      0     0 ?        I<   10:33   0:00 [kworker/0:0H]
root         6  0.0  0.0      0     0 ?        I<   10:33   0:00 [mm_percpu_wq]
root         7  0.0  0.0      0     0 ?        S    10:33   0:00 [ksoftirqd/0]
root         8  0.0  0.0      0     0 ?        R    10:33   0:00 [rcu_sched]
root         9  0.0  0.0      0     0 ?        I    10:33   0:00 [rcu_bh]
root        10  0.0  0.0      0     0 ?        S    10:33   0:00 [migration/0]
root        11  0.0  0.0      0     0 ?        S    10:33   0:00 [watchdog/0]
root        12  0.0  0.0      0     0 ?        S    10:33   0:00 [cpuhp/0]
root        13  0.0  0.0      0     0 ?        S    10:33   0:00 [cpuhp/1]
...
```

### terminal2
```zsh
ps aux

/ # ps aux
PID   USER     TIME  COMMAND
    1 root      0:00 sh
    8 root      0:00 ps aux
```


terminal1에는 현재 실행 중인 프로세스가 많다. 그에 반면 terminal2는 구동중인 프로세스가 단 2개이다. PID1을 비교해보면 다른 명령어를 통해 실행된 프로세스임을 확인할 수 있다. 
```zsh
terminal1 
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.1  0.5  77464  8500 ?        Ss   10:33   0:01 /sbin/init

terminal2
PID   USER     TIME  COMMAND
    1 root      0:00 sh

```

<br>

## 4. 네트워크 비교
ip link : 네트워크 레이어에 있는 디바이스 설정

https://man7.org/linux/man-pages/man8/ip-link.8.html

### terminal1
```zsh
root@ubuntu1804:~# ip link
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN mode DEFAULT group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP mode DEFAULT group default qlen 1000
    link/ether 08:00:27:4b:a1:76 brd ff:ff:ff:ff:ff:ff
3: eth1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP mode DEFAULT group default qlen 1000
    link/ether 08:00:27:a4:c2:79 brd ff:ff:ff:ff:ff:ff
4: docker0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP mode DEFAULT group default
    link/ether 02:42:d7:cc:8a:7c brd ff:ff:ff:ff:ff:ff
6: veth73856ef@if5: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue master docker0 state UP mode DEFAULT group default
    link/ether 5a:3d:f2:8a:c6:3b brd ff:ff:ff:ff:ff:ff link-netnsid 0
```

### terminal2
```zsh
ip link

/ # ip link
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
5: eth0@if6: <BROADCAST,MULTICAST,UP,LOWER_UP,M-DOWN> mtu 1500 qdisc noqueue
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff
```

terminal1에서는 loopback, ethernet0, docker 등 여러 개의 장치가 보이는 반면에 terminal2에서는 loopback, ethernet0 2개만 표시된다.

<br>

## 4. 호스트네임, uid, gid 비교
### terminal1
```zsh
root@ubuntu1804:~# hostname
ubuntu1804
root@ubuntu1804:~# id
uid=0(root) gid=0(root) groups=0(root)
```

### terminal2
```zsh
/ # hostname
8f8d2b8f3ddb
/ # id
uid=0(root) gid=0(root) groups=0(root),10(wheel)
```

hostname은 다르나 id에 표시된 root는 같아보인다. terminal1과 terminal2의 root는 같은 root일까? 다르다. 이는 추후 다뤄볼 pivot root를 통해 왜 다른지 확인할 수 있다. 

<br>

# refs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/