require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive Dubhe kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.1-dubhe"
SRCREV:starfive-dubhe = "0a9c9aa4208378d4da0cf13c6c5a3a92da370b26"

LINUX_VERSION ?= "6.1.20"
LINUX_VERSION:starfive-dubhe = "6.1.20"
LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
        git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH} \
        file://cpio.cfg \
        ${@oe.utils.conditional('ENABLE_EXT4','1','file://ext4.patch','',d)} \
        ${@oe.utils.conditional('ENABLE_UBI','1','file://ubi.patch','',d)} \
        ${@oe.utils.conditional('ENABLE_NFS','1','file://nfs.patch','',d)} \
        "

INITRAMFS_IMAGE_BUNDLE:starfive-dubhe = "${@oe.utils.conditional('ENABLE_INIT','1','1','',d)}"
INITRAMFS_IMAGE:starfive-dubhe = "${@oe.utils.conditional('ENABLE_INIT','1','dubhe-image-initramfs','',d)}"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_compile[nostamp] = "1"
