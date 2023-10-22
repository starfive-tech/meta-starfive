SUMMARY = "QSPI Image Creator"
DESCRIPTION = "Recipe to create a QSPI Image"
LICENSE = "CLOSED"

inherit core-image

DEPENDS = "deploy-bootfiles"

LIC_FILES_CHKSUM = ""

IMAGE_FSTYPES = "qspi"

do_rootfs[depends] += "dubhe-image-initramfs:do_rootfs"

do_image[depends] += "\
	deploy-bootfiles:do_deploy \
	u-boot-starfive:do_deploy \
	"

IMAGE_CMD:qspi(){
	dd if=${DEPLOY_DIR_IMAGE}/bootcode.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-qspi-tftpboot.bin bs=32 seek=0 count=128
	dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-qspi-tftpboot.bin bs=32 seek=128 count=128
	dd if=${DEPLOY_DIR_IMAGE}/u-boot-spl.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-qspi-tftpboot.bin bs=32 seek=256 count=8192
	dd if=${DEPLOY_DIR_IMAGE}/u-boot.itb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-qspi-tftpboot.bin bs=32 seek=8448 count=32768
}
