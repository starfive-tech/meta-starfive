SUMMARY = "A StarFive image with additional packages."

require sd-image.inc

DEPENDS += " opensbi u-boot-tools-native dtc-native"

IMAGE_INSTALL = "\
	packagegroup-core-boot \
	${CORE_IMAGE_EXTRA_INSTALL} \
	bmap-tools \
	systemd-analyze \
	e2fsprogs-resize2fs \
	parted \
	ldd \
	file \
	git \
	texinfo \
	libcheck \
	packagegroup-core-buildessential \
	packagegroup-starfive-c \
	packagegroup-starfive-essentials \
	nodejs \
	nodejs-npm \
	"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"
