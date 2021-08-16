SUMMARY = "QSPI Image Creator"
DESCRIPTION = "Recipe to create a QSPI Image"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM = ""

do_createqspiimage[depends] += " virtual/kernel:do_deploy "
do_createqspiimage[depends] += "opensbi:do_deploy"
addtask do_createqspiimage after do_deploy before do_build

do_createqspiimage(){
	dd if=${DEPLOY_DIR_IMAGE}/bootcode.bin of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=0 count=4096
	dd if=${DEPLOY_DIR_IMAGE}/bootjump.bin of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=4096 count=32
	dd if=${DEPLOY_DIR_IMAGE}/dubhe_fpga.dtb of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=4128 count=4064
	dd if=${DEPLOY_DIR_IMAGE}/fw_payload.bin of=${DEPLOY_DIR_IMAGE}/QSPI-Image.bin bs=1 seek=8192 count=134209536
}
