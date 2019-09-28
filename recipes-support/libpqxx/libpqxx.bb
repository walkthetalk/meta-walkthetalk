DESCRIPTION = "C++ client API for PostgreSQL"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=b47c6e627b703512c8bbe2c0cf86eec3"

PV = "6.1.0"
PR = "r1"

inherit autotools

DEPENDS = " \
	python-native \
	autoconf-archive-native \
	postgresql \
"

RDEPENDS_${PN} = " \
	libpq \
"

SRC_URI = " \
	git://github.com/jtv/libpqxx.git;protocol=https;tag=44970d7331e4f369e160af0135a2d1fc16f27a21 \
	file://disable_check_pg_config.patch \
	file://subst_python_shebang.patch \
"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
	--enable-shared \
	--disable-documentation \
	--with-postgres-include=${STAGING_DIR_HOST}/usr/include \
	--with-postgres-lib=${STAGING_DIR_HOST}/usr/lib \
"

FILES_${PN} = " \
	${libdir}/libpqxx-6.1.so \
	${bindir}/pqxx-config \
"

FILES_${PN}-dev = " \
	${libdir}/libpqxx.so \
	${libdir}/libpqxx.la \
	${libdir}/pkgconfig/* \
	${includedir} \
"

do_install_append() {
	sed -i 's|${STAGING_DIR_HOST}||' ${D}/usr/lib/pkgconfig/libpqxx.pc
}

