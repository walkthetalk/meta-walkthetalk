SUMMARY = "NVidia Binary Graphics Driver"
LICENSE = "NVIDIA-Proprietary"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=2cc00be68c1227a7c42ff3620ef75d05"

NVIDIA_ARCHIVE_NAME = "NVIDIA-Linux-${TARGET_ARCH}-${PV}"
NVIDIA_SRC = "${WORKDIR}/${NVIDIA_ARCHIVE_NAME}"

SRC_URI = " \
	https://us.download.nvidia.com/XFree86/Linux-${TARGET_ARCH}/${PV}/${NVIDIA_ARCHIVE_NAME}.run \
	file://nvidia-drm-outputclass.conf \
	file://nvidia-utils.sysusers \
	file://nvidia.rules \
	file://modprobe.conf \
	file://modules-load.conf \
"

S = "${NVIDIA_SRC}/kernel"

do_unpack_custom () {
	RUNSRC="${WORKDIR}/${NVIDIA_ARCHIVE_NAME}.run"
	if [[ -x "${RUNSRC}" ]]; then
		# maybe is a link whose source located in the folder not writable
		echo "already executable..."
	else
		chmod +x "${RUNSRC}"
	fi
	rm -rf ${NVIDIA_SRC}
	"${RUNSRC}" -x --target ${NVIDIA_SRC}

	cd ${NVIDIA_SRC}
	tar -xf nvidia-persistenced-init.tar.bz2
}

do_patch_custom () {
	cd ${S}
	sed -i "s/__VERSION_STRING/${PV}/" dkms.conf
	sed -i 's/__JOBS/`nproc`/' dkms.conf
	sed -i 's/__DKMS_MODULES//' dkms.conf
	sed -i '$iBUILT_MODULE_NAME[0]="nvidia"\
DEST_MODULE_LOCATION[0]="/kernel/drivers/video"\
BUILT_MODULE_NAME[1]="nvidia-uvm"\
DEST_MODULE_LOCATION[1]="/kernel/drivers/video"\
BUILT_MODULE_NAME[2]="nvidia-modeset"\
DEST_MODULE_LOCATION[2]="/kernel/drivers/video"\
BUILT_MODULE_NAME[3]="nvidia-drm"\
DEST_MODULE_LOCATION[3]="/kernel/drivers/video"\
BUILT_MODULE_NAME[4]="nvidia-peermem"\
DEST_MODULE_LOCATION[4]="/kernel/drivers/video"' dkms.conf

	# Gift for linux-rt guys
	sed -i 's/NV_EXCLUDE_BUILD_MODULES/IGNORE_PREEMPT_RT_PRESENCE=1 NV_EXCLUDE_BUILD_MODULES/' dkms.conf
}

do_unpack:append() {
    bb.build.exec_func("do_unpack_custom", d)
}
do_patch:prepend() {
    bb.build.exec_func("do_patch_custom", d)
}

#do_make_scripts[noexec] = "1"

RDEPENDS_${PN} += " libglapi"
