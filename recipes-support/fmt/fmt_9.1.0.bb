SUMMARY = "Open-source formatting library for C++"
HOMEPAGE = "https://fmt.dev"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=af88d758f75f3c5c48a967501f24384b"

DEPENDS = "zlib"

S = "${WORKDIR}/fmt-${PV}"
SRC_URI = "https://github.com/fmtlib/fmt/releases/download/${PV}/fmt-${PV}.zip"
SRC_URI[sha256sum] = "cceb4cb9366e18a5742128cb3524ce5f50e88b476f1e54737a47ffdf4df4c996"

UPSTREAM_CHECK_URI = "https://github.com/fmtlib/${BPN}/releases"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[static] = "-DBUILD_SHARED_LIBS=OFF,-DBUILD_SHARED_LIBS=ON,"

#EXTRA_OECMAKE += " \
#    -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} \
#"
#
#PACKAGES =+ "${PN}-testapps ${PN}-evlib-event ${PN}-evlib-uv ${PN}-evlib-ev"
#
#FILES:${PN}-testapps += "${datadir}/libwebsockets-test-server/* ${bindir}/libwebsockets-test-*"
#FILES:${PN}-evlib-event += "${libdir}/libwebsockets-evlib_event.so"
#FILES:${PN}-evlib-uv += "${libdir}/libwebsockets-evlib_uv.so"
#FILES:${PN}-evlib-ev += "${libdir}/libwebsockets-evlib_ev.so"
#
#RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'libevent', '${PN}-evlib-event', '', d)}"
#RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'libuv', '${PN}-evlib-uv', '', d)}"
#RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'libev', '${PN}-evlib-ev', '', d)}"

RDEPENDS:${PN}-dev += " ${@bb.utils.contains('PACKAGECONFIG', 'static', '${PN}-staticdev', '', d)}"
