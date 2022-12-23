FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

HAS_PLATFORM_INIT:append:fsref = " zynq_fsref_defconfig"

SRC_URI:append = " file://ensure_dma_for_spl_load_file.patch \
		"
SRC_URI:append:fsref = " file://add_fsref.patch \
			 file://add_fsref_dtb_object.patch \
			 file://spl_qspi_booting.patch \
			"

SRC_URI:append:ax7021 = " file://add_ax7021.patch \
			  file://add_ax7021_dtb_object.patch \
			"
