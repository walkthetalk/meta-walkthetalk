require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Simplified Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/releases/download/${PV}R/SourceHanSerifCN.zip \
"
SRC_URI[md5sum] = "27d5602e755cb21e5ec5c4245f786136"
SRC_URI[sha256sum] = "467f36c4f8d8027dda8b5fa89052c945f9f0ac629e50718af94b4258b82b7a34"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/CN/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${datadir}/fonts \
"

