DESCRIPTION = "C++ client API for PostgreSQL"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=fe6a87ebf0a117888ce73e60a1e46d18"

PV = "7.6.0"
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

SRCREV = "221ddc8be329bafb376a3d83b9cd257fd52fc7b7"
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
	${libdir}/libpqxx-7.6.so \
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

