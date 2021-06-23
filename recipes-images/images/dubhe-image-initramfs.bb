DESCRIPTION = "Small image capable of booting a device with no flash."

# use -testfs live-install scripts
#PACKAGE_INSTALL = "initramfs-live-boot initramfs-live-install-testfs initramfs-live-install-efi-testfs busybox udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"
#PACKAGE_INSTALL = "initramfs-debug busybox udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"
#PACKAGE_INSTALL = "busybox udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = "empty-root-password allow-empty-password"
IMAGE_FEATURES += "tools-sdk dev-pkgs"

#IMAGE_INSTALL = " packagegroup-core-boot packagegroup-core-full-cmdline"

export IMAGE_BASENAME = "dubhe-image-initramfs"
IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

BAD_RECOMMENDATIONS += "busybox-syslog"

# Use the same restriction as initramfs-live-install-testfs
#COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
