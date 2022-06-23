DESCRIPTION = "Utility for tracing a bitmap (input: PBM,PGM,PPM,BMP; output: EPS,PS,PDF,SVG,DXF,PGM,Gimppath,XFig)"
SECTION = "software platform"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ccdcb5472e5e7e8061f4128ef20e049"

DEPENDS = "zlib"
RDEPENDS_${PN} = "zlib"

SRC_URI = "http://potrace.sourceforge.net/download/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "86ecf296f8160cfd49c8304ea0bdd4cb"
SRC_URI[sha256sum] = "5b4307bcf15c98d3614619a6ead606b7e2aa9df63ae38fc6a2786d8ace682937"

#--enable-a4: Use A4 as the default paper size.
#--enable-metric: Use metric units (centimeters) as default
#--disable-static: This switch prevents installation of static versions of the libraries.
#--with-libpotrace: Install the library and headers.
#--docdir=/usr/share/doc/potrace-1.16

EXTRA_OECONF = "--with-libpotrace"

inherit autotools
