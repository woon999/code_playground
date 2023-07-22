#!/bin/sh

clang -o basicfork basicfork.c
clang -o pids_after_fork pids_after_fork.c
clang -o atexit_sample atexit_sample.c
clang -o at_exit_sample at_exit_sample.c
