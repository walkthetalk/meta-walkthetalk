FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append_fsref = " file://add_fsref.patch \
			 file://add_fsref_dtb_object.patch \
			"
