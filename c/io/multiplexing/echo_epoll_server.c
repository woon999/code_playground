#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <sys/epoll.h>

#define BUF_SIZE 100
#define MAX_EVENTS 50 // Maximum number of events to be returned from a single epoll_wait call

void error_handling(char *buf);

int main(int argc, char *argv[])
{
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_adr, clnt_adr;
    socklen_t adr_sz;
    int str_len, i;
    char buf[BUF_SIZE];

    struct epoll_event ev, *events;
    int epfd, event_cnt;

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

    // 4. epoll_create 함수를 호출해서 epoll 파일 디스크립터를 생성한다.
    epfd = epoll_create1(0); // Create epoll file descriptor
    if (epfd == -1)
        error_handling("epoll_create1() error");

    // 5. epoll_event 구조체 변수를 생성하고, 관심 이벤트를 등록한다.
    events = malloc(sizeof(struct epoll_event) * MAX_EVENTS);
    if (!events)
        error_handling("Failed to allocate event buffer");

    // 6. epoll_ctl 함수를 호출해서 서버 소켓(리스닝 소켓)을 epoll 파일 디스크립터에 등록한다.
    ev.events = EPOLLIN;
    ev.data.fd = serv_sock;
    if (epoll_ctl(epfd, EPOLL_CTL_ADD, serv_sock, &ev) == -1)
        error_handling("epoll_ctl() error");

    while (1)
    {
        // 7. epoll_wait 함수를 호출해서 이벤트가 발생한 파일 디스크립터를 찾는다.
        event_cnt = epoll_wait(epfd, events, MAX_EVENTS, -1); // Infinite timeout
        if (event_cnt == -1)
        {
            perror("epoll_wait()");
            break;
        }

        for (i = 0; i < event_cnt; i++)
        {
            if (events[i].data.fd == serv_sock) // New client connection ( 이벤트가 발생한 파일 디스크립터가 서버 소켓(리스닝 소켓)인지 확인)
            {
                adr_sz = sizeof(clnt_adr);
                clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_adr, &adr_sz);
                ev.events = EPOLLIN;
                ev.data.fd = clnt_sock;
                if (epoll_ctl(epfd, EPOLL_CTL_ADD, clnt_sock, &ev) == -1)
                    error_handling("epoll_ctl() error");
                printf("connected client: %d \n", clnt_sock);
            }
            else // Read data from client (클라이언트와 연결된 소켓에 이벤트 발생)
            {
                str_len = read(events[i].data.fd, buf, BUF_SIZE);
                if (str_len == 0) // Close request
                {
                    epoll_ctl(epfd, EPOLL_CTL_DEL, events[i].data.fd, NULL);
                    close(events[i].data.fd);
                    printf("closed client: %d \n", events[i].data.fd);
                }
                else
                {
                    write(events[i].data.fd, buf, str_len);
                }
            }
        }
    }

    close(serv_sock);
    close(epfd);
    free(events);
    return 0;
}

void error_handling(char *buf)
{
    fputs(buf, stderr);
    fputc('\n', stderr);
    exit(1);
}
