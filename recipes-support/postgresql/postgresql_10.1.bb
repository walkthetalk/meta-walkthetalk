require postgresql.inc

SYSTEMD_AUTO_ENABLE_${PN} = "enable"

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=81b69ddb31a8be66baafd14a90146ee2"

PR = "${INC_PR}.0"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://not-check-libperl.patch \
"

SRC_URI[md5sum] = "0a92328d9970bfb85dcecd011817238a"
SRC_URI[sha256sum] = "3ccb4e25fe7a7ea6308dea103cac202963e6b746697366d72ec2900449a5e713"

PACKAGECONFIG_kl360 = ""
PACKAGECONFIG_fsref = ""

