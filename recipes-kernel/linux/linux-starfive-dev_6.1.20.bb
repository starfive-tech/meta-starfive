require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive Dubhe kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.1-dubhe"
SRCREV:starfive-dubhe = "c888e193d4662be1943b79172f0f5780dab87b72"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "starfive-6.1-dev-jh8100-bmc-30Nov"
SRCREV:starfive-jh8100 = "d3c89a346ad56faa46713019ba82ade5ace25651"

LINUX_VERSION ?= "6.1.20"
LINUX_VERSION:starfive-dubhe = "6.1.20"
LINUX_VERSION:starfive-jh8100 = "6.1.20"

LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
        git://github.com/${FORK}/linux.git;protocol=https;branch=${BRANCH} \
        file://cpio.cfg \
        "

SRC_URI:starfive-jh8100 = "\
	git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH} \
	"

INITRAMFS_IMAGE_BUNDLE:starfive-dubhe = "1"
INITRAMFS_IMAGE:starfive-dubhe = "dubhe-image-initramfs"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"
KBUILD_DEFCONFIG:starfive-jh8100 = "jh8100_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-jh8100)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"
