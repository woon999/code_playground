/* signal_raising.c */
#include <stdio.h>
#include <signal.h>
#include <stdlib.h>

int main()
{
    printf("Testing SIGSTOP\n");
    raise(SIGSTOP);
    return 0;
}