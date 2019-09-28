DESCRIPTION = "Utility for tracing a bitmap (input: PBM,PGM,PPM,BMP; output: EPS,PS,PDF,SVG,DXF,PGM,Gimppath,XFig)"
SECTION = "software platform"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ccdcb5472e5e7e8061f4128ef20e049"

PV = "1.15"

DEPENDS = "zlib"
RDEPENDS_${PN} = "zlib"

SRC_URI = "http://potrace.sourceforge.net/download/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "60cecdaa9d14e0008f96b68dce77d246"
SRC_URI[sha256sum] = "a9b33904ace328340c850a01458199e0064e03ccaaa731bc869a842b1b8d529d"

EXTRA_OECONF = "--with-libpotrace"

inherit autotools
