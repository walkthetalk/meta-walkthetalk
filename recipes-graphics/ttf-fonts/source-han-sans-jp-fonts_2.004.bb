require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Japanese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=28010d6596196c21d55c40589bac441f"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/releases/download/${PV}R/SourceHanSansJP.zip \
    file://44-source-han-sans-jp.conf \
"
SRC_URI[md5sum] = "adecdfea722dabb2ba2b568cbdc4d897"
SRC_URI[sha256sum] = "1ae9f62ad620d686c4a049ce25cf54e3afd8fefc954a678c644cf9802750c17e"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-jp.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/JP/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

