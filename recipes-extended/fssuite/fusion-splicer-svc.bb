DESCRIPTION = "Simple fs service"
SECTION = "fs suite"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pkgconfig

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-service = "${PN}.service"
SYSTEMD_PACKAGES = "${PN}-service"
inherit systemd

DEPENDS += " \
	coreutils-native \
	boost \
	opencv \
	gsl \
	lua \
	libwebsockets \
	libpqxx \
	xlslib \
"

RDEPENDS_${PN}-service = " \
	libopencv-core \
	libopencv-imgproc \
	gsl \
	lua \
	libwebsockets \
	libpng \
	libpqxx \
	xlslib \
"

PACKAGES =+ "${PN}-bin ${PN}-service"
FILES_${PN}-bin = " \
		   ${libdir}/firmware \
		   ${bindir} \
"
FILES_${PN}-service = "${systemd_system_unitdir}"

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git:///mnt/datum/repositories/walkthetalk/fsref.svc.git \
	file://${BPN}.service \
"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	install -d ${D}${bindir}
	install -d ${D}/${libdir}/firmware/
	install -m 0755 ${B}/output/release/bin/fusion-splicer-svc ${D}${bindir}
	install -m 0755 ${S}/scripts/start_fs.sh ${D}/${bindir}
	install -m 0755 ${S}/external/firmware/system.bit.bin ${D}/${libdir}/firmware/
}
