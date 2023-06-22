# Execute Vagrant Ubuntu 18.04 with installed Docker 
```
# virtualbox install
brew install virtualbox --cask
# vagrant install
brew install vagrant --cask

# VM 중지와 재개
$ vagrant suspend
$ vagrant resume

# VM 종료와 기동
$ vagrant halt
$ vagrant up

# VM 재기동
$ vagrant reload

# VM 재설정/기동
$ vagrant reload --provision

# VM 상태 확인 (VM 목록별 상태 출력)
$ vagrant status

# VM 터미널 접속
$ vagrant ssh <VM이름>
```


---
refs 
- https://github.com/sam0kim/container-internal
- https://youtu.be/mSD88FuST80
