DESCRIPTION = "Official Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

LINUX_VERSION_EXTENSION = "-optimus"
LINUX_VERSION = "5.7.2"

PV = "${LINUX_VERSION}"
PR = "1"

SRC_URI = "http://www.kernel.org/pub/linux/kernel/v5.x/linux-${LINUX_VERSION}.tar.xz \
"

SRC_URI_append_fsref = " \
	file://0001-simplefb-add-memory-region-support-for-device-tree.patch \
	file://add_fsref.patch \
	file://add_sys_file_for_load_bitstream.patch \
"

SRC_URI[md5sum] = "3aec12c426030b27553006ea515a91a1"
SRC_URI[sha256sum] = "6065ae820e2d52a77a549ef97444c36adf7ab2969b294460256f028b4eed7909"

S = "${WORKDIR}/linux-${LINUX_VERSION}"

do_configure_prepend() {
	make O=${B} -C ${S} ${KERNEL_MACHINE}

	echo "# Global settings from linux recipe" >> ${B}/.config
	echo "CONFIG_LOCALVERSION=\"${LINUX_VERSION_EXTENSION}\"" >> ${B}/.config
}
