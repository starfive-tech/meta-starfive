SUMMARY = "Dubhe Image Minimal Creator"
DESCRIPTION = "Recipe to create a Core Image"
LICENSE = "CLOSED"

inherit core-image

DEPENDS += "opensbi deploy-bootfiles quilt quilt-native"

LIC_FILES_CHKSUM = ""

require dubhe-image-essential.inc

#DEPENDS = "opensbi deploy-bootfiles"
#IMAGE_INSTALL += "tools-sdk dev-pkgs"
#IMAGE_INSTALL += "packagegroup-common-essential"
#IMAGE_INSTALL += "packagegroup-network-essential"
#IMAGE_INSTALL += "packagegroup-core-ssh-openssh"

#export IMAGE_BASENAME = "${PN}"
export IMAGE_BASENAME = "console-image-minimal"

# NFS workaround
#ROOTFS_POSTPROCESS_COMMAND += "nfs_rootfs ; lighttpd_rootfs ;"
#nfs_rootfs(){
#	cd ${IMAGE_ROOTFS}/lib/systemd/system/; sed -i '/Wants/a ConditionKernelCommandLine=!root=/dev/nfs' connman.service
#}

#lighttpd_rootfs(){
#	rm ${IMAGE_ROOTFS}/var/log; mkdir -p ${IMAGE_ROOTFS}/var/log; touch ${IMAGE_ROOTFS}/var/log/lighttpd
#}

do_qspi[depends] += " deploy-bootfiles:do_deploy"
do_qspi[depends] += " opensbi:do_deploy"
do_qspi[depends] += " virtual/kernel:do_deploy"

do_qspi (){
	dd if=${DEPLOY_DIR_IMAGE}/bootcode_min.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe90_fpga_ext4.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin bs=32 seek=384

	cp ${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-ext4-image.bin ${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ext4-image.bin
        dd if=${DEPLOY_DIR_IMAGE}/dubhe80_fpga_ext4.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-ext4-image.bin bs=32 seek=129 count=255
}

do_sd (){
	cd ${IMGDEPLOYDIR}
	bmaptool copy console-image-minimal-starfive-dubhe.wic.gz ${DEPLOY_DIR_IMAGE}/SD-Image.img
}

IMAGE_POSTPROCESS_COMMAND += "do_qspi;do_sd;"
