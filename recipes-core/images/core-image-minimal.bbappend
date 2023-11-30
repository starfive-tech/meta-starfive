require sd-image.inc

DEPENDS:starfive-visionfive2 += " opensbi starfive-tool-native"

IMAGE_FEATURES += " allow-empty-password empty-root-password"

IMAGE_INSTALL += " \
	packagegroup-starfive-essentials \
	systemd-analyze \
	e2fsprogs-resize2fs \
	parted \
        "

IMAGE_INSTALL:starfive-dubhe += "\
	packagegroup-starfive-dubhe-essentials \
	packagegroup-starfive-dubhe-ltp \
	mtd-utils \
	"

IMAGE_INSTALL:starfive-jh8100 += "\
	i3c-tools \
	"

TOOLCHAIN_TARGET_TASK += "packagegroup-starfive-toolchain"
