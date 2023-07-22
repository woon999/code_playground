#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void exitfunc()
{
    printf("Called cleanup function - exitfunc()\n");
    return;
}

int main()
{
    atexit(exitfunc);
    printf("Hello, World!\n");
    _exit(0); // exit(0)과 동일한 기능을 수행하지만, atexit()에 등록된 함수를 호출하지 않는다.
}