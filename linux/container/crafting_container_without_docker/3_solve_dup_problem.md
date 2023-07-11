# 3. 중복을 해결하자

# 오버레이 파일시스템
OverlayFS는 일종의 유니온 파일 시스템이다. 이를 통해 사용자는 한 파일 시스템을 다른 파일 시스템 위에 오버레이할 수 있다. 변경 사항은 상위 파일 시스템에 기록되지만 하위 파일 시스템은 수정되지 않은 상태로 유지된다
- 여러 이미지 레이어를 하나로 마운트
- Lower 레이어는 ReadOnly
- Upper 레이어는 Writable
- CoW, copy-on-write (원본유지)
- Lower 레이어는 변경 x, Upper 레이어만 변경 o
- https://www.kernel.org/doc/html/latest/filesystems/overlayfs.html

<img width="613" alt="스크린샷 2023-07-03 오후 6 47 20" src="https://github.com/loosie/code_playground/assets/54282927/e97722e2-4f02-4b7b-a3cc-3fa71336964f">

## 이미지 중복 문제 해결
- 이미지 저장소: docker repository
- 컨테이너: 전체 파일 시스템 뷰는 merged view에 합쳐져서 보인다 

<img width="613" alt="스크린샷 2023-07-03 오후 6 52 08" src="https://github.com/loosie/code_playground/assets/54282927/a75f9c29-5268-40cd-a44f-4bc0cd55530f">

<br>


