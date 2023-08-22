#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>

#define BUF_SIZE 30
void error_handling(char *message);
void *handle_clnt(void *arg);

int main(int argc, char *argv[])
{
    // 파일 디스크립터를 위한 변수
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_adr;
    struct sockaddr_in clnt_adr;

    pthread_t t_id;
    socklen_t adr_sz;
    if (argc != 2)
    {
        printf("Usage : %s <port>\n", argv[0]);
        exit(1);
    }

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
        // 4. 메인 스레드는 리스닝 소켓으로 accept 함수를 호출해서 연결 요청을 수락한다.
        clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_adr, &adr_sz);

        if (clnt_sock == -1)
            continue;

        puts("new client connected...");

        // 5. 클라이언트와 연결된 소켓의 파일 디스크립터를 워커 스레드를 생성해 넘겨준다.
        pthread_create(&t_id, NULL, handle_clnt, (void *)&clnt_sock);
        pthread_detach(t_id);
    }

    close(serv_sock);
    return 0;
}

void *handle_clnt(void *arg)
{
    int clnt_sock = *((int *)arg);
    int str_len = 0, i;
    char buf[BUF_SIZE];

    // 6. 워커 스레드는 전달받은 파일 디스크립터를 바탕으로 서비스를 제공한다.
    while ((str_len = read(clnt_sock, buf, BUF_SIZE)) != 0)
        write(clnt_sock, buf, str_len);

    puts("client disconnected...");
    close(clnt_sock);
    return NULL;
}

void error_handling(char *msg)
{
    fputs(msg, stderr);
    fputc('\n', stderr);
    exit(1);
}