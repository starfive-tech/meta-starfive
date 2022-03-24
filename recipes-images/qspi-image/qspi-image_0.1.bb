SUMMARY = "QSPI Image Creator"
DESCRIPTION = "Recipe to create a QSPI Image"
LICENSE = "CLOSED"

inherit core-image

DEPENDS = "opensbi deploy-bootfiles"

LIC_FILES_CHKSUM = ""

IMAGE_FSTYPES = "qspi"

do_rootfs[depends] += "dubhe-image-initramfs:do_rootfs"
do_image[depends] += " deploy-bootfiles:do_deploy"
do_image[depends] += " opensbi:do_deploy"
do_image[depends] += " virtual/kernel:do_deploy"

IMAGE_CMD:qspi () {
	dd if=${DEPLOY_DIR_IMAGE}/bootcode.bin of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=0 count=4096
	dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=4096 count=32
	dd if=${DEPLOY_DIR_IMAGE}/dubhe_fpga.dtb of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=4128 count=8160
	dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=12288 
}
