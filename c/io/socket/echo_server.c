#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUF_SIZE 1024
void error_handling(char *message);

int main(int argc, char *argv[])
{
    // 파일 디스크립터를 위한 변수
    int serv_sock, clnt_sock;
    char message[BUF_SIZE];
    int str_len, i;

    struct sockaddr_in serv_adr;
    struct sockaddr_in clnt_adr;
    socklen_t clnt_adr_sz;

    if (argc != 2)
    {
        printf("Usage : %s <port>\n", argv[0]);
        exit(1);
    }

    // 1. 소켓 하나를 생성한다.
    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    if (serv_sock == -1)
        error_handling("socket() error");

    memset(&serv_adr, 0, sizeof(serv_adr));
    serv_adr.sin_family = AF_INET;                // IPv4 인터넷 프로토콜을 사용한다.
    serv_adr.sin_addr.s_addr = htonl(INADDR_ANY); // 서버의 IP 주소를 자동으로 찾아서 대입한다.
    serv_adr.sin_port = htons(atoi(argv[1]));     // 1번 인자로 전달된 포트 번호를 사용한다.

    // 2. 소켓에 IP와 포트 번호를 할당한다.
    if (bind(serv_sock, (struct sockaddr *)&serv_adr, sizeof(serv_adr)) == -1)
        error_handling("bind() error");

    // 3. 서버 소켓(리스닝 소켓)을 통해 클라이언트의 접속 요청을 대기한다.
    //    5개의 수신 대기열(큐)을 생성한다.
    if (listen(serv_sock, 5) == -1)
        error_handling("listen() error");

    clnt_adr_sz = sizeof(clnt_adr);

    for (i = 0; i < 5; i++)
    {
        // 4. 클라이언트 접속 요청을 수락한다(클라이언트와 연결된 새로운 소켓이 생성된다).
        clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_adr, &clnt_adr_sz);
        if (clnt_sock == -1)
            error_handling("accept() error");
        else
            printf("Connected client %d \n", i + 1);

        // 5. 클라이언트와 연결된 소켓을 통해 데이터를 송수신한다.
        while ((str_len = read(clnt_sock, message, BUF_SIZE)) != 0)
            write(clnt_sock, message, str_len);

        close(clnt_sock);
    }

    close(serv_sock);
    return 0;
}

void error_handling(char *message)
{
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}