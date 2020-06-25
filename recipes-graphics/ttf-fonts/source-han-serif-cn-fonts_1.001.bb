require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Simplified Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/raw/release/SubsetOTF/SourceHanSerifCN.zip \
"
SRC_URI[md5sum] = "e7f29b671b6fca8353e4a68b15380331"
SRC_URI[sha256sum] = "bd75d74f38e0088f3425c8bb448ef03dc170386bcf2693a434141aa844b77a30"

S = "${WORKDIR}/SourceHanSerifCN"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${datadir}/fonts \
"

