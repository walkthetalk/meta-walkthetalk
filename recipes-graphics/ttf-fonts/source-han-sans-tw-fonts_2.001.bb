require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Traditional Chinese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-sans"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=67ce1af7af0b1daf08233acc3e786c46"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-sans/raw/release/SubsetOTF/SourceHanSansTW.zip \
    file://44-source-han-sans-tw.conf \
"
SRC_URI[md5sum] = "ef44bc94f0ec605cd0283ab5fcbe6bc4"
SRC_URI[sha256sum] = "46d0ab308913572698d7a0ddf031c74b420bc0d575aba9a332db9e7e872db40f"

S = "${WORKDIR}/SourceHanSansTW"

do_install() {
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 ${WORKDIR}/44-source-han-sans-tw.conf ${D}${sysconfdir}/fonts/conf.d/

    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${sysconfdir}/fonts \
    ${datadir}/fonts \
"

