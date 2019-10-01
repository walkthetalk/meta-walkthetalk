FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

HAS_PLATFORM_INIT_append_fsref = " zynq_fsref_defconfig"

SRC_URI_append_fsref = " file://add_fsref.patch \
			 file://add_fsref_dtb_object.patch \
			"
