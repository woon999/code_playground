#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/mman.h>
void write_mmap_sample_data();

int main()
{
    struct stat mmapstat;
    char *data;
    int minbyteindex;
    int maxbyteindex;
    int offset;
    int fd;
    int unmapstatus;

    write_mmap_sample_data();

    // Get the file size
    if (stat("MMAP_DATA.txt", &mmapstat) == -1)
    {
        perror("stat failure");
        return 1;
    }

    // Open the file
    if ((fd = open("MMAP_DATA.txt", O_RDONLY)) == -1)
    {
        perror("open failure");
        return 1;
    }

    // Map the file
    data = mmap((caddr_t)0, mmapstat.st_size, PROT_READ, MAP_SHARED, fd, 0);

    if (data == (caddr_t)(-1))
    {
        perror("mmap failure");
        return 1;
    }
    minbyteindex = 0;
    maxbyteindex = mmapstat.st_size - 1;

    // Read the data (until the user enters -1)
    do
    {
        printf("Enter -1 to quit or ");
        printf("enter a number between %d and %d: ", minbyteindex, maxbyteindex);
        scanf("%d", &offset);
        if ((offset >= 0) && (offset <= maxbyteindex))
            printf("Received char at %d is %c\n", offset, data[offset]);
        else if (offset != -1)
            printf("Received invalid index %d\n", offset);
    } while (offset != -1);

    // Unmap the file
    unmapstatus = munmap(data, mmapstat.st_size);

    if (unmapstatus == -1)
    {
        perror("munmap failure");
        return 1;
    }

    close(fd);
    system("rm -f MMAP_DATA.txt");
    return 0;
}

// Write sample data(알파벳, 숫자) to a file
void write_mmap_sample_data()
{
    int fd;
    char ch;
    struct stat textfilestat;

    // Create a file
    fd = open("MMAP_DATA.txt", O_CREAT | O_TRUNC | O_WRONLY, 0666);

    if (fd == -1)
    {
        perror("File open error ");
        return;
    }

    // Write A to Z
    ch = 'A';

    while (ch <= 'Z')
    {
        write(fd, &ch, sizeof(ch));
        ch++;
    }
    // Write 0 to 9
    ch = '0';

    while (ch <= '9')
    {
        write(fd, &ch, sizeof(ch));
        ch++;
    }
    // Write a to z
    ch = 'a';

    while (ch <= 'z')
    {
        write(fd, &ch, sizeof(ch));
        ch++;
    }
    close(fd);
    return;
}