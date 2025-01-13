require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive Dubhe kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.6.63-dubhe"
SRCREV:starfive-dubhe = "aa7eaea608ab33b1f6b9ace31fe62ec582c90113"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "starfive-6.6.10-dev-external-jh8100"
SRCREV:starfive-jh8100 = "9da8129da97b9fb9fb1662475935f52e546b9385"

LINUX_VERSION ?= "6.6.10"
LINUX_VERSION:starfive-dubhe = "6.6.63"
LINUX_VERSION:starfive-jh8100 = "6.6.10"

LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
    git://github.com/${FORK}/linux.git;protocol=https;branch=${BRANCH} \
    file://cpio.cfg \
    "

SRC_URI:starfive-jh8100 = "\
    git://github.com/${FORK}/linux.git;protocol=https;branch=${BRANCH} \
    file://jh8100.cfg \
    "

do_compile:append:starfive-dubhe() {
    rm -rf ${DEPLOY_DIR_IMAGE}/kernel_fit
    install -d ${DEPLOY_DIR_IMAGE}/kernel_fit
    cp ${B}/arch/riscv/boot/dts/starfive/dubhe*_fpga.dtb* ${DEPLOY_DIR_IMAGE}/kernel_fit
    cp ${B}/arch/riscv/boot/Image ${DEPLOY_DIR_IMAGE}/kernel_fit
}

INITRAMFS_IMAGE_BUNDLE:starfive-dubhe = "1"
INITRAMFS_IMAGE:starfive-dubhe = "dubhe-image-initramfs"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"
KBUILD_DEFCONFIG:starfive-jh8100 = "jh8100_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-jh8100)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"
