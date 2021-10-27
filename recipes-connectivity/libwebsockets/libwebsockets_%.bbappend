FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI += " file://fix_uninitialized_buf.patch"

PACKAGECONFIG[externalpoll] = "-DLWS_WITH_EXTERNAL_POLL=ON,-DLWS_WITH_EXTERNAL_POLL=OFF,"
