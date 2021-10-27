require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Traditional Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/releases/download/${PV}R/SourceHanSerifTW.zip \
"
SRC_URI[md5sum] = "7c9450fb25c9ad0af20cc8a38ace5a3b"
SRC_URI[sha256sum] = "0db3a736646bff21b92a02748dcdddcac99927d0a7ec08cf97db032c81a29f6d"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/TW/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${datadir}/fonts \
"

