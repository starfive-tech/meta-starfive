FORK = "starfive-tech"
BRANCH = "starfive-5.15-dubhe"
SRCREV = "cbb31a27ff5e0fa07b106691a9ad9851435a5a07"

LINUX_VERSION = "5.15.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
	   file://defconfig \
	   "

INITRAMFS_IMAGE_BUNDLE = "1"
INITRAMFS_IMAGE = "dubhe-image-initramfs"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"
