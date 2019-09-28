#FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
#SRC_URI += "\
#            file://oe-device-extra.pri \
#"
#do_configure_prepend() {
#    cp ${WORKDIR}/oe-device-extra.pri ${S}/mkspecs/oe-device-extra.pri
#}


PACKAGECONFIG_append_kl360 = " \
	linuxfb accessibility sql-sqlite sql-psql no-opengl \
"
PACKAGECONFIG_append_fsref = " \
	linuxfb accessibility sql-sqlite sql-psql no-opengl \
"
