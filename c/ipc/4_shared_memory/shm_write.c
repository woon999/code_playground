#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define BUF_SIZE 1024
#define SHM_KEY 0x1234

struct shmseg
{
    int cnt;
    int complete;
    char buf[BUF_SIZE];
};
int fill_buffer(char *bufptr, int size);

int main(int argc, char *argv[])
{
    int shmid, numtimes;
    struct shmseg *shmp;
    char *bufptr;
    int spaceavailable;

    // shmget(): 공유 메모리 세그먼트를 생성하거나 할당한다
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

    /* Transfer blocks of data from buffer to shared memory */
    bufptr = shmp->buf;
    spaceavailable = BUF_SIZE;
    for (numtimes = 0; numtimes < 5; numtimes++)
    {
        shmp->cnt = fill_buffer(bufptr, spaceavailable);
        shmp->complete = 0;
        printf("Writing Process: Shared Memory Write: Wrote %d bytes\n", shmp->cnt);
        bufptr = shmp->buf;
        spaceavailable = BUF_SIZE;
        sleep(3);
    }
    printf("Writing Process: Wrote %d times\n", numtimes);
    shmp->complete = 1;

    // shmdt(): 호출 프로세스의 주소 공간에서 공유 메모리 세그먼트를 분리하는 시스템 V 공유 메모리 세그먼트에 대한 공유 메모리 연산을 수행한다
    // - 성공 시 0을 반환하고 실패 시 -1을 반환한다
    if (shmdt(shmp) == -1)
    {
        perror("shmdt");
        return 1;
    }

    // shmctl(): 공유 메모리 세그먼트에 대한 제어 작업을 수행한다
    // - IPC_RMID: 공유 메모리 세그먼트를 제거한다
    if (shmctl(shmid, IPC_RMID, 0) == -1)
    {
        perror("shmctl");
        return 1;
    }
    printf("Writing Process: Complete\n");
    return 0;
}

int fill_buffer(char *bufptr, int size)
{
    static char ch = 'A';
    int filled_count;

    // printf("size is %d\n", size);
    memset(bufptr, ch, size - 1);
    bufptr[size - 1] = '\0';
    if (ch > 122)
        ch = 65;
    if ((ch >= 65) && (ch <= 122))
    {
        if ((ch >= 91) && (ch <= 96))
        {
            ch = 65;
        }
    }
    filled_count = strlen(bufptr);

    // printf("buffer count is: %d\n", filled_count);
    // printf("buffer filled is:%s\n", bufptr);
    ch++;
    return filled_count;
}