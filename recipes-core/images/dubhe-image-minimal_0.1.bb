SUMMARY = "Dubhe Image Minimal Creator"
DESCRIPTION = "Recipe to create a Core Image"
LICENSE = "CLOSED"

inherit core-image

DEPENDS += "opensbi deploy-bootfiles quilt quilt-native"

LIC_FILES_CHKSUM = ""

IMAGE_FSTYPES += "qspi_ext4 qspi_sd"

require dubhe-image-essential.inc

export IMAGE_BASENAME = "console-image-minimal"

do_image_qspi_ext4[depends] += " \
	deploy-bootfiles:do_deploy \
	opensbi:do_deploy \
	virtual/kernel:do_deploy \
	"

IMAGE_TYPEDEP:qspi_sd = "ext4 wic wic.gz wic.bmap"

IMAGE_CMD:qspi_ext4 (){
	dd if=${DEPLOY_DIR_IMAGE}/bootcode_min.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe90_fpga_ext4.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=384

	dd if=${DEPLOY_DIR_IMAGE}/bootcode_min.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ext4-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ext4-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe80_fpga_ext4.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ext4-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ext4-image.bin bs=32 seek=384
}

IMAGE_CMD:qspi_sd (){
	cd ${IMGDEPLOYDIR}
	bmaptool copy console-image-minimal-starfive-dubhe.wic.gz ${DEPLOY_DIR_IMAGE}/SD-Image.img
}
