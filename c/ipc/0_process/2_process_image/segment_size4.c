#include <stdio.h>

// data +8
// bss  +0
int myglobalint1 = 500; // data 4
int main()
{
    static int mystaticint1;       // compiler optimization으로 포함 안됨
    static int mystaticint2 = 100; // data 4
    printf("Hello World\n");
    return 0;
}