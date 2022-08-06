SUMMARY = "A C/C++ multi-platform library for dynamically generating Excel(TM) files"
HOMEPAGE = "http://xlslib.sourceforge.net"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "https://sourceforge.net/projects/xlslib/files/${BPN}-package-${PV}.zip \
	   file://remove-semicolon.patch"

SRC_URI[md5sum] = "567336ebee41552195c4035c87b9361b"
SRC_URI[sha256sum] = "05a5d052ffdd6590755949d80d16a56285561557bc9a5e887e3b8b3fef92a3f3"

S = "${WORKDIR}/${BPN}/${BPN}"
FILES:${PN} = "${libdir} ${includedir}/xlslib"

DEPENDS += "automake-native autoconf-native"

do_configure() {
	/bin/sh ./bootstrap

	# becuse of cross_compiling, disable some check
	echo "ac_cv_have_pragma_pack_N+=no" >> wtt.cache
	echo "ac_cv_have_pragma_pack_push_pop+=no" >> wtt.cache
	echo "ac_cv_have_pragma_pack_cc_too_smart+=no" >> wtt.cache

	LIBS=-lstdc++ ./configure --prefix=${D}/usr --host=arm-linux --cache-file=wtt.cache
}

do_compile() {
	oe_runmake
}
do_install() {
	oe_runmake install
}
