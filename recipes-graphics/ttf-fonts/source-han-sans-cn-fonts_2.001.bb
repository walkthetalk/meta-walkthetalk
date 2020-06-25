require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Simplified Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=67ce1af7af0b1daf08233acc3e786c46"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/raw/release/SubsetOTF/SourceHanSansCN.zip \
    file://44-source-han-sans-cn.conf \
"
SRC_URI[md5sum] = "9b00ec58fb88815f0275c12a5fcde821"
SRC_URI[sha256sum] = "dbc63746cd8daa8b219a236fe0278e5eb7f910bec90c9897ada440e6d796f256"

S = "${WORKDIR}/SourceHanSansCN"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-cn.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

