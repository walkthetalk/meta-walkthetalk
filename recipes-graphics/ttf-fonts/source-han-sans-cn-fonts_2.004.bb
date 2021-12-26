require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Simplified Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=28010d6596196c21d55c40589bac441f"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/releases/download/${PV}R/SourceHanSansCN.zip \
    file://44-source-han-sans-cn.conf \
"
SRC_URI[md5sum] = "b4fe166f05bb41247230829da45f9d86"
SRC_URI[sha256sum] = "6841fc13f1c0d255cfeb33d2a2c68d24bbebd94ae2c070347a2b2b200a1db4d6"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-cn.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/CN/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

PACKAGES =+ "${PN}-bold \
             ${PN}-extralight \
             ${PN}-heavy \
             ${PN}-light \
             ${PN}-medium \
             ${PN}-normal \
             ${PN}-regular \
"

FILES:${PN}-bold       += "${datadir}/fonts/truetype/SourceHanSansCN-Bold.otf"
FILES:${PN}-extralight += "${datadir}/fonts/truetype/SourceHanSansCN-ExtraLight.otf"
FILES:${PN}-heavy      += "${datadir}/fonts/truetype/SourceHanSansCN-Heavy.otf"
FILES:${PN}-light      += "${datadir}/fonts/truetype/SourceHanSansCN-Light.otf"
FILES:${PN}-medium     += "${datadir}/fonts/truetype/SourceHanSansCN-Medium.otf"
FILES:${PN}-normal     += "${datadir}/fonts/truetype/SourceHanSansCN-Normal.otf"
FILES:${PN}-regular    += "${datadir}/fonts/truetype/SourceHanSansCN-Regular.otf"

FILES:${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"
RDEPENDS:${PN} += "${PN}-bold \
             ${PN}-extralight \
             ${PN}-heavy \
             ${PN}-light \
             ${PN}-medium \
             ${PN}-normal \
             ${PN}-regular \
"