# 0. 오버레이 파일시스템 만들기
[2. 탈옥 막자](https://github.com/loosie/code_playground/blob/master/linux/container/crafting_container_without_docker/2_prevent_container_escape.md) 에서 만든 myroot와 새롭게 tools를 추가해서 upper dir을 올리고 merged view를 만들어 볼 것이다


<br>

# 1. Lower Dir1: myroot 확인
이전에 만든 myroot가 제대로 있는지 우선 확인해보자
```zsh
root@ubuntu1804:/tmp# tree -L 1 myroot
myroot
├── bin
├── escape_chroo
├── lib
├── lib64
├── proc
└── usr
```


<br>

# 2. Lower Dir2: tools 만들기
tools 디렉토리를 생성한다
```zsh
mkdir tools
```

tools에 which, rm 을 복사하자
```zsh
wget https://raw.githubusercontent.com/loosie/code_playground/master/linux/container/scripts/lowerdir2_package.sh
bash lowerdir2_package.sh;
```

tree 명령어로 /bin/rm, /usr/bin/which 모두 제대로 복사되었는지 확인한다 
```zsh
root@ubuntu1804:/tmp# tree tools
tools
├── bin
│   └── rm
├── lib
│   └── x86_64-linux-gnu
│       ├── libc.so.6
│       ├── libdl.so.2
│       ├── libpcre.so.3
│       ├── libpthread.so.0
│       └── libselinux.so.1
├── lib64
│   └── ld-linux-x86-64.so.2
└── usr
    └── bin
        └── which

6 directories, 8 files
```

<br>

# 3. 오버레이 마운트 준비
LowerDir1, LowerDir2는 모두 준비가 되었다. 이제 오버레이 마운트할 경로를 만들어보자
- container: upper 디렉토리
- merge: 통합 뷰(merged view) 디렉토리. 실제로 오버레이 마운트 포인트
- work: upper 디렉토리 업데이트를 보장하기 위해 존재하는 디렉토리
```zsh
mkdir -p rootfs/{container,work,merge}

root@ubuntu1804:/tmp# mkdir -p rootfs/{container,work,merge}
root@ubuntu1804:/tmp# tree rootfs
rootfs
├── container
├── merge
└── work

3 directories, 0 files
```

<br>

# 4. 오버레이 마운트
이제 준비는 다 되었으니 바로 오버레이 마운트를 해보자.
```zsh
mount -t overlay overlay -o lowerdir=tools:myroot,upperdir=rootfs/container,workdir=rootfs/work rootfs/merge
```

제대로 되었는지 확인해보자. merge와 work 디렉토리에 새롭게 파일들이 추가되었다.
```zsh
root@ubuntu1804:/tmp# mount -t overlay overlay -o lowerdir=tools:myroot,upperdir=rootfs/container,workdir=rootfs/work rootfs/merge
root@ubuntu1804:/tmp# cd rootfs
root@ubuntu1804:/tmp/rootfs# tree -L 2 .
.
├── container
├── merge
│   ├── bin
│   ├── escape_chroot
│   ├── lib
│   ├── lib64
│   ├── proc
│   └── usr
└── work
    └── work

9 directories, 1 file
```


제대로 유니온 마운트 되었는지 다음과 같이 비교해보면 rootfs/merge에 myroot, tools 이 두 개의 lower_dir가 합쳐진 것을 확인할 수 있다
myroot 
- bin/{ls,mkdir,mount,ps,sh}
- usr/lib
tools
- bin/rm
- usr/bin/which
rootfs/merge
- bin/{ls,mkdir,mount,ps,sh} +  {rm}
- usr/lib
- usr/bin/which
```zsh
tree -L 2 myroot/{bin,usr}; 
tree -L 2 tools/{bin,usr};
tree -L 2 rootfs/merge/{bin,usr};

root@ubuntu1804:/tmp# tree -L 2 myroot/{bin,usr}
myroot/bin
├── ls
├── mkdir
├── mount
├── ps
└── sh
myroot/usr
└── lib
    └── x86_64-linux-gnu

2 directories, 5 files

root@ubuntu1804:/tmp# tree -L 2 tools/{bin,usr}
tools/bin
└── rm
tools/usr
└── bin
    └── which

1 directory, 2 files

root@ubuntu1804:/tmp# tree -L 2 rootfs/merge/{bin,usr}
rootfs/merge/bin
├── ls
├── mkdir
├── mount
├── ps
├── rm
└── sh
rootfs/merge/usr
├── bin
│   └── which
└── lib
    └── x86_64-linux-gnu

3 directories, 7 files
```

<br>

# 5. CoW, Copy On Write
container 디렉토리는 비어있다. 그런데 merge에 있는 파일을 삭제하고 다시 조회하면 container에 노란색으로 해당 삭제된 파일이 생성되어있는 것을 볼 수 있는데 그 이유는 삭제 마킹을 한 것이다. escape_root는 lower_dir에 있는 파일이 삭제된 것이고 해당 삭제된 정보가 upper_dir 밑에 쓰여진 것이다. 
- 실제 myroot(lower_dir1)에는 파일이 그대로 있다. 변경된 정보를 upper_dir에서만 관리해준다.

<img width="819" alt="스크린샷 2023-07-03 오후 7 38 51" src="https://github.com/loosie/code_playground/assets/54282927/8c89d8f0-7121-4792-a26a-145f4457143a">

```zsh
root@ubuntu1804:/tmp# tree -L 1 myroot
myroot
├── bin
├── escape_chroot
├── lib
├── lib64
├── proc
└── usr
```

여기까지 오버레이 마운트를 통해 중복 문제를 해결해보았다. 이제 다시 umount를 통해 실습 환경을 초기화하자. 
```
umount /tmp/roofs/merge
```

<br>

# 컨테이너 전용 루트파일시스템
여기까지 컨테이너 전용 루트파일시스템을 갖게되는 과정을 살펴보았다. 컨테이너를 사용하기 전에는 프로세스 안에 가상 메모리 공간으로 메모리 참조하는 정보가 host 루트 파일시스템을 참조했엇다. 컨테이너 전용 루트 파일시스템을 갖게되면서 host 루트 파일시스템에 의존하는 것이 아닌 컨테이너 자제 루트파일시스템에서 관련된 바이너리(bin)나 라이브러리(lib)를 사용할 수 있게되었다.
1. 컨테이너 자체 루트파일시스템 
2. 이미지 중복 문제 해결 

<img width="864" alt="스크린샷 2023-07-03 오후 7 50 01" src="https://github.com/loosie/code_playground/assets/54282927/87965690-bf84-421a-be1c-352d42c2c635">


<br> 
<br>

지금까지 탈옥, 중복 문제를 해결해보았다. 그러나 완벽한 격리 환경을 갖추기 위해서는 아직 부족하다. 다음엔 네임스페이스와 cgroups를 통해 남은 문제들을 해결해보자. 
- 탈옥 문제: pivot_root
- 중복 문제: 오버레이 파일스시템
- 격리 안됨, 루트 권한: 네임스페이스
- 자원 보장: cgroups


<br>

# refs
- https://docs.docker.com/storage/storagedriver/
- http://cloudrain21.com/examination-of-docker-containersize-ufs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/
