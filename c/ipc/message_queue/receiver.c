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

    // 받아오는 쪽의 msqid얻어오고
    if ((msqid = msgget(key, IPC_CREAT | 0666)) == -1)
    {
        printf("msgget failed\n");
        exit(0);
    }
    // 메시지를 받는다.
    if (msgrcv(msqid, &msg, sizeof(struct real_data), 0, 0) == -1)
    {
        printf("msgrcv failed\n");
        exit(0);
    }

    printf("name : %s, age :%d\n", msg.data.name, msg.data.age);

    // 이후 메시지 큐를 지운다.
    if (msgctl(msqid, IPC_RMID, NULL) == -1)
    {
        printf("msgctl failed\n");
        exit(0);
    }
}