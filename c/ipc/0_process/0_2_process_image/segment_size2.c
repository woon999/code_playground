#include <stdio.h>

// data +0
// bss  +0
int main()
{
    static int mystaticint1; // optimization으로 할당 안됨
    printf("Hello World\n");
    return 0;
}
