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
	postgresql-native \
	coreutils-native \
	boost \
	opencv \
	gsl \
	lua \
	libwebsockets \
	libpqxx \
	xlslib \
"

RDEPENDS_${PN}-bin = " \
	postgresql \
	libopencv-core \
	libopencv-imgproc \
	gsl \
	lua \
	libwebsockets \
	libpng \
	libpqxx \
	xlslib \
"

RDEPENDS_${PN}-service = " \
	${PN}-bin \
"

PACKAGES =+ "${PN}-bin ${PN}-service"
FILES_${PN}-bin = " \
		   ${libdir} \
		   ${datadir} \
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
	install -m 0755 ${S}/external/firmware/system.bit.bin ${D}/${libdir}/firmware/

	# service files
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/${PN}.service ${D}${systemd_system_unitdir}

	install -d ${D}${datadir}
	install -d ${D}${datadir}/${PN}
	install -m 0755 ${S}/scripts/start_fs.sh ${D}${datadir}/${PN}
	install -m 0755 ${S}/scripts/initdb.sh ${D}${datadir}/${PN}
	install -m 0755 ${S}/scripts/initdb.sql ${D}${datadir}/${PN}
}

pkg_postinst_ontarget_${PN}-bin () {
	if test -x ${datadir}/${PN}/initdb.sh
	then
		${datadir}/${PN}/initdb.sh
	fi
}

#pkg_postinst_${PN}-bin () {
#	PGDATA=$D/var/lib/postgresql/data
#	rm -rf ${PGDATA}
#	mkdir ${PGDATA}
#	mkdir ${PGDATA}/../abc
#
#	initdb --no-locale -E UTF8 -D $PGDATA
#	cat $D${datadir}/${PN}/initdb.sql | postgres --single -D $PGDATA -j
#
#	chown postgres:postgres ${PGDATA}
#	chown postgres:postgres $D${datadir}/${PN}/initdb.sql
#	chown postgres:postgres $D${datadir}/${PN}/initdb.sh
#}
