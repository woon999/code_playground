#!/bin/bash

# copy ls
cp /bin/ls myroot/bin/;
cp /lib/x86_64-linux-gnu/{libselinux.so.1,libc.so.6,libpcre.so.3,libdl.so.2,libpthread.so.0} myroot/lib/x86_64-linux-gnu;
cp /lib64/ld-linux-x86-64.so.2 myroot/lib64;
