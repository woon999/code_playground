#!/bin/sh

set -xe 

clang -Wall -Wextra -o echo_client echo_client.c
clang -Wall -Wextra -o echo_server echo_server.c
