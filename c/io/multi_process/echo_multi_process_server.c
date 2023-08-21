#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUF_SIZE 30
void error_handling(char *message);
void read_childproc(int sig);

int main(int argc, char *argv[])
{
    // 파일 디스크립터를 위한 변수
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_adr;
    struct sockaddr_in clnt_adr;

    pid_t pid;
    struct sigaction act; // 시그널 처리를 위한 구조체
    socklen_t adr_sz;     // 주소의 길이를 저장할 변수
    int str_len, state;
    char buf[BUF_SIZE];
    if (argc != 2)
    {
        printf("Usage : %s <port>\n", argv[0]);
        exit(1);
    }

    act.sa_handler = read_childproc; // 시그널 발생 시 호출될 함수
    sigemptyset(&act.sa_mask);       // 시그널 처리 중 블록될 시그널을 모두 제거한다.
    act.sa_flags = 0;
    state = sigaction(SIGCHLD, &act, 0);

    // 1. 소켓 하나를 생성한다.
    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    memset(&serv_adr, 0, sizeof(serv_adr));
    serv_adr.sin_family = AF_INET;
    serv_adr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_adr.sin_port = htons(atoi(argv[1]));

    // 2. 소켓에 IP와 포트 번호를 할당한다.
    if (bind(serv_sock, (struct sockaddr *)&serv_adr, sizeof(serv_adr)) == -1)
        error_handling("bind() error");

    // 3. 생성한 소켓을 서버 소켓(리스닝 소켓)으로 등록한다.
    if (listen(serv_sock, 5) == -1)
        error_handling("listen() error");

    while (1)
    {
        adr_sz = sizeof(clnt_adr);

        // 4. 부모 프로세스는 리스닝 소켓으로 accept 함수를 호출해서 연결 요청을 수락한다.
        clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_adr, &adr_sz);
        if (clnt_sock == -1)
            continue;
        else
            puts("new client connected...");

        // 5. 이때 얻는 소켓의 파일 디스크립터(클라이언트와 연결된 연결 소켓)를 자식 프로세스를 생성해 넘겨준다.
        pid = fork();
        if (pid == -1)
        {
            close(clnt_sock);
            continue;
        }
        if (pid == 0)
        {
            close(serv_sock);

            // 6. 자식 프로세스는 전달받은 파일 디스크립터를 바탕으로 서비스를 제공한다.
            while ((str_len = read(clnt_sock, buf, BUF_SIZE)) != 0)
                write(clnt_sock, buf, str_len);

            close(clnt_sock);
            puts("client disconnected...");
            return 0;
        }
        else
            close(clnt_sock);
    }
    close(serv_sock);
    return 0;
}

void read_childproc(int sig)
{
    pid_t pid;
    int status;
    pid = waitpid(-1, &status, WNOHANG);
    printf("removed proc id: %d \n", pid);
}

void error_handling(char *message)
{
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}