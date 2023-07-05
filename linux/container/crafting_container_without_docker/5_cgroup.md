# 5. 컨테이너 만들기 - Cgroups
# Cgroup
Cgroup은 구글에서 개발하여 2008년에 Linux에 컨트리뷰트하였다. 컨테이너 별로 자원을 분배하고 limit 내에서 운용한다. 
- 하나 또는 복수의 장치를 묶어서 그룹
- 프로세스가 사용하는 리소스 통제
	- 그룹 A CPU 30%, 그룹 B CPU 25%...

Linux는 모든 게 파일이다. Cgroup도 자원 할당과 제어를 파일시스템으로 관리된다. Cgroup 네임스페이스로 격리할 수 있다.

해당 디렉토리에 들어가면 cpu 설정 및 관리하는 단위가 보인다
```zsh
$ tree -L 1 /sys/fs/cgroup/cpu

/sys/fs/cgroup/cpu
├── cgroup.clone_children
├── cgroup.procs
├── cgroup.sane_behavior
├── cpuacct.stat
├── cpuacct.usage
├── cpuacct.usage_all
├── cpuacct.usage_percpu
├── cpuacct.usage_percpu_sys
├── cpuacct.usage_percpu_user
├── cpuacct.usage_sys
├── cpuacct.usage_user
├── cpu.cfs_period_us
├── cpu.cfs_quota_us
├── cpu.shares
├── cpu.stat
├── docker
├── notify_on_release
├── release_agent
├── system.slice
├── tasks
└── user.slice

3 directories, 18 files
```

<br>

# 1. stress 실행 및 CPU 사용량 확인
Cgroup을 다루기 위해서 root 계정으로 접속하고 아래 툴들을 설치한다
```
sudo -Es;
apt install -y cgroup-tools;
apt install -y stress;
```

위에서 설치한 툴들을 실행해보자

### terminal (host1)
```
stress -c 1
```

### terminal (host2)
```
top
```

stress를 실행하고 모니터링해보면 자원 제한을 안주었기 때문에 CPU가 100%까지 차는 것을 볼 수 있다

<br>

# 2. mycgroup 생성
cpu밑에 mycgroup을 생성해보자
```
cgcreate -a root -g cpu:mycgroup

tree /sys/fs/cgroup/cpu/mycgroup
```

파일시스템으로 확인해보면 디렉토리만 생성했을 뿐인데 커널이 mycgroup이 관리할 정보들을 생성해주었다
```
root@ubuntu1804:~# tree /sys/fs/cgroup/cpu/mycgroup
/sys/fs/cgroup/cpu/mycgroup
├── cgroup.clone_children
├── cgroup.procs
├── cpuacct.stat
├── cpuacct.usage
├── cpuacct.usage_all
├── cpuacct.usage_percpu
├── cpuacct.usage_percpu_sys
├── cpuacct.usage_percpu_user
├── cpuacct.usage_sys
├── cpuacct.usage_user
├── cpu.cfs_period_us
├── cpu.cfs_quota_us
├── cpu.shares
├── cpu.stat
├── notify_on_release
└── tasks

0 directories, 16 files
```

<br>

# 3. mycgroup CPU 사용률 설정
cpu 사용률 설정은 cpu.cfs_period_us, cpu.cfs_quota_us 로 한다
- cpu 사용률 계산
```
cpu.cfs_quota_us / cpu.cfs_period_us * 100
```

다음과 같이 설정하면 CPUT 사용률이 30%로 제한된다 (cpu.cfs_period_us의 디폴트가 100000 이다)
```zsh
cgset -r cpu.cfs_quota_us=30000 mycgroup;
```

해당 cgroup을 이용해서 다시 stress 테스트를 진행해보자 
### terminal (host1)
```
cgexec -g cpu:mycgroup stress -c 1
```

### terminal (host2)
```
top
```

아까와 똑같이 코어 1개 할당해서 stress를 실행하였는데 모니터링 결과 CPU가 최대 30%까지만 차는 것을 볼 수 있다. cgroup으로 설정을 해주어서 30%가 넘지않게 쓰로틀링이 걸리는 것이다.

<br>


여기까지 Cgroup에 대해서 알아보았다. 정리하면 다음과 같다.
- Cgroup 파일시스템으로 리소스 관리
- 제어그룹(mycgroup) 생성
- 제어그룹(mycgroup) 리소스 설정
- 제어그룹(mycgroup) 프로세스 할당


지금까지(1~5) 컨테이너의 역사에 대해서 다 훑어보았다. 
- chroot: 1979년에 나온 chroot
- mount: 컨테이너 개발이 본격적으로 이루어지면서 pivot_root하기 위해서 mount 네임스페이스가 2002년 개발
- uts,ipc: 파일시스템만으로 컨테이너를 운용하기 위해 더 완벽한 격리 환경을 위해 uts, ipc 네임스페이스 추가 2006년 개발
- pid, cgroups: 이도 마찬가지로 격리, 자원 제어를 위해 2008년 개발 
- net: 호스트로부터 독립적인 네트워크를 사용할 수 있는 네임스페이스 2009년 개발. 가상 네트워크 장치 사용
- user: 루트 권한 문제를 해결하는 user 네임스페이스 개발 
- 2013년 Docker 등장 
- 2015년 Kubernetes 등장 

이제 마지막으로 지금까지 학습한 내용들을 바탕으로 docker없이 컨테이너를 만들어보자


# refs
- https://youtu.be/lVtgqmjv4BQ
- https://netpple.github.io/docs/make-container-without-docker/