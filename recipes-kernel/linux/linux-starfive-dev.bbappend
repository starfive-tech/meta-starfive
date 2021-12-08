FORK = "starfive-tech"
BRANCH = "starfive-5.13"
SRCREV = "8cb8c83456bff1938d95b88de4b5eb248e8475d8"

LINUX_VERSION = "5.13.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/starfive-tech/linux/;protocol=https;branch=${BRANCH} \
	   file://defconfig \
	   "

INITRAMFS_IMAGE_BUNDLE = "1"
INITRAMFS_IMAGE = "dubhe-image-initramfs"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"
