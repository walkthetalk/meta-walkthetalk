require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family for Japanese"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-serif"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-serif/raw/release/SubsetOTF/SourceHanSerifJP.zip \
"
SRC_URI[md5sum] = "ac1d55f863e6a0434a0b85e39fccbec5"
SRC_URI[sha256sum] = "0c78abd9261986f59f80c148f5bdc52c7531ea2c59e9a434bd9a7b80610bcaa3"

S = "${WORKDIR}/SourceHanSerifJP"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name '*.otf' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES_${PN} = " \
    ${datadir}/fonts \
"

