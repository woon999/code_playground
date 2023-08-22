#!/bin/sh

set -xe 

clang -Wall -Wextra -o echo_multi_process_server echo_multi_process_server.c
