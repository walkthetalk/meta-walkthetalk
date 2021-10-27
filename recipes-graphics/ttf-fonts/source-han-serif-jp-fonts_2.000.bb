require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Japanese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/releases/download/${PV}R/SourceHanSerifJP.zip \
"
SRC_URI[md5sum] = "ad4e3dc830edc405ed05dd0cf51bda20"
SRC_URI[sha256sum] = "49c9308a4717303f8b490783ec24e50ad2df9566d2828a488a04db827a6e9781"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/JP/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${datadir}/fonts \
"

