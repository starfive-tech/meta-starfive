SUMMARY = "Dubhe NFS Image Creator"
DESCRIPTION = "Recipe to create a NFS Image"
LICENSE = "CLOSED" 

inherit core-image

DEPENDS += "opensbi deploy-bootfiles quilt quilt-native"

LIC_FILES_CHKSUM = ""

require qspi-nfs-essential.inc

#DEPENDS = "opensbi deploy-bootfiles"
#IMAGE_INSTALL += "tools-sdk dev-pkgs"
#IMAGE_INSTALL += "packagegroup-common-essential"
#IMAGE_INSTALL += "packagegroup-network-essential"
#IMAGE_INSTALL += "packagegroup-core-ssh-openssh"

#export IMAGE_BASENAME = "${PN}"
export IMAGE_BASENAME = "nfs-rootfs"

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
	dd if=${DEPLOY_DIR_IMAGE}/bootcode_min.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=0 count=128
        dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=128 count=1
        dd if=${DEPLOY_DIR_IMAGE}/dubhe90_fpga_nfs.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=129 count=255
        dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin bs=32 seek=384

	cp ${DEPLOY_DIR_IMAGE}/starfive-dubhe-90-qspi-nfs-image.bin ${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-nfs-image.bin
        dd if=${DEPLOY_DIR_IMAGE}/dubhe80_fpga_nfs.dtb of=${DEPLOY_DIR_IMAGE}/starfive-dubhe-80-qspi-nfs-image.bin bs=32 seek=129 count=255
}

IMAGE_POSTPROCESS_COMMAND += "do_qspi;"
