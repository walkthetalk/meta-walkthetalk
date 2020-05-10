DESCRIPTION = "C++ client API for PostgreSQL"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=f8ef8e6ae44b3eda3f116eee3a64f236"

PV = "7.0.7"
PR = "r1"

inherit autotools

DEPENDS = " \
	python3-native \
	autoconf-archive-native \
	postgresql \
"

RDEPENDS_${PN} = " \
	libpq \
"

SRC_URI = " \
	${GITHUB_MIRROR}/jtv/libpqxx.git;protocol=${GITHUB_PROTOCOL};branch=master;tag=${PV} \
	"
#	file://subst_python_shebang.patch \
#"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
	--enable-shared \
	--disable-documentation \
	--with-postgres-include=${STAGING_DIR_HOST}/usr/include \
	--with-postgres-lib=${STAGING_DIR_HOST}/usr/lib \
"

FILES_${PN} = " \
	${libdir}/libpqxx-7.0.so \
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

