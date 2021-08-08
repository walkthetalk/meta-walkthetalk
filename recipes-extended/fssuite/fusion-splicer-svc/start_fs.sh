#!/usr/bin/env sh
set -e

# TODO: remove it
mount -o remount,sync /

# use ethernet
ifconfig eth0 up
ifconfig eth0 192.168.3.4 netmask 255.255.255.0

# load fpga
echo system.bit.bin.tm050 > /sys/class/fpga_manager/fpga0/firmware

#fusion-splicer-svc &
#systemctl restart fusion-splicer-app
#systemctl restart fbtest
