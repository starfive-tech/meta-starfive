SUMMARY = "QSPI Ubifs Image Creator"
DESCRIPTION = "Recipe to create a QSPI Ubifs Image"
LICENSE = "CLOSED"

inherit core-image

DEPENDS += "opensbi deploy-bootfiles quilt quilt-native"

LIC_FILES_CHKSUM = ""

IMAGE_FSTYPES = "ubi ubifs"
MKUBIFS_ARGS = "-m 1 -e 65408 -c 26876"
UBINIZE_ARGS = " -p 64KiB -m 1"

IMAGE_ROOTFS_SIZE ?= "8192"

IMAGE_FEATURES += "allow-empty-password empty-root-password"

#IMAGE_INSTALL += "mtd-utils mtd-utils-ubifs kernel-modules"
IMAGE_INSTALL += "helloworld"

IMAGE_FEATURES:remove = "dbg-pkgs"

export IMAGE_BASENAME = "qspi-ubifs"
do_qspi[depends] += " deploy-bootfiles:do_deploy"
do_qspi[depends] += " opensbi:do_deploy"
do_qspi[depends] += " virtual/kernel:do_deploy"

do_qspi () {
        dd if=${DEPLOY_DIR_IMAGE}/bootcode.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ubifs-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ubifs-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe90_fpga_ubi.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ubifs-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ubifs-image.bin bs=32 seek=384 count=1048192
	dd if=${IMGDEPLOYDIR}/qspi-ubifs-starfive-dubhe.ubi of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ubifs-image.bin bs=64KiB seek=512

	cp ${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ubifs-image.bin ${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ubifs-image.bin
        dd if=${DEPLOY_DIR_IMAGE}/dubhe80_fpga_ubi.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ubifs-image.bin bs=32 seek=129 count=255
}

IMAGE_POSTPROCESS_COMMAND += "do_qspi;"
