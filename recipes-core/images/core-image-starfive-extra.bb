SUMMARY = "A StarFive image with additional packages."

require sd-image.inc

DEPENDS += " opensbi u-boot-tools-native dtc-native"

IMAGE_INSTALL = "packagegroup-core-boot \
		${CORE_IMAGE_EXTRA_INSTALL} \
		helloworld \
        	coremark \
        	dhrystone \
        	perf \
        	gdb \
        	gdbserver \
        	util-linux \
        	ethtool \
        	bmap-tools \
        	systemd-analyze \
        	openssh \
        	e2fsprogs-resize2fs \
		ldd \
		file \
		git \
		texinfo \
		libcheck \
		packagegroup-core-buildessential \
		packagegroup-starfive-c \
		nodejs \
        	nodejs-npm \
		"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"
