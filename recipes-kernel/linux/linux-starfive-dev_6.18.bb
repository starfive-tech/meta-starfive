require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive Dubhe kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.18.7-dubhe"
SRCREV:starfive-dubhe = "4972342c5a74acbf316a47aac466a9cd1ed5b966"

LINUX_VERSION ?= "6.18.7"
LINUX_VERSION:starfive-dubhe = "6.18.7"

LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
    git://git@192.168.188.11:880/${FORK}/linux.git;protocol=http;branch=${BRANCH} \
    file://cpio.cfg \
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

KERNEL_FEATURES:remove:riscv32 = " ${KERNEL_FEATURES_RISCV}"
KERNEL_FEATURES:remove:riscv64 = " ${KERNEL_FEATURES_RISCV}"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_kernel_configcheck[noexec] = "1"
