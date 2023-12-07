require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive VisionFive2 kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-visionfive2 = "starfive-tech"
BRANCH:starfive-visionfive2 = "JH7110_VisionFive2_devel"
SRCREV:starfive-visionfive2 = "d9eee31aaec51ade1641391836c1f07dd2151a4a"

LINUX_VERSION ?= "5.15.0"
LINUX_VERSION:starfive-visionfive2 = "5.15.0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-visionfive2 = " \
	git://github.com/${FORK}/linux.git;protocol=https;branch=${BRANCH} \
	file://0001-riscv-fix-building-external-modules.patch \
	file://vf2.cfg \
	"

INITRAMFS_IMAGE:starfive-visionfive2 = "core-image-minimal-initramfs"

KBUILD_DEFCONFIG:starfive-visionfive2 = "starfive_visionfive2_defconfig"

do_deploy:append:starfive-visionfive2 () {
	# Create FitImage
	cd ${DEPLOY_DIR_IMAGE}
	rm -rf ${DEPLOY_DIR_IMAGE}/tmp
	mkdir ${DEPLOY_DIR_IMAGE}/tmp
	cp -P ${DEPLOYDIR}/* ${DEPLOY_DIR_IMAGE}/tmp/
	mkimage -A riscv -O linux -T ramdisk -n "Initial Ram Disk" -d core-image-minimal-initramfs-starfive-visionfive2.cpio.gz initramfs.img
	mkimage -f ${DEPLOY_DIR_IMAGE}/visionfive2-fit-image.its ${DEPLOY_DIR_IMAGE}/starfiveu.fit
}

COMPATIBLE_MACHINE = "(starfive-visionfive2)"
