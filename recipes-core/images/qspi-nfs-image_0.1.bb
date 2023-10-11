SUMMARY = "Dubhe NFS Image Creator"
DESCRIPTION = "Recipe to create a NFS Image"
LICENSE = "CLOSED" 

inherit core-image

DEPENDS += "opensbi deploy-bootfiles quilt quilt-native"

LIC_FILES_CHKSUM = ""

IMAGE_FSTYPES += "qspi_nfs"

require qspi-nfs-essential.inc

export IMAGE_BASENAME = "nfs-rootfs"

# NFS workaround
#ROOTFS_POSTPROCESS_COMMAND += "nfs_rootfs ; lighttpd_rootfs ;"
#nfs_rootfs(){
#	cd ${IMAGE_ROOTFS}/lib/systemd/system/; sed -i '/Wants/a ConditionKernelCommandLine=!root=/dev/nfs' connman.service
#}

#lighttpd_rootfs(){
#	rm ${IMAGE_ROOTFS}/var/log; mkdir -p ${IMAGE_ROOTFS}/var/log; touch ${IMAGE_ROOTFS}/var/log/lighttpd
#}

do_image_qspi_nfs[depends] += "\
	deploy-bootfiles:do_deploy \
	opensbi:do_deploy \
	virtual/kernel:do_deploy \
	"

IMAGE_CMD:qspi_nfs (){
	dd if=${DEPLOY_DIR_IMAGE}/bootcode_min.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe90_fpga_nfs.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=384

	dd if=${DEPLOY_DIR_IMAGE}/bootcode_min.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-nfs-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-nfs-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe80_fpga_nfs.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-nfs-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-nfs-image.bin bs=32 seek=384
}
