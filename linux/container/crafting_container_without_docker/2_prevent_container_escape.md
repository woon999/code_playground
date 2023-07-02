# 2. 탈옥 막자
[1. 프로세스 격리](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/1_process_isolation.md)에서 chroot를 통해 root를 변경해보았다. 그리고 해당 방식으로는 완벽한 컨테이너 격리 환경을 구축할 수 없다는 것을 직접 탈옥해보면서 알아보았다. 이번에는 탈옥을 못하게 막아보자. 

<br>

# pivot_root
탈옥을 막기 위해 루트 파일시스템을 pivot_root를 사용한다. 해당 명령어는 컨테이너를 위해 개발된 명령어는 아니다.  시스템 부팅시에 시스템에 붙어있는 디스크, 램카드 등을 올려야하는데 부팅 파일시스템을 사용한다.  부팅 파일시스템을 사용해서 장치가 모두 올라오면 실제 운영체제가 사용하는 루트파일시스템으로 전환해주어야 한다. 이때 사용하는 명령어가 'pivot_root'이다.

## 루트파일시스템
- 최상위 파일시스템
- 루트디렉토리를 포함
- 하위의 모든 파일시스템들이 마운트

루트 파일시스템을 피봇하면 Host에 영향이 간다. 그래서 프로세스의 환경을 격리하는 'Namespace'(2002)를 개발하게 되었다. 네임스페이스를 통해 격리를 해서 하고 싶었던 것은 Host에게 영향을 주지않고 pivot_root가 가능하게 만드려고 했다. 그래서 이때 고민했던 것이 '마운트'이다. 


## 마운트 Mount
파일시스템을 루트파일시스템의 하위 디렉토리로 부착하는 system call
- 마운트 포인트: 부착 지점, 접근 지점
- 예) USB, CDROM 마운트

부착되는 지점을 마운트 포인트라고 한다. (이전 실습에서 ps 명령어를 실행할 때 proc 마운트 포인트가 필요했음)

## 마운트 namespace
그래서 pivot_root를 하기 위해서 최초의 네임스페이스로 '마운트 네임스페이스'가 개발이 된다. 마운트 네임스페이스는 '마운트 포인트'를 격리한다. 파일 시스템이 루트 파일시스템에 부착되는 위치에 대한 정보를 격리하는 것이다.
- 부모 프로세스가 속해있는 host 상에서 M 디렉토리가 그대로 보이고 부착된 파일시스템이 보이지 않는다
- 이렇게 되면 호스트에 영향이 없으므로 pivot_root가 가능해진다 

<br>

# 1. unshare 마운트 격리하기
unshare 명령어 하나로 격리를 시켜줄 수 있다. mount 네임스페이스를 unshare하면 부모 프로세스의 mount 네임스페이스를 복사해서 자식 네임스페이스를 만든다. 
```zsh
unshare --mount /bin/sh
```

다음 명령어로 비교해보면 완전히 똑같은 것을 확인할 수 있다.
``` zsh
df -h

Filesystem                    Size  Used Avail Use% Mounted on
/dev/mapper/vagrant--vg-root   62G  2.4G   57G   5% /
udev                          713M     0  713M   0% /dev
tmpfs                         745M     0  745M   0% /dev/shm
tmpfs                         149M  5.5M  144M   4% /run
tmpfs                         5.0M     0  5.0M   0% /run/lock
tmpfs                         149M     0  149M   0% /run/user/1000
tmpfs                         745M     0  745M   0% /sys/fs/cgroup
vagrant                       466G  228G  238G  49% /vagrant
overlay                        62G  2.4G   57G   5% /var/lib/docker/overlay2/db950aec60bf921106d4558e8483b129641a08dad34fd119b5a2263a021d9af2/merged
overlay                        62G  2.4G   57G   5% /var/lib/docker/overlay2/7ff7c21eb124bcf8a9df2cf69fac230834b6b8793d9e2bfce4cbe9e4e1d316e4/merged
```

