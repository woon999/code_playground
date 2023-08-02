#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define SHM_KEY 0x12345
struct shmseg
{
    int cntr;
    int write_complete;
    int read_complete;
};
void shared_memory_cntr_increment(int pid, struct shmseg *shmp, int total_count);

int main(int argc, char *argv[])
{
    int shmid;
    struct shmseg *shmp;
    char *bufptr;
    int total_count;
    int sleep_time;
    pid_t pid;
    if (argc != 2)
        total_count = 10000;
    else
    {
        total_count = atoi(argv[1]);
        if (total_count < 10000)
            total_count = 10000;
    }
    printf("Total Count is %d\n", total_count);
    shmid = shmget(SHM_KEY, sizeof(struct shmseg), 0644 | IPC_CREAT);

    if (shmid == -1)
    {
        perror("Shared memory");
        return 1;
    }

    // Attach to the segment to get a pointer to it.
    shmp = shmat(shmid, NULL, 0);
    if (shmp == (void *)-1)
    {
        perror("Shared memory attach");
        return 1;
    }
    shmp->cntr = 0;
    pid = fork();

    /* Parent Process - Writing Once */
    if (pid > 0)
    {
        shared_memory_cntr_increment(pid, shmp, total_count);
    }
    else if (pid == 0)
    {
        shared_memory_cntr_increment(pid, shmp, total_count);
        return 0;
    }
    else
    {
        perror("Fork Failure\n");
        return 1;
    }

    // 읽기 프로세스가 읽을 때까지 기다림
    while (shmp->read_complete != 1)
        sleep(1);

    // 공유 메모리 detach
    if (shmdt(shmp) == -1)
    {
        perror("shmdt");
        return 1;
    }

    // 공유 메모리 삭제
    if (shmctl(shmid, IPC_RMID, 0) == -1)
    {
        perror("shmctl");
        return 1;
    }
    printf("Writing Process: Complete\n");
    return 0;
}

// 공유 메모리에 있는 cntr을 total_count만큼 증가시키는 함수
void shared_memory_cntr_increment(int pid, struct shmseg *shmp, int total_count)
{
    int cntr;
    int numtimes;
    int sleep_time;
    cntr = shmp->cntr;
    shmp->write_complete = 0;
    if (pid == 0)
        printf("SHM_WRITE: CHILD: Now writing\n");
    else if (pid > 0)
        printf("SHM_WRITE: PARENT: Now writing\n");

    // total_count만큼 1씩 증가시킴
    for (numtimes = 0; numtimes < total_count; numtimes++)
    {
        cntr += 1;
        shmp->cntr = cntr;

        // 1000번마다 1초씩 sleep
        sleep_time = cntr % 1000;
        if (sleep_time == 0)
            sleep(1);
    }

    shmp->write_complete = 1;
    if (pid == 0)
        printf("SHM_WRITE: CHILD: Writing Done\n");
    else if (pid > 0)
        printf("SHM_WRITE: PARENT: Writing Done\n");
    return;
}