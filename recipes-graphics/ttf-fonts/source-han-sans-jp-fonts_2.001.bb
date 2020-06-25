require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Japanese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=67ce1af7af0b1daf08233acc3e786c46"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/raw/release/SubsetOTF/SourceHanSansJP.zip \
    file://44-source-han-sans-jp.conf \
"
SRC_URI[md5sum] = "c48c8d160cfa11606ec5adf0a0aaa51a"
SRC_URI[sha256sum] = "285f669d01f0b790b40f00ac85296b299a95542386c0cb6c9fc830e52bad8757"

S = "${WORKDIR}/SourceHanSansJP"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-jp.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

