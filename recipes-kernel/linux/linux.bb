DESCRIPTION = "Official Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

LINUX_VERSION_EXTENSION = "-optimus"
LINUX_VERSION = "5.4.7"

PV = "${LINUX_VERSION}"
PR = "1"

SRC_URI = "http://www.kernel.org/pub/linux/kernel/v5.x/linux-${LINUX_VERSION}.tar.xz \
"

SRC_URI_append_fsref = " \
	file://0001-simplefb-add-memory-region-support-for-device-tree.patch \
	file://add_fsref.patch \
	file://add_sys_file_for_load_bitstream.patch \
"

SRC_URI[md5sum] = "25023313e5ac66a98621917b088ec248"
SRC_URI[sha256sum] = "abc9b21d9146d95853dac35f4c4489a0199aff53ee6eee4b0563d1b37079fcc9"

S = "${WORKDIR}/linux-${LINUX_VERSION}"

do_configure_prepend() {
	make O=${B} -C ${S} ${KERNEL_MACHINE}

	echo "# Global settings from linux recipe" >> ${B}/.config
	echo "CONFIG_LOCALVERSION=\"${LINUX_VERSION_EXTENSION}\"" >> ${B}/.config
}
