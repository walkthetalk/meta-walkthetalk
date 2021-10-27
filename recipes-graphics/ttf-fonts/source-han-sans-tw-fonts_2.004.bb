require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Traditional Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=28010d6596196c21d55c40589bac441f"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/releases/download/${PV}R/SourceHanSansTW.zip \
    file://44-source-han-sans-tw.conf \
"
SRC_URI[md5sum] = "7810919187cb4eb631affc766f2eaf56"
SRC_URI[sha256sum] = "11d78c8fbc1a4bb04a453cdd65c99db8d41ec5cd6ba6d68c8638e6ba170de806"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-tw.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./SubsetOTF/TW/ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

