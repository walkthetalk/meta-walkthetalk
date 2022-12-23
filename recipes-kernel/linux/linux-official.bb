DESCRIPTION = "Official Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

LINUX_VERSION_EXTENSION = "-optimus"
LINUX_VERSION = "5.15.53"

PV = "${LINUX_VERSION}"
PR = "1"

SRC_URI = "http://www.kernel.org/pub/linux/kernel/v5.x/linux-${LINUX_VERSION}.tar.xz \
"

# NOTE: for zynq qspi problem:
# https://support.xilinx.com/s/question/0D52E00006hpObcSAE/linux-qspi-driver-and-4-byte-address
SRC_URI:append:fsref = " \
	file://0001-simplefb-add-memory-region-support-for-device-tree.patch \
	file://0002-hack_for_zynq_qspi_3bytes_address.patch \
	file://add_fsref.patch \
	file://add_sys_file_for_load_bitstream.patch \
	file://system.bit.bin.tm050 \
"

SRC_URI[md5sum] = "2c28b3a681c90f52c1501998f34ec659"
SRC_URI[sha256sum] = "f3aa717243051f3fcca90ebfe26fe5c3a596c2f6047846e8d1724ea90df77b07"

S = "${WORKDIR}/linux-${LINUX_VERSION}"

do_configure:prepend() {
	make O=${B} -C ${S} ${KERNEL_MACHINE}

	echo "# Global settings from linux recipe" >> ${B}/.config
	echo "CONFIG_LOCALVERSION=\"${LINUX_VERSION_EXTENSION}\"" >> ${B}/.config
}
