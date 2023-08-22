#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <sys/select.h>

#define BUF_SIZE 100
void error_handling(char *buf);

int main(int argc, char *argv[])
{
    // 파일 디스크립터를 위한 변수
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_adr, clnt_adr;
    struct timeval timeout;

    // 파일 상태 테이블 선언
    fd_set reads, cpy_reads;

    socklen_t adr_sz;
    int fd_max, str_len, fd_num, i;
    char buf[BUF_SIZE];
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

    FD_ZERO(&reads);           // fd_set 테이블을 초기화한다.
    FD_SET(serv_sock, &reads); // 서버 소켓(리스닝 소켓)의 이벤트 검사를 위해 fd_set 테이블에 추가한다.
    fd_max = serv_sock;

    while (1)
    {
        cpy_reads = reads;
        timeout.tv_sec = 5;
        timeout.tv_usec = 5000;

        // 4. select 함수를 호출해서 이벤트가 발생한 파일 디스크립터를 찾는다.
        // result
        //  -1: 오류 발생
        //  0: 타임 아웃
        //  1 이상: 등록된 파일 디스크립터에 해당 이벤트가 발생하면 이벤트가 발생한 파일 디스크립터의 수를 반환한다.
        if ((fd_num = select(fd_max + 1, &cpy_reads, 0, 0, &timeout)) == -1)
            break;

        if (fd_num == 0)
            continue;

        for (i = 0; i < fd_max + 1; i++)
        {
            if (FD_ISSET(i, &cpy_reads))
            { // fd_set 테이블을 검사한다.
                // 서버 소켓(리스닝 소켓)에 이벤트(연결 요청) 발생
                if (i == serv_sock)
                {
                    adr_sz = sizeof(clnt_adr);
                    clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_adr, &adr_sz);
                    FD_SET(clnt_sock, &reads); // fd_set 테이블에 클라이언트 소켓 디스크립터를 추가한다.
                    if (fd_max < clnt_sock)
                        fd_max = clnt_sock;
                    printf("connected client: %d \n", clnt_sock);
                }
                // 클라이언트와 연결된 소켓에 이벤트 발생
                else
                {
                    str_len = read(i, buf, BUF_SIZE);
                    if (str_len == 0)
                    {                      // close request!
                        FD_CLR(i, &reads); // fd_set 테이블에서 파일 디스크립터를 삭제한다.
                        close(i);
                        printf("closed client: %d \n", i);
                    }
                    else
                    {
                        write(i, buf, str_len); // 가독성을 위해 write에 대한 블로킹 여부 확인 처리는 생략한다.
                    }
                }
            }
        }
    }

    close(serv_sock);
    return 0;
}

void error_handling(char *buf)
{
    fputs(buf, stderr);
    fputc('\n', stderr);
    exit(1);
}