<br>

# 2. new_root 마운트하기
mount 네임스페이스로 격리한 환경에서 new_root에 마운트를 해보자
- -t: 타입 (tmpfs, 임시 파일 시스템)
- none: memory none
- mount point: new_root
```
mkdir new_root
mount -t tmpfs none new_root
```

호스트와 비교해보면 호스트에도 new_root가 생성된 것을 확인할 수 있다. 그러나 host에서는 mount 네임스페이스를 unshare했기 때문에 new_root mount 정보는 보이지 않는다.
## terminal1 (container)
``` zsh
# ls
chroot_ls.sh
chroot_ps.sh
escape_chroot.c
myroot
netplan_m9qd_jsa
new_root
nginx-root
systemd-private-f730aa709d194b68883b9fdab4ccc930-systemd-resolved.service-wGxlwB
# mount | grep new_root
none on /tmp/new_root type tmpfs (rw,relatime)
```

## terminal2 (host)
```zsh
root@ubuntu1804:/tmp# ls
chroot_ls.sh
chroot_ps.sh
escape_chroot.c
myroot
netplan_m9qd_jsa
new_root
nginx-root
systemd-private-f730aa709d194b68883b9fdab4ccc930-systemd-resolved.service-wGxlwB
root@ubuntu1804:/tmp# mount | grep new_root
```

<br>

# 3. myroot -> new_root 복사
[1. 프로세스 격리](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/1_process_isolation.md)에서 실습했던 myroot 파일 목록들을 new_root에 복사해보자. 

## terminal1 (container)
```zsh
# cp -r myroot/* new_root
# tree new_root
new_root
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

## terminal2 (host)
```zsh
root@ubuntu1804:/tmp# tree new_root
new_root

0 directories, 0 files
```

mount 네임스페이스를 격리했기 때문에 host 환경에서는 복사된 내용이 보이지 않는다. 

<br>

# 3. pivot_root
그러면 이제 격리된 환경에서 pivot root를 해보자.

`pivot_root <new_root> <put_old>`
- new_root: 마운트 포인트(신규 루트)
- put_old: 디렉토리(기존 루트)
``` zsh
mkdir new_root/put_old;
cd new_root;
pivot_root . put_old;
cd /;
```


## terminal1 (container)
```zsh
# ls /
bin  escape_chroot  lib  lib64	proc  put_old  usr
# ls put_old
bin   home	      lib64	  opt	sbin  usr      vmlinuz.old
boot  initrd.img      lost+found  proc	srv   vagrant
dev   initrd.img.old  media	  root	sys   var
etc   lib	      mnt	  run	tmp   vmlinuz
```

## terminal2 (host)
```zsh
root@ubuntu1804:/tmp# ls /
bin   home            lib64       opt   sbin  usr      vmlinuz.old
boot  initrd.img      lost+found  proc  srv   vagrant
dev   initrd.img.old  media       root  sys   var
etc   lib             mnt         run   tmp   vmlinuz
```

다음과 같이 보면 루트디렉토리가 변경되어서 terminal1 put_old에 기존 루트 파일시스템이 부착된 것을 확인할 수 있다. 그러면 이전에 탈옥을 했던 코드를 다시 실행시켜보자.
```zsh
# ./escape_chroot
# ls /
bin  escape_chroot  lib  lib64	proc  put_old  usr
```

탈옥이 되지 않았다. pivot_root를 시키면 진짜로 루트 파일시스템을 바뀌어버렸기 때문에 chroot를 escape할 수 없다. 지금까지 실습한 내용을 정리해보면 다음과 같다.
1. chroot를 사용하여 패키징, 프로세스 격리 후 실행
2. 그러나 chroot 탈옥 가능
3. 마운트 네임스페이스 격리 + pivot_root를 이용하여 탈옥 방지 


이제 남은 문제는 중복 문제이다. 보안, 관리, 비용 등 다양한 문제가 발생한다. 다음은 이를 해결해보자. 

<br>

# refs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/