require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Korean"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/raw/release/SubsetOTF/SourceHanSerifKR.zip \
"
SRC_URI[md5sum] = "f50b39c4f7c242cafd8a9e8d2a001663"
SRC_URI[sha256sum] = "a162d6446e02a098227f112901ceb8c7d6889ae0f46e48ee384791a36a46f5e2"

S = "${WORKDIR}/SourceHanSerifKR"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${datadir}/fonts \
"

