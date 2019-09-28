SUMMARY = "Canonical libwebsockets.org websocket library"
HOMEPAGE = "https://libwebsockets.org/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b415f60bd65e057120037bb15cad7139"

DEPENDS = "zlib"

PV = "2.4.1+git"

#SRC_URI = "https://github.com/warmcat/libwebsockets/archive/v${PV}.tar.gz \
#           file://remove_u_int64_t_definition.patch \
#"

SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;tag=8e9871d99559799efa67a4677bd35bd1c48c2462"
S = "${WORKDIR}/git"

SRC_URI[md5sum] = "742c94e3fd255bb94735a87e86b1bdde"
SRC_URI[sha256sum] = "29414be4f79f6abc0e6aadccd09a4da0f0c431e3b5691f496acd081ae6a8240c"

inherit cmake pkgconfig

PACKAGECONFIG ?= "client server http2"
PACKAGECONFIG[client] = "-DLWS_WITHOUT_CLIENT=OFF,-DLWS_WITHOUT_CLIENT=ON,"
PACKAGECONFIG[http2] = "-DLWS_WITH_HTTP2=ON,-DLWS_WITH_HTTP2=OFF,"
PACKAGECONFIG[ipv6] = "-DLWS_IPV6=ON,-DLWS_IPV6=OFF,"
PACKAGECONFIG[libev] = "-DLWS_WITH_LIBEV=ON,-DLWS_WITH_LIBEV=OFF,libev"
PACKAGECONFIG[libuv] = "-DLWS_WITH_LIBUV=ON,-DLWS_WITH_LIBUV=OFF,libuv"
PACKAGECONFIG[server] = "-DLWS_WITHOUT_SERVER=OFF,-DLWS_WITHOUT_SERVER=ON,"
PACKAGECONFIG[ssl] = "-DLWS_WITH_SSL=ON,-DLWS_WITH_SSL=OFF,openssl"
PACKAGECONFIG[testapps] = "-DLWS_WITHOUT_TESTAPPS=OFF,-DLWS_WITHOUT_TESTAPPS=ON,"

PACKAGES =+ "${PN}-testapps"

FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}-testapps += "${datadir}/libwebsockets-test-server/*"
