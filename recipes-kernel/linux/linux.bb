DESCRIPTION = "Official Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

LINUX_VERSION_EXTENSION = "-optimus"
LINUX_VERSION = "5.14.12"

PV = "${LINUX_VERSION}"
PR = "1"

SRC_URI = "http://www.kernel.org/pub/linux/kernel/v5.x/linux-${LINUX_VERSION}.tar.xz \
"

SRC_URI:append:fsref = " \
	file://0001-simplefb-add-memory-region-support-for-device-tree.patch \
	file://add_fsref.patch \
	file://add_sys_file_for_load_bitstream.patch \
	file://system.bit.bin.tm050 \
"

SRC_URI[md5sum] = "a52286fe206a3f7664ca5a6c5995b9ed"
SRC_URI[sha256sum] = "8d272679dc5285136bfe1f6d54548c7cf9f1ed8925c04b8bae149c8983eb5c37"

S = "${WORKDIR}/linux-${LINUX_VERSION}"

do_configure:prepend() {
	make O=${B} -C ${S} ${KERNEL_MACHINE}

	echo "# Global settings from linux recipe" >> ${B}/.config
	echo "CONFIG_LOCALVERSION=\"${LINUX_VERSION_EXTENSION}\"" >> ${B}/.config
}
