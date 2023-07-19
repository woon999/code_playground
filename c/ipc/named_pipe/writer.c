#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

#define FIFO_FILE "MYFIFO"

int main()
{
    int fd;
    char str[] = "Hello, world!";

    /* create the FIFO (named pipe) */
    mkfifo(FIFO_FILE, 0666);

    /* write "Hello, world!" to the FIFO */
    fd = open(FIFO_FILE, O_WRONLY);
    write(fd, str, strlen(str));
    close(fd);

    /* remove the FIFO */
    unlink(FIFO_FILE);

    return 0;
}
