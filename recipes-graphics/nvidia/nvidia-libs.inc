# Jesus fuck, this is complicated.
# Read this first: https://devtalk.nvidia.com/default/topic/915640/unix-graphics-announcements-and-news/multiple-glx-client-libraries-in-the-nvidia-linux-driver-installer-package/

PACKAGES =+ " \
	opencl-nvidia \
	nvidia-base \
	nvidia-allocator \
	nvidia-egl \
	nvidia-gbm \
	nvidia-wayland \
	nvidia-opengl \
	nvidia-openglx \
	nvidia-vulkan \
	nvidia-vdpau \
	nvidia-wine \
	nvidia-raytracing \
	nvidia-cuda \
	nvidia-utils \
	nvidia-dkms \
	nvidia-xf86 \
"

do_install:append() {
	pkgdir=${D}
	pkgver=${PV}
        srcdir=${NVIDIA_SRC}

        cd ${srcdir}

	# nvidia-opencl
	install -Dm644 nvidia.icd "${pkgdir}/etc/OpenCL/vendors/nvidia.icd"
	install -Dm755 "libnvidia-compiler.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-compiler.so.${pkgver}"
	install -Dm755 "libnvidia-opencl.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-opencl.so.${pkgver}"
	install -Dm755 "libOpenCL.so.1.0.0" "${pkgdir}/usr/lib/"

	mkdir -p "${pkgdir}/usr/share/licenses"
	ln -s nvidia-utils "${pkgdir}/usr/share/licenses/opencl-nvidia"
	
	# nvidia-dkms
	#install -dm 755 "${pkgdir}/usr/src"
	#cp -dr --no-preserve='ownership' kernel "${pkgdir}/usr/src/nvidia-${pkgver}"
	#install -Dt "${pkgdir}/usr/share/licenses/nvidia-dkms" -m644 "LICENSE"
	
	# nvidia-utils
	# X driver
	install -Dm755 nvidia_drv.so "${pkgdir}/usr/lib/xorg/modules/drivers/nvidia_drv.so"

	# Wayland/GBM
	install -Dm755 libnvidia-egl-gbm.so.1* -t "${pkgdir}/usr/lib/"
	install -Dm644 15_nvidia_gbm.json "${pkgdir}/usr/share/egl/egl_external_platform.d/15_nvidia_gbm.json"
	mkdir -p "${pkgdir}/usr/lib/gbm"
	ln -sr "${pkgdir}/usr/lib/libnvidia-allocator.so.${pkgver}" "${pkgdir}/usr/lib/gbm/nvidia-drm_gbm.so"

	# firmware
	install -Dm644 firmware/gsp.bin "${pkgdir}/usr/lib/firmware/nvidia/${pkgver}/gsp.bin"

	# GLX extension module for X
	install -Dm755 "libglxserver_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/nvidia/xorg/libglxserver_nvidia.so.${pkgver}"
	# Ensure that X finds glx
	ln -s "libglxserver_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/nvidia/xorg/libglxserver_nvidia.so.1"
	ln -s "libglxserver_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/nvidia/xorg/libglxserver_nvidia.so"

	install -Dm755 "libGLX_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/libGLX_nvidia.so.${pkgver}"

	# OpenGL libraries
	install -Dm755 "libEGL_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/libEGL_nvidia.so.${pkgver}"
	install -Dm755 "libGLESv1_CM_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/libGLESv1_CM_nvidia.so.${pkgver}"
	install -Dm755 "libGLESv2_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/libGLESv2_nvidia.so.${pkgver}"
	install -Dm644 "10_nvidia.json" "${pkgdir}/usr/share/glvnd/egl_vendor.d/10_nvidia.json"

	# OpenGL core library
	install -Dm755 "libnvidia-glcore.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-glcore.so.${pkgver}"
	install -Dm755 "libnvidia-eglcore.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-eglcore.so.${pkgver}"
	install -Dm755 "libnvidia-glsi.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-glsi.so.${pkgver}"

	# misc
	install -Dm755 "libnvidia-fbc.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-fbc.so.${pkgver}"
	install -Dm755 "libnvidia-encode.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-encode.so.${pkgver}"
	install -Dm755 "libnvidia-cfg.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-cfg.so.${pkgver}"
	install -Dm755 "libnvidia-ml.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-ml.so.${pkgver}"
	install -Dm755 "libnvidia-glvkspirv.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-glvkspirv.so.${pkgver}"
	install -Dm755 "libnvidia-allocator.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-allocator.so.${pkgver}"
	install -Dm755 "libnvidia-vulkan-producer.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-vulkan-producer.so.${pkgver}"
	# Sigh libnvidia-vulkan-producer.so has no SONAME set so create_links doesn't catch it. NVIDIA please fix!
	ln -s "libnvidia-vulkan-producer.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-vulkan-producer.so.1"
	ln -s "libnvidia-vulkan-producer.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-vulkan-producer.so"

	# Vulkan ICD
	install -Dm644 "nvidia_icd.json" "${pkgdir}/usr/share/vulkan/icd.d/nvidia_icd.json"
	install -Dm644 "nvidia_layers.json" "${pkgdir}/usr/share/vulkan/implicit_layer.d/nvidia_layers.json"

	# VDPAU
	install -Dm755 "libvdpau_nvidia.so.${pkgver}" "${pkgdir}/usr/lib/vdpau/libvdpau_nvidia.so.${pkgver}"

	# nvidia-tls library
	install -Dm755 "libnvidia-tls.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-tls.so.${pkgver}"

	# CUDA
	install -Dm755 "libcuda.so.${pkgver}" "${pkgdir}/usr/lib/libcuda.so.${pkgver}"
	install -Dm755 "libnvcuvid.so.${pkgver}" "${pkgdir}/usr/lib/libnvcuvid.so.${pkgver}"

	# PTX JIT Compiler (Parallel Thread Execution (PTX) is a pseudo-assembly language for CUDA)
	install -Dm755 "libnvidia-ptxjitcompiler.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-ptxjitcompiler.so.${pkgver}"

	# raytracing
	install -Dm755 "libnvoptix.so.${pkgver}" "${pkgdir}/usr/lib/libnvoptix.so.${pkgver}"
	install -Dm755 "libnvidia-rtcore.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-rtcore.so.${pkgver}"

	# NGX
	install -Dm755 nvidia-ngx-updater "${pkgdir}/usr/bin/nvidia-ngx-updater"
	install -Dm755 "libnvidia-ngx.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-ngx.so.${pkgver}"
	install -Dm755 _nvngx.dll "${pkgdir}/usr/lib/nvidia/wine/_nvngx.dll"
	install -Dm755 nvngx.dll "${pkgdir}/usr/lib/nvidia/wine/nvngx.dll"

	# Optical flow
	install -Dm755 "libnvidia-opticalflow.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-opticalflow.so.${pkgver}"

	# DEBUG
	install -Dm755 nvidia-debugdump "${pkgdir}/usr/bin/nvidia-debugdump"

	# nvidia-xconfig
	install -Dm755 nvidia-xconfig "${pkgdir}/usr/bin/nvidia-xconfig"
	install -Dm644 nvidia-xconfig.1.gz "${pkgdir}/usr/share/man/man1/nvidia-xconfig.1.gz"

	# nvidia-bug-report
	install -Dm755 nvidia-bug-report.sh "${pkgdir}/usr/bin/nvidia-bug-report.sh"

	# nvidia-smi
	install -Dm755 nvidia-smi "${pkgdir}/usr/bin/nvidia-smi"
	install -Dm644 nvidia-smi.1.gz "${pkgdir}/usr/share/man/man1/nvidia-smi.1.gz"

	# nvidia-cuda-mps
	install -Dm755 nvidia-cuda-mps-server "${pkgdir}/usr/bin/nvidia-cuda-mps-server"
	install -Dm755 nvidia-cuda-mps-control "${pkgdir}/usr/bin/nvidia-cuda-mps-control"
	install -Dm644 nvidia-cuda-mps-control.1.gz "${pkgdir}/usr/share/man/man1/nvidia-cuda-mps-control.1.gz"

	# nvidia-modprobe
	# This should be removed if nvidia fixed their uvm module!
	install -Dm4755 nvidia-modprobe "${pkgdir}/usr/bin/nvidia-modprobe"
	install -Dm644 nvidia-modprobe.1.gz "${pkgdir}/usr/share/man/man1/nvidia-modprobe.1.gz"

	# nvidia-persistenced
	install -Dm755 nvidia-persistenced "${pkgdir}/usr/bin/nvidia-persistenced"
	install -Dm644 nvidia-persistenced.1.gz "${pkgdir}/usr/share/man/man1/nvidia-persistenced.1.gz"
	install -Dm644 nvidia-persistenced-init/systemd/nvidia-persistenced.service.template "${pkgdir}/usr/lib/systemd/system/nvidia-persistenced.service"
	sed -i 's/__USER__/nvidia-persistenced/' "${pkgdir}/usr/lib/systemd/system/nvidia-persistenced.service"
	
	# application profiles
	install -Dm644 nvidia-application-profiles-${pkgver}-rc "${pkgdir}/usr/share/nvidia/nvidia-application-profiles-${pkgver}-rc"
	install -Dm644 nvidia-application-profiles-${pkgver}-key-documentation "${pkgdir}/usr/share/nvidia/nvidia-application-profiles-${pkgver}-key-documentation"

	install -Dm644 LICENSE "${pkgdir}/usr/share/licenses/nvidia-utils/LICENSE"
	install -Dm644 README.txt "${pkgdir}/usr/share/doc/nvidia/README"
	install -Dm644 NVIDIA_Changelog "${pkgdir}/usr/share/doc/nvidia/NVIDIA_Changelog"
	cp -r html "${pkgdir}/usr/share/doc/nvidia/"
	ln -s nvidia "${pkgdir}/usr/share/doc/nvidia-utils"

	# new power management support
	install -Dm644 systemd/system/*.service -t "${pkgdir}/usr/lib/systemd/system"
	install -Dm755 systemd/system-sleep/nvidia "${pkgdir}/usr/lib/systemd/system-sleep/nvidia"
	install -Dm755 systemd/nvidia-sleep.sh "${pkgdir}/usr/bin/nvidia-sleep.sh"
	install -Dm755 nvidia-powerd "${pkgdir}/usr/bin/nvidia-powerd"

	# Not installing DBUS file for the time beimg, see https://bugs.archlinux.org/task/74894
	#install -Dm644 nvidia-dbus.conf "${pkgdir}"/usr/share/dbus-1/system.d/nvidia-dbus.conf

	# distro specific files must be installed in /usr/share/X11/xorg.conf.d
	install -Dm644 "${WORKDIR}/nvidia-drm-outputclass.conf" "${pkgdir}/usr/share/X11/xorg.conf.d/10-nvidia-drm-outputclass.conf"

	install -Dm644 "${WORKDIR}/nvidia-utils.sysusers" "${pkgdir}/usr/lib/sysusers.d/nvidia-utils.conf"

	install -Dm644 "${WORKDIR}/nvidia.rules" "$pkgdir"/usr/lib/udev/rules.d/60-nvidia.rules

	install -Dm644 "${WORKDIR}/modprobe.conf" "${pkgdir}/usr/lib/modprobe.d/nvidia-utils.conf"
	install -Dm644 "${WORKDIR}/modules-load.conf" "${pkgdir}/usr/lib/modules-load.d/nvidia-utils.conf"

	################################# extra contents #############################################
	# CUDA/OPENCL
	#install -Dm755 "libnvidia-nvvm.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-nvvm.so.${pkgver}"

	# Wayland
	#install -Dm644 10_nvidia_wayland.json "${pkgdir}/usr/share/egl/egl_external_platform.d/10_nvidia_wayland.json"
	#install -Dm755 libnvidia-egl-wayland.so.1* "${pkgdir}/usr/lib/"

	# GTK2
	#install -Dm755 "libnvidia-gtk2.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-gtk2.so.${pkgver}"
	# GTK3
	#install -Dm755 "libnvidia-gtk3.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-gtk3.so.${pkgver}"

	# nvidia-installer
	#install -Dm755 nvidia-installer "${pkgdir}/usr/bin/nvidia-installer"
	#install -Dm644 nvidia-installer.1.gz ${pkgdir}/usr/share/man/man1/nvidia-installer.1.gz
	
	# nvidia-settings
	#install -Dm755 nvidia-settings "${pkgdir}/usr/bin/nvidia-settings"
	#install -Dm644 nvidia-settings.1.gz ${pkgdir}/usr/share/man/man1/nvidia-settings.1.gz
	#install -Dm644 nvidia-settings.png ${pkgdir}/usr/share/pixmaps/nvidia/

	# create symlinks
	find "$pkgdir" -type f -name '*.so*' ! -path '*xorg/*' -print0 | while read -d $'\0' _lib; do
		_soname=$(dirname "${_lib}")/$(readelf -d "${_lib}" | grep -Po 'SONAME.*: \[\K[^]]*' || true)
		_base=$(echo ${_soname} | sed -r 's/(.*)\.so.*/\1.so/')
		[[ -e "${_soname}" ]] || ln -s $(basename "${_lib}") "${_soname}"
		[[ -e "${_base}" ]] || ln -s $(basename "${_soname}") "${_base}"
	done
}

