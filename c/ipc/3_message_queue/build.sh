#!/bin/sh

# -e(errexit): 이 옵션은 명령이 오류를 나타내는 0이 아닌 종료 상태를 반환하는 경우 스크립트를 즉시 종료하도록 한다.
# -x(xtrace): 이 옵션은 스크립트에서 명령 추적을 활성화한다. 즉, 각 명령이 실행되기 전에 콘솔에 출력된다.
set -xe 

# -Wall -Wextra: 컴파일 중에 추가 경고를 활성화하는 컴파일러 플래그. 잠재적인 문제를 파악하고 코드 품질을 개선하는 데 도움.

clang -Wall -Wextra -o sender sender.c
clang -Wall -Wextra -o receiver receiver.c
