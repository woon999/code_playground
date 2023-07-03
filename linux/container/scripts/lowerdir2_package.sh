#!/bin/bash

# copy which 
ldd /usr/bin/which;
mkdir -p tools/usr/bin;
cp /usr/bin/which tools/usr/bin/;

# copy rm
ldd /bin/rm;
mkdir -p tools/{bin,lib/x86_64-linux-gnu,lib64};
cp /bin/rm tools/bin/;
cp /lib/x86_64-linux-gnu/{libselinux.so.1,libc.so.6,libpcre.so.3,libdl.so.2,libpthread.so.0} tools/lib/x86_64-linux-gnu;
cp /lib64/ld-linux-x86-64.so.2 tools/lib64;
