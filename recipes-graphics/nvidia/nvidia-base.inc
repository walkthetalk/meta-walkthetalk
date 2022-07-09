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
"

S = "${NVIDIA_SRC}/kernel"

do_unpack() {
	chmod +x ${DL_DIR}/${NVIDIA_ARCHIVE_NAME}.run
	rm -rf ${NVIDIA_SRC}
	${DL_DIR}/${NVIDIA_ARCHIVE_NAME}.run -x --target ${NVIDIA_SRC}

	cd ${NVIDIA_SRC}
	bsdtar -xf nvidia-persistenced-init.tar.bz2
}

do_unpack:append() {
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

do_make_scripts[noexec] = "1"

RDEPENDS_${PN} += " libglapi"
