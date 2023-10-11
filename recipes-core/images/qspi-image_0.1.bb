SUMMARY = "QSPI Image Creator"
DESCRIPTION = "Recipe to create a QSPI Image"
LICENSE = "CLOSED"

inherit core-image

DEPENDS = "opensbi deploy-bootfiles"

LIC_FILES_CHKSUM = ""

IMAGE_FSTYPES = "qspi"

do_rootfs[depends] += "dubhe-image-initramfs:do_rootfs"
do_image[depends] += "\
	deploy-bootfiles:do_deploy \
	opensbi:do_deploy \
	virtual/kernel:do_deploy \
	"

IMAGE_CMD:qspi () {
	dd if=${DEPLOY_DIR_IMAGE}/bootcode.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-initramfs-image.bin bs=32 seek=0 count=128
	dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-initramfs-image.bin bs=32 seek=128 count=1
	dd if=${DEPLOY_DIR_IMAGE}/dubhe90_fpga.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-initramfs-image.bin bs=32 seek=129 count=255
	dd if=${DEPLOY_DIR_IMAGE}/fw_payload_initramfs.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-initramfs-image.bin bs=32 seek=384

	dd if=${DEPLOY_DIR_IMAGE}/bootcode.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-initramfs-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-initramfs-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe80_fpga.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-initramfs-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload_initramfs.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-initramfs-image.bin bs=32 seek=384
}
