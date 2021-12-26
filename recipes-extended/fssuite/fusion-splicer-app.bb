DESCRIPTION = "the fusion splicer application"
SECTION = "product suite"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=400cd81ffff8789915eea5992120606e"

inherit qt6-qmake systemd

SYSTEMD_SERVICE:${PN} = "${PN}.service"

PV = "0.8"
PR = "r1"
SRCREV = "${AUTOREV}"
DEPENDS = "qtbase \
           qtdeclarative \
           qtwebsockets \
           qtsvg \
           qtcharts \
           qtvirtualkeyboard \
           qttools-native \
           xlslib"

RDEPENDS:${PN} = " \
                  postgresql \
                  xlslib \
                  qtbase qtbase-plugins \
                  qtdeclarative \
                  qtdeclarative-qmlplugins \
                  qtsvg \
                  qtsvg-plugins \
                  qtwebsockets \
                  qtwebsockets-qmlplugins \
                  qtvirtualkeyboard \
                  qtvirtualkeyboard-plugins \
                  qtvirtualkeyboard-qmlplugins \
                  qtcharts \
                  qtcharts-plugins \
                  qtcharts-qmlplugins \
                  source-han-sans-cn-fonts-medium \
"

FILES:${PN} += "${datadir}/fusion-splicer-app \
		${bindir} \
		${datadir}/fonts/* \
"

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
	install -m 0644 ${S}/resources/qtquickcontrols2.conf ${D}${datadir}/fusion-splicer-app
}
