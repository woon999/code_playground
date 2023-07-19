#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFF_SIZE 1024

int main()
{
    int pipes_parent[2];
    int pipes_child[2];
    char buff[BUFF_SIZE];
    int counter = 0;
    pid_t pid;

    if (-1 == pipe(pipes_parent))
    {
        perror("파이프 생성 실패");
        exit(1);
    }

    if (-1 == pipe(pipes_child))
    {
        perror("파이프 생성 실패");
        exit(1);
    }

    pid = fork(); // create child process

    switch (pid)
    {
    case -1:
        printf("자식 프로세스 생성 실패\n");
        return -1;
    case 0: // 자식 프로세스입니다.
        while (1)
        {
            sprintf(buff, "-> 자식 메시지: %d", counter++); // buff 에 메시지 저장
            write(pipes_child[1], buff, strlen(buff));      // child pipe 에 buff 저장
            memset(buff, 0, BUFF_SIZE);                     // buff 삭제
            read(pipes_parent[0], buff, BUFF_SIZE);         // parent pipe 에서 buff 읽어오기
            printf("자식 프로세스 %s\n", buff);             // buff 출력
            sleep(1);
        }
    default: // 부모 프로세스입니다.
        while (1)
        {
            sprintf(buff, "-> 부모 메시지: %d", counter--); // buff 에 메시지 저장
            write(pipes_parent[1], buff, strlen(buff));     // parent pipe 에 buff 저장
            memset(buff, 0, BUFF_SIZE);                     // buff 삭제
            read(pipes_child[0], buff, BUFF_SIZE);          // child pipe 에서 buff 읽어오기
            printf("부모 프로세스 %s\n", buff);             // buff 출력
            sleep(1);
        }
    }
}