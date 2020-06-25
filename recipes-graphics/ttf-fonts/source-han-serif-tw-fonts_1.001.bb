require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Traditional Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/raw/release/SubsetOTF/SourceHanSerifTW.zip \
"
SRC_URI[md5sum] = "cbbefc3402d9f86ba05f24bf23431f50"
SRC_URI[sha256sum] = "8475fcac5ee874422494ab62a71e1e5d06af3102e5e05f31345c686c9d210041"

S = "${WORKDIR}/SourceHanSerifTW"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${datadir}/fonts \
"

