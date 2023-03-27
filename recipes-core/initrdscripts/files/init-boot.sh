#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

#sudo mkdir /proc
#sudo mkdir /sys
mount -t proc proc /proc
mount -t sysfs sysfs /sys

mknod -m 666 /dev/mem c 1 1

mknod /dev/ttyS0 c 4 64
mknod /dev/ttySIF0 c 249 0
setsid cttyhack /bin/sh
