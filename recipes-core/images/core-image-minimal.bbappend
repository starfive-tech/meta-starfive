require sd-image.inc

DEPENDS:starfive-visionfive2 += " opensbi starfive-tool-native"

IMAGE_FEATURES += " allow-empty-password empty-root-password"

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

EXTRA_IMAGEDEPENDS:starfive-visionfive2 += " alsa-lib libgpiod i2c-tools"
