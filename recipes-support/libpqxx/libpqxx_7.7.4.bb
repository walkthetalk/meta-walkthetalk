DESCRIPTION = "C++ client API for PostgreSQL"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=227be903d79fb9bf45c7d1fe4d7cf0cd"

PV = "7.7.4"
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

SRCREV = "17e5a6c8ac3abc05329891aaa378bd6004b9c8ee"
SRC_URI = " \
	${GITHUB_MIRROR}/jtv/libpqxx.git;protocol=${GITHUB_PROTOCOL};branch=master \
	"
#	file://subst_python_shebang.patch \
#"

S = "${WORKDIR}/git"

#CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"
EXTRA_OECONF = " \
	--enable-shared \
	--disable-documentation \
	--with-postgres-include=${STAGING_DIR_HOST}/usr/include \
	--with-postgres-lib=${STAGING_DIR_HOST}/usr/lib \
"

FILES:${PN} = " \
	${libdir}/libpqxx-7.7.so \
	${bindir}/pqxx-config \
"

FILES:${PN}-dev = " \
	${libdir}/libpqxx.so \
	${libdir}/libpqxx.la \
	${libdir}/pkgconfig/* \
	${includedir} \
"

do_install:append() {
	sed -i 's|${STAGING_DIR_HOST}||' ${D}/usr/lib/pkgconfig/libpqxx.pc
}

