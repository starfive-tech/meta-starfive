# Image Root Filesystem Configuration

IMAGE_FSTYPES += "ext4 wic wic.gz wic.bmap"

IMAGE_ROOTFS_SIZE ?= "8192"
#IMAGE_ROOTFS_EXTRA_SPACE = "0"

IMAGE_FEATURES += "allow-empty-password empty-root-password"
IMAGE_FEATURES:remove = "dbg-pkgs"

IMAGE_INSTALL += "\
	packagegroup-starfive-essentials \
	packagegroup-starfive-dubhe-essentials \
	mtd-utils \
	btrfs-tools \
	parted \
	lrzsz \
	devmem2 \
	e2fsprogs \
	"

TOOLCHAIN_TARGET_TASK += "packagegroup-starfive-toolchain"
