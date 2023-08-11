# memory map
- mmap() System call은 호출 프로세스의 가상 주소 공간에서 파일 또는 장치를 메모리에 매핑하는 매핑을 제공한다

장점
- 효율적인 파일 엑세스에 사용할 수 있음
- 전통적인 I/O 기능 대신 파일을 메모리에 매핑하고 메모리 I/O 기능을 사용할 수 있음. 대용량 파일 조작에 유용함
- 여러 프로세스가 동일한 파일을 메모리에 매핑하고 매핑된 메모리 공간에서 읽고 쓰는 방식으로 통신할 수 있음

단점 
- 메모리 매핑을 잘못 사용하면 메모리 누수, 시스템 리소스 과다 사용, 극단적인 경우 시스템 충돌과 같은 문제가 발생할 수 있음

## 파일 매핑 또는 파일 지원 매핑 
이 매핑은 프로세스의 가상 메모리 영역을 파일에 매핑한다. 즉, 해당 메모리 영역을 읽거나 쓰면 파일이 읽히거나 쓰여집니다. 이것이 기본 매핑 유형이다.

## 익명 매핑 
이 매핑은 파일에 의해 백업되지 않은 프로세스의 가상 메모리 영역을 매핑한다. 내용은 0으로 초기화된다. 이 매핑은 동적 메모리 할당(malloc())과 유사하며 특정 할당을 위한 일부 malloc() 구현에서 사용된다.

## 메모리 매핑 공유 방식 
한 프로세스 매핑의 메모리는 다른 프로세스의 매핑과 공유될 수 있다. 
1. 두 프로세스가 파일의 동일한 영역을 매핑하면 물리적 메모리의 동일한 페이지를 공유한다
2. 자식 프로세스가 생성되면 부모의 매핑을 상속받으며 이러한 매핑은 부모의 매핑과 동일한 물리적 메모리 페이지를 참조한다. 자식 프로세스의 데이터가 변경되면 자식 프로세스에 대해 다른 페이지가 생성된다.

## 두 개 이상 프로세스가 동일한 페이지 공유하는 경우 
각 프로세스는 매핑 유형에 따라 다른 프로세스가 변경한 페이지 내용을 볼 수 있다. 매핑 유형은 비공개 또는 공유일 수 있다.
- 비공개 매핑(MAP_PRIVATE): 이 매핑의 콘텐츠에 대한 수정 사항은 다른 프로세스에 표시되지 않으며 매핑이 기본 파일로 전달되지 않는다
- 공유 매핑(MAP_SHARED): 이 매핑의 콘텐츠에 대한 수정 사항이 다른 프로세스에 표시되고 매핑이 기본 파일로 전달된다

# 명령어
## mmap()
매핑이 성공하면 매핑의 시작 주소를 반환하고, 오류가 발생하면 MAP_FAILED를 반환한다.
```c
#include <sys/mman.h>

void *mmap(void *addr, size_t length, int prot, int flags, int fd, off_t offset);
```
- addr: 가상 주소. 사용자가 지정하거나 커널에서 생성할 수 있다(addr을 NULL로 전달할 때)
- length: 표시된 필드 길이는 매핑 크기를 바이트 단위로 지정해야 한다. 
- prot: 각각 액세스, 읽기, 쓰기 또는 실행할 수 없는 영역에 대한 PROT_NONE, PROT_READ, PROT_WRITE, PROT_EXEC와 같은 메모리 보호 값을 나타낸다. 
    - 이 값은 단일(PROT_NONE)이거나 세 개의 플래그 중 하나(last 3)와 함께 ORd될 수 있다. 
    - 필드 플래그는 매핑 유형 또는 MAP_PRIVATE 또는 MAP_SHARED를 나타낸다. 
    - 'fd' 필드는 매핑할 파일을 식별하는 파일 설명자를 나타내고 'offset' 필드는 파일의 시작점을 의미하며, 전체 파일을 매핑해야 하는 경우 오프셋은 0이어야 한다.

## munmap()
성공 시 0을 반환하고 오류 시 -1을 반환한다.
```c
#include <sys/mman.h>

int munmap(void *addr, size_t length);
```
- 시스템 호출 munmap은 이미 메모리 매핑된 영역의 매핑 해제를 수행합니다. 
- addr: 매핑의 시작 주소를 나타내고 length는 매핑을 해제할 매핑의 크기(바이트)를 나타냅니다. 
- 일반적으로 매핑 및 매핑 해제는 매핑된 전체 영역에 대해 이루어진다. 매핑과 언매핑이 달라야 하는 경우 크기를 줄이거나 두 부분으로 잘라야 한다. 주소에 매핑이 없는 경우 이 호출은 아무런 효과가 없으며 호출은 0(성공)을 반환한다.


<br>

# memory map 
1. mmap() 시스템 호출을 사용하여 파일 내용을 메모리 매핑
2. 비용이 많이 드는 read() 시스템 호출을 읽지 않으므로 배열 표기법(포인터 표기법으로도 액세스 가능)을 사용하여 파일 콘텐츠에 액세스 
    - 메모리 매핑을 사용하면 사용자 공간, 커널 공간 버퍼 및 버퍼 캐시 간에 다중 복사를 피할 수 있음
3. 매핑된 메모리 영역의 매핑 해제(munmap()), 파일 닫기 및 파일 제거 등 정리 작업을 수행한다
```
> ./mmap
Enter -1 to quit or enter a number between 0 and 61: 3
Received char at 3 is D
Enter -1 to quit or enter a number between 0 and 61: 28
Received char at 28 is 2
Enter -1 to quit or enter a number between 0 and 61: 38
Received char at 38 is c
Enter -1 to quit or enter a number between 0 and 61: 59
Received char at 59 is x
Enter -1 to quit or enter a number between 0 and 61: 65
Received invalid index 65
Enter -1 to quit or enter a number between 0 and 61: -99
Received invalid index -99
Enter -1 to quit or enter a number between 0 and 61: -1
```