FILES:nvidia-base += " \
	${libdir}/libnvidia-tls.so.* \
	${libdir}/libnvidia-glsi.so.* \
"
INSANE_SKIP:nvidia-base += "ldflags"

FILES:nvidia-allocator += " \
	${libdir}/libnvidia-allocator.so.* \
"
INSANE_SKIP:nvidia-allocator += "ldflags"

FILES:opencl-nvidia += " \
	${libdir}/libnvidia-compiler.so.* \
	${libdir}/libnvidia-opencl.so.* \
	${libdir}/libOpenCL.so.* \
	${sysconfdir}/OpenCL/vendors/nvidia.icd \
	${datadir}/licenses/opencl-nvidia \
"
INSANE_SKIP:opencl-nvidia += "ldflags"

FILES:nvidia-egl += " \
	${libdir}/libEGL_nvidia.so.* \
	${libdir}/libGLESv1_CM_nvidia.so.* \
	${libdir}/libGLESv2_nvidia.so.* \
	${libdir}/libnvidia-eglcore.so.* \
	${datadir}/glvnd/egl_vendor.d/10_nvidia.json \
"
INSANE_SKIP:nvidia-egl += "ldflags"
RDEPENDS:nvidia-egl += "nvidia-base"
#RDEPENDS:nvidia-egl += "libglvnd"

