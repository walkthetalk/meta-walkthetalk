FILESEXTRAPATHS:prepend := "${THISDIR}/qemu:"

SRC_URI:append = " file://fix_for_ebpf.patch \
		"
