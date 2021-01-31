require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family Serif version"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/OFL-1.1;md5=fac3a519e5e9eb96316656e0ca4f2b90"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/releases/download/1.001R/SourceHanSerif.ttc \
"
SRC_URI[md5sum] = "386dd14320f81ad42668d2f06aeef091"
SRC_URI[sha256sum] = "85cc634fa228286ca307803bbb4ca61f61fa821b3ed573f4f07c6f0c064426b5"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name 'SourceHanSerif.ttc' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${datadir}/fonts \
"

