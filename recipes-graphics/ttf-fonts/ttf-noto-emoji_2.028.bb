require ttf.inc

SUMMARY = "Google noto emoji font pack"
HOMEPAGE = "https://github.com/googlefonts/noto-emoji"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://fonts/LICENSE;md5=55719faa0112708e946b820b24b14097"

SRC_URI = "${GITHUB_MIRROR}/googlefonts/noto-emoji;protocol=${GITHUB_PROTOCOL}"
SRCREV = "6f11c8db698594bd982b90b5a07b2c0ee62bb145"

PACKAGES = "${PN}-color ${PN}-regular"
FONT_PACKAGES = "${PN}-color ${PN}-regular"

S = "${WORKDIR}/git"

FILES:${PN}-color = "${datadir}/fonts/truetype/NotoColorEmoji.ttf"
FILES:${PN}-regular = "${datadir}/fonts/truetype/NotoEmoji-Regular.ttf"

do_compile[noexec] = "1"
