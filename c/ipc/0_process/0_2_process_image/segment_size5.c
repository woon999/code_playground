#include <stdio.h>

// data +8
// bss  +8
int myglobalint1 = 500;
int myglobalint2;
int main()
{
    static int mystaticint1;
    static int mystaticint2 = 100;
    printf("Hello World\n");
    return 0;
}