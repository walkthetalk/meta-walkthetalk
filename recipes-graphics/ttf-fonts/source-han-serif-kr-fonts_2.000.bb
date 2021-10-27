require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Korean"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/releases/download/${PV}R/SourceHanSerifKR.zip \
"
SRC_URI[md5sum] = "a767ee7be379a71e3ad5464afdfa9a85"
SRC_URI[sha256sum] = "fc3e801d765440e37e0f662212d19598ca7f2f1acca6d83c4c6c0a2713791f5b"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/KR/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${datadir}/fonts \
"

