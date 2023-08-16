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

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "starfive-6.1-dev-jh8100-bmc"
SRCREV:starfive-jh8100 ="602adfee5440add362c13535b9f7d4d2647e2526"

LINUX_VERSION ?= "6.1.20"
LINUX_VERSION:starfive-dubhe = "6.1.20"
LINUX_VERSION:starfive-jh8100 = "6.1.20"

LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
        git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH} \
        file://cpio.cfg \
        ${@oe.utils.conditional('ENABLE_EXT4','1','file://ext4.patch','',d)} \
        ${@oe.utils.conditional('ENABLE_UBI','1','file://ubi.patch','',d)} \
        ${@oe.utils.conditional('ENABLE_NFS','1','file://nfs.patch','',d)} \
        "
SRC_URI:starfive-jh8100 = "git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH}"


INITRAMFS_IMAGE_BUNDLE:starfive-dubhe = "${@oe.utils.conditional('ENABLE_INIT','1','1','',d)}"
INITRAMFS_IMAGE:starfive-dubhe = "${@oe.utils.conditional('ENABLE_INIT','1','dubhe-image-initramfs','',d)}"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"
KBUILD_DEFCONFIG:starfive-jh8100 = "jh8100_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-jh8100)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_compile[nostamp] = "1"
