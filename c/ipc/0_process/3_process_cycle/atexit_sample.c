#include <stdio.h>
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
    exit(0); // atexit()에 등록된 함수를 호출한 후 프로세스를 종료한다.
}