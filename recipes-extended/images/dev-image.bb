DESCRIPTION = "An image for fusion splicer that will launch into the Qt based Application"
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
	i2c-tools \
	evtest \
	mtd-utils \
	dosfstools \
	e2fsprogs-mke2fs \
	e2fsprogs-resize2fs \
	e2fsprogs-tune2fs \
	strace \
	sudo \
	libwebsockets \
"
IMAGE_INSTALL += " \
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
	qtvirtualkeyboard \
	qtwebsockets \
	qtsvg-plugins \
	qtwebsockets-qmlplugins \
	qtvirtualkeyboard-qmlplugins \
	qtvirtualkeyboard-plugins \
 \
	qtdeclarative-tools \
"

# NOTE: don't use machine specific append, or will result in missing files.
IMAGE_INSTALL += " \
	exttools \
	haveged \
	gsl \
	fusion-splicer-svc \
	fusion-splicer-app \
"

inherit core-image