FILES:nvidia-gbm += " \
	${libdir}/libnvidia-egl-gbm.so.* \
	${libdir}/gbm \
	${datadir}/egl/egl_external_platform.d/15_nvidia_gbm.json \
"
INSANE_SKIP:nvidia-gbm += "ldflags dev-so"
#TODO: drm gbm
RDEPENDS:nvidia-gbm += "nvidia-allocator"

FILES:nvidia-wayland += " \
	${libdir}/libnvidia-egl-wayland.so.* \
	${datadir}/egl/egl_external_platform.d/10_nvidia_wayland.json \
"
INSANE_SKIP:nvidia-wayland += "ldflags"
#RDEPENDS:nvidia-wayland += "wayland"

FILES:nvidia-opengl += " \
	${libdir}/libnvidia-glcore.so.* \
"
INSANE_SKIP:nvidia-opengl += "ldflags"
RDEPENDS:nvidia-opengl += "nvidia-base"

FILES:nvidia-openglx += " \
	${libdir}/libGLX_nvidia.so* \
"
INSANE_SKIP:nvidia-openglx += "ldflags dev-so"
#TODO: X11 Xext
RDEPENDS:nvidia-openglx += "nvidia-opengl"

FILES:nvidia-vulkan += " \
	${datadir}/vulkan/icd.d \
	${datadir}/vulkan/implicit_layer.d \
"
RDEPENDS:nvidia-vulkan += "nvidia-openglx"

