#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>

#define BUF_SIZE 1024
#define SHM_KEY 0x1234

struct shmseg
{
    int cnt;
    int complete;
    char buf[BUF_SIZE];
};

int main(int argc, char *argv[])
{
    int shmid;
    struct shmseg *shmp;

    // shmget(): 공유 메모리 세그먼트를 생성하거나 할당한다.
    // - 성공 시 유효한 공유 메모리 식별자(공유 메모리의 추가 호출에 사용)를 반환하고 실패 시 -1을 반환한다
    shmid = shmget(SHM_KEY, sizeof(struct shmseg), 0644 | IPC_CREAT);
    if (shmid == -1)
    {
        perror("Shared memory");
        return 1;
    }

    // Attach to the segment to get a pointer to it.
    // shmat(): 공유 메모리 세그먼트에 대한 공유 메모리 작업, 즉 호출 프로세스의 주소 공간에 공유 메모리 세그먼트를 연결하는 작업을 수행한다
    // - 성공 시 연결된 공유 메모리 세그먼트의 주소를 반환하고 실패 시 -1을 반환한다
    shmp = shmat(shmid, NULL, 0);
    if (shmp == (void *)-1)
    {
        perror("Shared memory attach");
        return 1;
    }

    /* Transfer blocks of data from shared memory to stdout*/
    while (shmp->complete != 1)
    {
        printf("segment contains : \n\"%s\"\n", shmp->buf);
        if (shmp->cnt == -1)
        {
            perror("read");
            return 1;
        }
        printf("Reading Process: Shared Memory: Read %d bytes\n", shmp->cnt);
        sleep(3);
    }
    printf("Reading Process: Reading Done, Detaching Shared Memory\n");

    // shmdt(): 호출 프로세스의 주소 공간에서 공유 메모리 세그먼트를 분리하는 시스템 V 공유 메모리 세그먼트에 대한 공유 메모리 연산을 수행한다
    // - 성공 시 0을 반환하고 실패 시 -1을 반환한다
    if (shmdt(shmp) == -1)
    {
        perror("shmdt");
        return 1;
    }
    printf("Reading Process: Complete\n");
    return 0;
}