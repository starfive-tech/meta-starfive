require sd-image.inc

DEPENDS += " opensbi starfive-tool-native u-boot-tools-native dtc-native"

IMAGE_INSTALL += " \
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
	"

EXTRA_IMAGEDEPENDS += " alsa-lib libgpiod i2c-tools"
