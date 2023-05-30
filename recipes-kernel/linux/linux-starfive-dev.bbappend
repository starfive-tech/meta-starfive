FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.1-dubhe"
SRCREV:starfive-dubhe = "0328291313ba8ad2cd6ac94df9039f2f4a365b6f"

FORK:starfive-visionfive2 = "starfive-tech"
BRANCH:starfive-visionfive2 = "JH7110_VisionFive2_devel"
SRCREV:starfive-visionfive2 = "d9eee31aaec51ade1641391836c1f07dd2151a4a"

LINUX_VERSION ?= "6.1.20"
LINUX_VERSION:starfive-dubhe = "6.1.20"
LINUX_VERSION:starfive-visionfive2 = "5.15.0"
LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
	git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH} \
	file://cpio.cfg \
	"
SRC_URI:starfive-visionfive2 = " \
	git://github.com/${FORK}/linux.git;protocol=https;branch=${BRANCH} \
	file://vf2.cfg \
	"

INITRAMFS_IMAGE_BUNDLE:starfive-dubhe = "${@oe.utils.conditional('ENABLE_INIT','1','1','',d)}"
INITRAMFS_IMAGE:starfive-dubhe = "${@oe.utils.conditional('ENABLE_INIT','1','dubhe-image-initramfs','',d)}"
INITRAMFS_IMAGE:starfive-visionfive2 = "core-image-minimal-initramfs"

# Temporary remove the patch to update the kernel, will create new patches after that
SRC_URI:append:starfive-dubhe = "${@oe.utils.conditional('ENABLE_EXT4','1','file://ext4.patch','',d)}"
SRC_URI:append:starfive-dubhe = "${@oe.utils.conditional('ENABLE_UBI','1','file://ubi.patch','',d)}"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"
KBUILD_DEFCONFIG:starfive-visionfive2 = "starfive_visionfive2_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-visionfive2)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

DEPENDS:starfive-visionfive2 += " u-boot-tools-native u-boot-starfive"

do_deploy:append:starfive-visionfive2 () {
	# Unpack and repack cpio archive to include rgx firmware for DDK1.19
	cd ${DEPLOY_DIR_IMAGE}
	rm -rf ${DEPLOY_DIR_IMAGE}/initramfs
	mkdir ${DEPLOY_DIR_IMAGE}/initramfs
	cd ${DEPLOY_DIR_IMAGE}/initramfs
	zcat ${DEPLOY_DIR_IMAGE}/core-image-minimal-initramfs-starfive-visionfive2.cpio.gz | cpio -idmv || true
	# cp -r ${DEPLOY_DIR}/lib ./
	find . | cpio -H newc -o | gzip > ${DEPLOY_DIR_IMAGE}/core-image-minimal-initramfs-starfive-visionfive2-temp.cpio.gz
	# Create FitImage
	cd ${DEPLOY_DIR_IMAGE}
	rm -rf ${DEPLOY_DIR_IMAGE}/tmp
	mkdir ${DEPLOY_DIR_IMAGE}/tmp
	cp -P ${DEPLOYDIR}/* ${DEPLOY_DIR_IMAGE}/tmp/
	mkimage -A riscv -O linux -T ramdisk -n "Initial Ram Disk" -d core-image-minimal-initramfs-starfive-visionfive2-temp.cpio.gz initramfs.img
	mkimage -f ${DEPLOY_DIR_IMAGE}/visionfive2-fit-image.its ${DEPLOY_DIR_IMAGE}/starfiveu.fit
}

do_compile[nostamp] = "1"
