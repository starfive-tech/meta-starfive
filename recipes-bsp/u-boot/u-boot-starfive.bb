require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit uboot-extlinux-config

LIC_FILES_CHKSUM:starfive-dubhe = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
LIC_FILES_CHKSUM:starfive-visionfive2 = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"
LIC_FILES_CHKSUM:starfive-jh8100 = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "dubhe_fpga_dev_v2023.10"
SRCREV:starfive-dubhe = "88c4bf23b45f807c9c4173ef24dc84c3e75b67bc"

FORK:starfive-visionfive2 = "starfive-tech"
BRANCH:starfive-visionfive2 = "JH7110_VisionFive2_devel"
SRCREV:starfive-visionfive2 = "b6e2b0e85c774a18ae668223a6e5f7d335895243"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "jh8100_fpga_dev_v2023.01-bmc-30Nov"
SRCREV:starfive-jh8100 = "c1a4c841e01fbcd3f6d5e894e726e9f50abc6938"

SRC_URI:starfive-dubhe = "\
	git://git@192.168.110.45/${FORK}/u-boot.git;protocol=ssh;branch=${BRANCH} \
	file://run_qemu_virt.dtb \
	file://0001-include-configs-starfive-dubhe-fpga.h-Mask-ttyS0-and.patch \
	"

SRC_URI:starfive-visionfive2 = "\
	git://github.com/${FORK}/u-boot.git;protocol=ssh;branch=${BRANCH} \
	file://vf2_uEnv.txt \
	file://vf2_nvme_uEnv.txt \
	file://tftp-mmc-boot.txt \
	file://visionfive2-fit-image.its \
	file://uboot_disable_logo.patch \
	"

SRC_URI:starfive-jh8100 = "\
	git://git@192.168.110.45/${FORK}/u-boot.git;protocol=ssh;branch=${BRANCH} \
	file://tftp-mmc-boot.txt \
	file://run_qemu_virt.dtb \
	file://bootloader.bin.normal.out \
	file://firmware_merak.bin.normal.out \
	file://uboot.env \
	"

DEPENDS:append:starfive-dubhe = " u-boot-tools-native bmap-tools-native opensbi"
DEPENDS:append:starfive-visionfive2 = " u-boot-tools-native starfive-tool-native"
DEPENDS:append:starfive-jh8100 = " u-boot-tools-native bmap-tools-native opensbi starfive-tool"

# Overwrite this for your server
TFTP_SERVER_IP ?= "127.0.0.1"

do_configure:prepend:starfive-visionfive2() {
    sed -i -e 's,@SERVERIP@,${TFTP_SERVER_IP},g' ${WORKDIR}/tftp-mmc-boot.txt
    mkimage -O linux -T script -C none -n "U-Boot boot script" \
	-d ${WORKDIR}/tftp-mmc-boot.txt ${WORKDIR}/${UBOOT_ENV_BINARY}
}

do_configure:prepend:starfive-jh8100() {
    sed -i -e 's,@SERVERIP@,${TFTP_SERVER_IP},g' ${WORKDIR}/tftp-mmc-boot.txt
    mkimage -O linux -T script -C none -n "U-Boot boot script" \
        -d ${WORKDIR}/tftp-mmc-boot.txt ${WORKDIR}/${UBOOT_ENV_BINARY}
}

do_configure:prepend:starfive-dubhe() {
    cp ${DEPLOY_DIR_IMAGE}/fw_dynamic.bin ${WORKDIR}/build/fw_dynamic.bin
    export OPENSBI=${WORKDIR}/build/fw_dynamic.bin
}

do_configure:prepend:starfive-jh8100() {
    cp ${DEPLOY_DIR_IMAGE}/fw_dynamic.bin ${WORKDIR}/build/fw_dynamic.bin
    export OPENSBI=${WORKDIR}/build/fw_dynamic.bin
}

do_deploy:append:starfive-visionfive2() {
    install -m 644 ${WORKDIR}/vf2_nvme_uEnv.txt ${DEPLOYDIR}/vf2_nvme_uEnv.txt
    install -m 644 ${WORKDIR}/vf2_uEnv.txt ${DEPLOYDIR}/vf2_uEnv.txt
    install -m 644 ${WORKDIR}/visionfive2-fit-image.its ${DEPLOYDIR}/visionfive2-fit-image.its
    spl_tool -c -f ${DEPLOYDIR}/${SPL_IMAGE}
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_SYMLINK}.normal.out
}

do_deploy:append:starfive-dubhe() {
    install -m 644 ${WORKDIR}/build/u-boot.itb ${DEPLOYDIR}/u-boot.itb
    rm ${WORKDIR}/build/fw_dynamic.bin
    install -m 644 ${WORKDIR}/run_qemu_virt.dtb ${DEPLOYDIR}/run_qemu_virt.dtb
}

do_deploy:append:starfive-jh8100() {
    install -m 644 ${WORKDIR}/uboot.env ${DEPLOYDIR}/uboot.env
    install -m 644 ${WORKDIR}/bootloader.bin.normal.out ${DEPLOYDIR}/bootloader.bin.normal.out
    install -m 644 ${WORKDIR}/firmware_merak.bin.normal.out ${DEPLOYDIR}/firmware_merak.bin.normal.out
    install -m 644 ${WORKDIR}/build/u-boot.itb ${DEPLOYDIR}/u-boot.itb
    rm ${WORKDIR}/build/fw_dynamic.bin
    install -m 644 ${WORKDIR}/run_qemu_virt.dtb ${DEPLOYDIR}/run_qemu_virt.dtb

    ${DEPLOY_DIR_IMAGE}/jh8100_cst/mkbif ${DEPLOYDIR}/${SPL_BINARYNAME}

    dd if=${WORKDIR}/bootloader.bin.normal.out of=${DEPLOY_DIR_IMAGE}/scp_raw.img count=1 bs=512k conv=sync
    dd if=${WORKDIR}/bootloader.bin.normal.out of=${DEPLOY_DIR_IMAGE}/scp_raw.img seek=1 count=1 bs=512k conv=sync

    dd if=${WORKDIR}/firmware_merak.bin.normal.out of=${DEPLOY_DIR_IMAGE}/scp_raw.img seek=2 count=1 bs=512k conv=sync
    dd if=${WORKDIR}/firmware_merak.bin.normal.out of=${DEPLOY_DIR_IMAGE}/scp_raw.img seek=3 count=1 bs=512k conv=sync

    dd if=${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out of=${DEPLOY_DIR_IMAGE}/scp_raw.img seek=4 count=1 bs=512k conv=sync
    dd if=${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out of=${DEPLOY_DIR_IMAGE}/scp_raw.img seek=5 count=1 bs=512k conv=sync
}

TOOLCHAIN = "gcc"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-visionfive2|starfive-jh8100)"