FILES:nvidia-vdpau += " \
	${libdir}/vdpau/libvdpau_nvidia.so* \
"
INSANE_SKIP:nvidia-vdpau += "ldflags dev-so"
# TODO: X11 Xext
RDEPENDS:nvidia-vdpau += ""

FILES:nvidia-wine += " \
	${libdir}/nvidia/wine/ \
"

FILES:nvidia-raytracing += " \
	${libdir}/libnvoptix.so* \
	${libdir}/libnvidia-rtcore.so* \
"
INSANE_SKIP:nvidia-raytracing += "ldflags dev-so"

FILES:nvidia-cuda += " \
	${libdir}/libcuda.so.* \
	${libdir}/libnvcuvid.so.* \
	${libdir}/libnvidia-ptxjitcompiler.so.* \
"
INSANE_SKIP:nvidia-cuda += "ldflags"

FILES:nvidia-dkms += " \
	${srcdir}/nvidia-${PV} \
	${datadir}/licenses/nvidia-dkms \
	${libdir}/mod \
"

FILES:nvidia-xf86 += " \
	${libdir}/nvidia/xorg \
	${libdir}/xorg/modules/drivers \
	${datadir}/X11/xorg.conf.d \
"
INSANE_SKIP:nvidia-xf86 += "ldflags dev-so"
RDEPENDS:nvidia-xf86 += "nvidia-base nvidia-opengl"

FILES:nvidia-utils += " \
	${libdir}/modprobe.d \
	${libdir}/modules-load.d \
	${libdir}/sysusers.d \
	${libdir}/udev \
	${libdir}/firmware/nvidia/${PV} \
	${libdir}/*.so.* \
	${bindir} \
	${datadir}/man/man1 \
	${libdir}/systemd/system \
	${libdir}/systemd/system-sleep \
	${datadir}/nvidia/ \
	${datadir}/licenses/nvidia-utils \
	${datadir}/doc/nvidia \
	 \
	${mandir} \
"

#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
#INHIBIT_PACKAGE_STRIP = "1"

# Package nvidia-utils contains Xorg driver (nvidia_drv.so) but no xorg-abi- dependencies [xorg-driver-abi]

INSANE_SKIP:${PN} += "already-stripped"
# dev-so '/usr/lib/nvidia/xorg/libglxserver_nvidia.so'
#        '/usr/lib/vdpau/libvdpau_nvidia.so'
#        '/usr/lib/gbm/nvidia-drm_gbm.so'
# arch   '/usr/lib/firmware/nvidia/515.57/gsp.bin'
INSANE_SKIP:nvidia-utils += "ldflags dev-so arch"
INSANE_SKIP:opencl-nvidia += "ldflags"
