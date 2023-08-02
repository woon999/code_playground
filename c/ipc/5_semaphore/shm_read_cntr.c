#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>

#define SHM_KEY 0x12345
struct shmseg
{
    int cntr;
    int write_complete;
    int read_complete;
};

int main(int argc, char *argv[])
{
    int shmid, numtimes;
    struct shmseg *shmp;
    int total_count;
    int cntr;
    int sleep_time;
    if (argc != 2)
        total_count = 10000;

    else
    {
        total_count = atoi(argv[1]);
        if (total_count < 10000)
            total_count = 10000;
    }

    // 공유 메모리 get
    shmid = shmget(SHM_KEY, sizeof(struct shmseg), 0644 | IPC_CREAT);

    if (shmid == -1)
    {
        perror("Shared memory");
        return 1;
    }

    // 공유 메모리 attach
    shmp = shmat(shmid, NULL, 0);

    if (shmp == (void *)-1)
    {
        perror("Shared memory attach");
        return 1;
    }

    // 쓰기 완료가 될 때까지 대기
    while (shmp->write_complete != 1)
    {
        if (shmp->cntr == -1)
        {
            perror("read");
            return 1;
        }
        sleep(3);
    }

    printf("Reading Process: Shared Memory: Counter is %d\n", shmp->cntr);
    printf("Reading Process: Reading Done, Detaching Shared Memory\n");
    shmp->read_complete = 1;

    if (shmdt(shmp) == -1)
    {
        perror("shmdt");
        return 1;
    }
    printf("Reading Process: Complete\n");
    return 0;
}