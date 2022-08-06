require ttf.inc

SUMMARY = "Adobe OpenType Pan-CJK font family Mono version"
HOMEPAGE = "https://github.com/adobe-fonts/source-han-mono"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/OFL-1.1;md5=fac3a519e5e9eb96316656e0ca4f2b90"

inherit allarch fontcache

SRC_URI = " \
    https://github.com/adobe-fonts/source-han-mono/releases/download/1.002/SourceHanMono.ttc \
"
SRC_URI[md5sum] = "7aced0c964f273e2978887c981325611"
SRC_URI[sha256sum] = "0c192448de90848f11eb8336876883a9a36dc65b8965e489600cfcc7a67358c1"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts/truetype/
    find ./ -name 'SourceHanMono.ttc' -exec install -m 0644 {} ${D}${datadir}/fonts/truetype/ \;
}

FILES:${PN} = " \
    ${datadir}/fonts \
"

