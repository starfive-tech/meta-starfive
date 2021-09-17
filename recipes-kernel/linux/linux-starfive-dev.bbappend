FORK = "starfive-tech"
BRANCH = "starfive-5.13"

LINUX_VERSION ?= "5.13.0"
LINUX_VERSION_EXTENSION_append = "-starlight"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/starfive-tech/linux.git;protocol=git;branch=${BRANCH}"

INITRAMFS_IMAGE_BUNDLE = "1"

SRC_URI_append := " \
    file://defconfig \
    "

#INITRAMFS_IMAGE = "riscv-initramfs-image"
INITRAMFS_IMAGE = "dubhe-image-initramfs"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"
