DESCRIPTION = "An image for fusion splicer that will launch into the QtE based Application"
LICENSE = "MIT"

PV = "1.0"
PR = "r1"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SYSTEMD_DEFAULT_TARGET = "graphical.target"

IMAGE_INSTALL = "\
	packagegroup-core-boot \
	packagegroup-core-ssh-dropbear \
	util-linux-agetty \
	systemd-serialgetty \
	systemd-analyze \
	lrzsz \
	cpufrequtils \
	mtd-utils \
	strace \
	sudo \
	libwebsockets \
"
IMAGE_INSTALL_append += " \
	postgresql \
	postgresql-client \
	postgresql-contrib \
	postgresql-timezone \
	libpqxx \
 \
	libopencv-core \
	libopencv-imgproc \
 \
	qtbase qtbase-plugins qtsvg \
	qtdeclarative \
	qtdeclarative-qmlplugins \
	qtgraphicaleffects \
	qtvirtualkeyboard \
	qtquickcontrols2-qmlplugins \
	qtwebsockets \
	qtsvg-plugins \
	qtwebsockets-qmlplugins \
	qtvirtualkeyboard-qmlplugins \
	qtvirtualkeyboard-plugins \
 \
	qtdeclarative-tools \
"

# NOTE: don't use machine specific append, or will result in missing files.
IMAGE_INSTALL_append += " \
	exttools \
	haveged \
	gsl \
	fusion-splicer-svc-service \
	fusion-splicer-app-service \
"

inherit core-image
