# Jesus fuck, this is complicated.
# Read this first: https://devtalk.nvidia.com/default/topic/915640/unix-graphics-announcements-and-news/multiple-glx-client-libraries-in-the-nvidia-linux-driver-installer-package/

PROVIDES += "opencl-nvidia nvidia-utils nvidia-dkms"

do_install:append() {
	pkgdir=${D}
	pkgver=${PV}

	cd ${NVIDIA_SRC}

	# nvidia-opencl
	install -Dm644 nvidia.icd "${pkgdir}/etc/OpenCL/vendors/nvidia.icd"
	install -Dm755 "libnvidia-compiler.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-compiler.so.${pkgver}"
	install -Dm755 "libnvidia-opencl.so.${pkgver}" "${pkgdir}/usr/lib/libnvidia-opencl.so.${pkgver}"
	install -Dm755 "libOpenCL.so.1.0.0" "${pkgdir}/usr/lib/"

	mkdir -p "${pkgdir}/usr/share/licenses"
	ln -s nvidia-utils "${pkgdir}/usr/share/licenses/opencl-nvidia"
	
	# nvidia-dkms
	install -dm 755 "${pkgdir}/usr/src"
	cp -dr --no-preserve='ownership' kernel "${pkgdir}/usr/src/nvidia-${pkgver}"
	install -Dt "${pkgdir}/usr/share/licenses/nvidia-dkms" -m644 "LICENSE"
	
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
	install -Dm644 "${srcdir}/nvidia-drm-outputclass.conf" "${pkgdir}/usr/share/X11/xorg.conf.d/10-nvidia-drm-outputclass.conf"

	install -Dm644 "${srcdir}/nvidia-utils.sysusers" "${pkgdir}/usr/lib/sysusers.d/$pkgname.conf"

	install -Dm644 "${srcdir}/nvidia.rules" "$pkgdir"/usr/lib/udev/rules.d/60-nvidia.rules

	echo "blacklist nouveau" | install -Dm644 /dev/stdin "${pkgdir}/usr/lib/modprobe.d/${pkgname}.conf"
	echo "nvidia-uvm" | install -Dm644 /dev/stdin "${pkgdir}/usr/lib/modules-load.d/${pkgname}.conf"

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

do_make_scripts[noexec] = "1"

FILES_opencl-nvidia += " \
	${libdir}/libnvidia-compiler.so.* \
	${libdir}/libnvidia-opencl.so.* \
	${sysconfdir}/OpenCL/vendors/nvidia.icd \
	${datadir}/licenses/opencl-nvidia \
"

FILES_nvidia-dkms += " \
	${srcdir}/nvidia-${PV} \
	${datadir}/licenses/nvidia-dkms \
"

FILES_nvidia-utils += " \
	${libdir}/xorg/modules/drivers \
	${datadir}/egl/egl_external_platform.d/ \
	${libdir}/gbm \
	${libdir}/firmware/nvidia/${PV} \
	${libdir}/nvidia/xorg \
	${libdir}/*.so* \
	${datadir}/glvnd/egl_vendor.d/ \
	${datadir}/vulkan/icd.d \
	${datadir}/vulkan/implicit_layer.d \
	${libdir}/vdpau \
	${libdir}/nvidia/wine/ \
	${bindir} \
	${datadir}/man/man1 \
	${libdir}/systemd/system \
	${libdir}/systemd/system-sleep \
	${datadir}/nvidia/ \
	${datadir}/licenses/nvidia-utils \
	${datadir}/doc/nvidia \
	 \
	${libdir}/xorg/modules/extensions/ \
	${mandir} \
	/lib64 \
"

RDEPENDS_nvidia-utils += " \
	libglvnd \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}:append = "ldflags already-stripped"
INSANE_SKIP_libgl-nvidia:append = "ldflags"
