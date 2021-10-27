require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Korean"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=28010d6596196c21d55c40589bac441f"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/releases/download/${PV}R/SourceHanSansKR.zip \
    file://44-source-han-sans-kr.conf \
"
SRC_URI[md5sum] = "51feb43a553327a759b3aeeaa4e188f9"
SRC_URI[sha256sum] = "02fe28a48c6381c49d61c27a1b173c77f0e6f2b9f2b68e79f076f10a6a8f4bfe"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-kr.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/KR/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

