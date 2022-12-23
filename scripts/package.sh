#!/usr/bin/env sh

SRC_DIR="/mnt/udatum/ps_linux/build/tmp/deploy/images/fsref/"
DST_DIR="/mnt/udatum/ps_linux/release/2022.12.23/"

cp ${SRC_DIR}/boot.bin-fsref-* ${DST_DIR}/boot.bin
cp ${SRC_DIR}/fitImage--* ${DST_DIR}/uImage
cp ${SRC_DIR}/zynq-fsref-emmc--* ${DST_DIR}/zynq-fsref-emmc.dtb
cp ${SRC_DIR}/zynq-fsref-sdcard--* ${DST_DIR}/system.dtb
cp ${SRC_DIR}/dev-image-fsref-*.rootfs.tar.gz ${DST_DIR}/rootfs.tar.gz
