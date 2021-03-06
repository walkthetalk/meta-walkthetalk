SUMMARY = "Canonical libwebsockets.org websocket library"
HOMEPAGE = "https://libwebsockets.org/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c47b078124308a4e1354e8d59f606b7"

DEPENDS = "zlib"

S = "${WORKDIR}/git"
SRCREV = "8021ab54f02331263346a19a1a0a61ebe92a6ff1"
SRC_URI = "${GITHUB_MIRROR}/warmcat/libwebsockets.git;protocol=${GITHUB_PROTOCOL};branch=v4.0-stable"

inherit cmake pkgconfig

PACKAGECONFIG ?= "libuv client server http2 ssl"
PACKAGECONFIG[externalpoll] = "-DLWS_WITH_EXTERNAL_POLL=ON,-DLWS_WITH_EXTERNAL_POLL=OFF,"
PACKAGECONFIG[client] = "-DLWS_WITHOUT_CLIENT=OFF,-DLWS_WITHOUT_CLIENT=ON,"
PACKAGECONFIG[http2] = "-DLWS_WITH_HTTP2=ON,-DLWS_WITH_HTTP2=OFF,"
PACKAGECONFIG[ipv6] = "-DLWS_IPV6=ON,-DLWS_IPV6=OFF,"
PACKAGECONFIG[libev] = "-DLWS_WITH_LIBEV=ON,-DLWS_WITH_LIBEV=OFF,libev"
PACKAGECONFIG[libuv] = "-DLWS_WITH_LIBUV=ON,-DLWS_WITH_LIBUV=OFF,libuv"
PACKAGECONFIG[server] = "-DLWS_WITHOUT_SERVER=OFF,-DLWS_WITHOUT_SERVER=ON,"
PACKAGECONFIG[ssl] = "-DLWS_WITH_SSL=ON,-DLWS_WITH_SSL=OFF,openssl"
PACKAGECONFIG[testapps] = "-DLWS_WITHOUT_TESTAPPS=OFF,-DLWS_WITHOUT_TESTAPPS=ON,"

EXTRA_OECMAKE += " \
    -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} \
"

PACKAGES =+ "${PN}-testapps"

FILES_${PN}-testapps += "${datadir}/libwebsockets-test-server/*"

CFLAGS_append = " -Wno-error"
