require sd-image.inc

DEPENDS:starfive-visionfive2 += " opensbi starfive-tool-native"

IMAGE_FEATURES += " allow-empty-password empty-root-password"

IMAGE_INSTALL += " \
	packagegroup-starfive-essentials \
	bmap-tools \
	systemd-analyze \
	e2fsprogs-resize2fs \
	parted \
        "

EXTRA_IMAGEDEPENDS:starfive-visionfive2 += " alsa-lib libgpiod i2c-tools"
