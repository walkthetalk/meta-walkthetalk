require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Korean"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=67ce1af7af0b1daf08233acc3e786c46"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/raw/release/SubsetOTF/SourceHanSansKR.zip \
    file://44-source-han-sans-kr.conf \
"
SRC_URI[md5sum] = "05529b46cdd8aca9ec2f64cd31069155"
SRC_URI[sha256sum] = "4217b66d8a0538db2741a8a9d955f2ebeb46a52265dfde0f27b20b0160b41413"

S = "${WORKDIR}/SourceHanSansKR"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-kr.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

