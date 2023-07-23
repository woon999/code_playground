#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#define FIFO_FILE "/tmp/fifo_twoway"

void reverse_string(char *);
int main()
{
    int fd;
    char readbuf[80];
    char end[10];
    int to_end;
    int read_bytes;

    /* Create the FIFO if it does not exist */
    mkfifo(FIFO_FILE, S_IFIFO | 0640);
    strcpy(end, "end");
    fd = open(FIFO_FILE, O_RDWR);
    while (1)
    {
        read_bytes = read(fd, readbuf, sizeof(readbuf));
        readbuf[read_bytes] = '\0';
        printf("FIFOSERVER: Received string: \"%s\" and length is %d\n", readbuf, (int)strlen(readbuf));
        to_end = strcmp(readbuf, end);

        if (to_end == 0)
        {
            close(fd);
            break;
        }
        reverse_string(readbuf);
        printf("FIFOSERVER: Sending Reversed String: \"%s\" and length is %d\n", readbuf, (int)strlen(readbuf));
        write(fd, readbuf, strlen(readbuf));
        /*
        sleep - This is to make sure other process reads this, otherwise this
        process would retrieve the message
        */
        sleep(2);
    }
    return 0;
}

void reverse_string(char *str)
{
    int last, limit, first;
    char temp;
    last = strlen(str) - 1;
    limit = last / 2;
    first = 0;

    while (first < last)
    {
        temp = str[first];
        str[first] = str[last];
        str[last] = temp;
        first++;
        last--;
    }
    return;
}