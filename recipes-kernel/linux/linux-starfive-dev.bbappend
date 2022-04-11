FORK = "starfive-tech"
BRANCH = "starfive-5.15-dubhe"
SRCREV = "448310505e105564f4ec0ed0802bd700130ac0c6"

LINUX_VERSION = "5.15.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://git@192.168.110.45/starfive-tech/linux;protocol=ssh;branch=${BRANCH} \
	   file://defconfig \
	   "

INITRAMFS_IMAGE_BUNDLE = "1"
INITRAMFS_IMAGE = "dubhe-image-initramfs"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"
