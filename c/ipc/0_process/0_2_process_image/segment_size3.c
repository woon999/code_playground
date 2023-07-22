#include <stdio.h>

// data +4
// bss  +4
int main()
{
    static int mystaticint1;       // bss  4
    static int mystaticint2 = 100; // data 4
    printf("Hello World\n");
    return 0;
}