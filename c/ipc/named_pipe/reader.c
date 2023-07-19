#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <unistd.h>

#define FIFO_FILE "MYFIFO"

int main()
{
    int fd;
    char buf[1024];

    /* open, read, and display the message from the FIFO */
    fd = open(FIFO_FILE, O_RDONLY);
    read(fd, buf, sizeof(buf));
    printf("Received: %s\n", buf);
    close(fd);

    return 0;
}
