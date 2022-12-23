#!/usr/bin/env sh

disk="/dev/mmcblk1"

fdisk $disk << EOF
n
p
1

+128M
n
p
2


w
EOF

mkfs.vfat -F 32 ${disk}p1
mkfs.ext4 ${disk}p2

mount /dev/mmcblk0p1 /mnt
mkdir /tmp/p1
mkdir /tmp/p2
mount ${disk}p1 /tmp/p1
mount ${disk}p2 /tmp/p2

cp /mnt/uImage /tmp/p1/uImage
cp /mnt/zynq-fsref-emmc.dtb /tmp/p1/system.dtb
flashcp /mnt/boot.bin /dev/mtd0
cp /mnt/rootfs.tar.gz /tmp/p1/rootfs.tar.gz

cd /tmp/p2
tar -xf /tmp/p1/rootfs.tar.gz

cd ~
umount /tmp/p2
umount /tmp/p1
umount /mnt

echo "SUCCESSED!"

exit 0
