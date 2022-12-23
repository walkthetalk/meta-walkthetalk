DESCRIPTION = "Extension Tools for Zynq"
SECTION = "tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://zynq_bit2bin.c \
           file://part_emmc.sh \
           file://prepare_fpga"
RDEPENDS_${PN} = "${VIRTUAL-RUNTIME_base-utils}"

S = "${WORKDIR}"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
	echo CC is ${CC}
	${CC} zynq_bit2bin.c -o zynq_bit2bin
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 zynq_bit2bin ${D}${bindir}
	install -m 0755 prepare_fpga ${D}${bindir}
	install -m 0755 part_emmc.sh ${D}${bindir}
}
