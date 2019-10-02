DESCRIPTION = "Official Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

LINUX_VERSION_EXTENSION = "-optimus"
LINUX_VERSION = "5.3.2"

PV = "${LINUX_VERSION}"
PR = "1"

SRC_URI = "http://www.kernel.org/pub/linux/kernel/v5.x/linux-${LINUX_VERSION}.tar.xz \
"

SRC_URI_append_fsref = " \
	file://0001-simplefb-add-memory-region-support-for-device-tree.patch \
	file://add_fsref.patch \
"

SRC_URI[md5sum] = "983a0a17a021e5066dce8a2eee626b6a"
SRC_URI[sha256sum] = "361f9c953bb5ca7dbc922c3f698170523667f6a2f43961ffb88fa47b6b10fc6b"

S = "${WORKDIR}/linux-${LINUX_VERSION}"

do_configure_prepend() {
	make O=${B} -C ${S} ${MACHINE}_defconfig

	echo "# Global settings from linux recipe" >> ${B}/.config
	echo "CONFIG_LOCALVERSION=\"${LINUX_VERSION_EXTENSION}\"" >> ${B}/.config
}
