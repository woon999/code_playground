#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <stdlib.h>
#include <string.h>
#include "msg_data.h"

int main()
{
    key_t key = 12345;
    int msqid;
    struct message msg;

    // msgget(): 새로운 메시지 큐를 생성하거나 기존의 메시지 큐에 접근하여 핸들 또는 식별자를 가져온다 & 받아오는 쪽의 msqid 얻는다
    if ((msqid = msgget(key, IPC_CREAT | 0666)) == -1)
    {
        printf("msgget failed\n");
        exit(0);
    }

    // msgrcv(): 메시지 큐에서 메시지를 읽어온다.
    if (msgrcv(msqid, &msg, sizeof(struct real_data), 0, 0) == -1)
    {
        printf("msgrcv failed\n");
        exit(0);
    }

    printf("name : %s, age :%d\n", msg.data.name, msg.data.age);

    // msgctl(): 메시지 큐에 대한 제어 작업을 수행한다. (IPC_RMID: 메시지 큐를 제거한다.)
    if (msgctl(msqid, IPC_RMID, NULL) == -1)
    {
        printf("msgctl failed\n");
        exit(0);
    }
}