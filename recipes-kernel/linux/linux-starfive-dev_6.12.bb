require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive Dubhe kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.12.15-dubhe"
SRCREV:starfive-dubhe = "0c28bd15d4849bf172a714d40af0725be3e2ceca"

LINUX_VERSION ?= "6.12.15"
LINUX_VERSION:starfive-dubhe = "6.12.15"

LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
    git://github.com/${FORK}/linux.git;protocol=https;branch=${BRANCH} \
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
