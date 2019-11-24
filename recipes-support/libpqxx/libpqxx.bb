DESCRIPTION = "C++ client API for PostgreSQL"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d1b370cdd0dae4a6607aa8e5071e67b"

PV = "6.4.5"
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
	git://github.com/jtv/libpqxx.git;protocol=https;branch=6.4;tag=${PV} \
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
	${libdir}/libpqxx-6.4.so \
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

