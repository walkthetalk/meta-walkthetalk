DESCRIPTION = "the fusion splicer application"
SECTION = "product suite"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=400cd81ffff8789915eea5992120606e"

inherit qmake5

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-service = "${PN}.service"
SYSTEMD_PACKAGES = "${PN}-service"

inherit systemd

PV = "0.8"
PR = "r1"
SRCREV = "${AUTOREV}"
DEPENDS = "qtbase \
           qtdeclarative \
           qtquickcontrols2 \
           qtwebsockets \
           qtsvg \
           qtgraphicaleffects \
           qtvirtualkeyboard \
           qttools-native \
           xlslib"

RDEPENDS_${PN}-bin = " \
                  postgresql \
                  xlslib \
                  qtbase qtbase-plugins qtsvg \
                  qtdeclarative \
                  qtdeclarative-qmlplugins \
                  qtgraphicaleffects \
                  qtvirtualkeyboard \
                  qtquickcontrols2-qmlplugins \
                  qtwebsockets \
                  qtsvg-plugins \
                  qtwebsockets-qmlplugins \
                  qtvirtualkeyboard-qmlplugins \
                  qtvirtualkeyboard-plugins \
"

RDEPENDS_${PN}-service = " \
		${PN}-bin \
"

PACKAGES =+ "${PN}-bin ${PN}-service"
FILES_${PN}-bin = "${datadir}/fusion-splicer-app \
	           ${datadir}/fusion-splicer-app/i18n \
		   ${bindir} \
"
FILES_${PN}-service = "${systemd_system_unitdir}"

SRC_URI = " \
	git:///mnt/datum/repositories/walkthetalk/fsref.app.git \
	file://${BPN}.service \
"

S = "${WORKDIR}/git"

do_install() {
	oe_runmake install DESTDIR=${D}

	# binary files
	install -d ${D}${bindir}
	install -m 0755 ${B}/release/fusion-splicer-app ${D}${bindir}

	# service files
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/fusion-splicer-app.service ${D}${systemd_system_unitdir}

	# resource files directory
	install -d ${D}${datadir}/fusion-splicer-app

	# language files
	install -d ${D}${datadir}/fusion-splicer-app/i18n
	install -m 0644 ${S}/i18n/*.lang ${D}${datadir}/fusion-splicer-app/i18n
}

FILES_${PN} += "${datadir}/fonts/*"
