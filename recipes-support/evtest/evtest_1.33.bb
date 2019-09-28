DESCRIPTION = "Simple tool for input event debugging."
HOMEPAGE = "http://people.freedesktop.org/~whot/evtest/"
AUTHOR = "Vojtech Pavlik <vojtech@suse.cz>"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libxml2"

SRC_URI = "http://cgit.freedesktop.org/evtest/snapshot/evtest-${PV}.tar.bz2;name=archive"
SRC_URI[archive.md5sum] = "08ed769710862588eca02f8848fa88a2"
SRC_URI[archive.sha256sum] = "2cbcd1d05ad27dc7164d134ce21e830b4c34cf6df3bdb6eca2b3918665d1decb"

inherit autotools
