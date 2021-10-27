require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family Serif version"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/OFL-1.1;md5=fac3a519e5e9eb96316656e0ca4f2b90"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/releases/download/${PV}R/SourceHanSerif.ttc.zip \
"
SRC_URI[md5sum] = "8bc3de9923ef9edbf531b55d6128cdfb"
SRC_URI[sha256sum] = "443832c1a6fb8304fe6013bb175289bca3afd04ff7fbb662fe9425f940ec19c3"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name 'SourceHanSerif.ttc' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${datadir}/fonts \
"

