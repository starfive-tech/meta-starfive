require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit uboot-extlinux-config

LIC_FILES_CHKSUM:starfive-dubhe = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
LIC_FILES_CHKSUM:starfive-visionfive2 = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"
LIC_FILES_CHKSUM:starfive-jh8100 = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "dubhe_fpga_dev_v2024.10"
SRCREV:starfive-dubhe = "34023eeb776ca3ba40fb3b53b73305a22d802898"

FORK:starfive-visionfive2 = "starfive-tech"
BRANCH:starfive-visionfive2 = "JH7110_VisionFive2_devel-v3.9.3"
SRCREV:starfive-visionfive2 = "b6e2b0e85c774a18ae668223a6e5f7d335895243"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "jh8100_fpga_dev_v2023.01_rebase_v2_2.0.8"
SRCREV:starfive-jh8100 = "442d4f8d5bc53f52b88d1d410709f3441d4f94af"

SRC_URI:starfive-dubhe = "\
    git://github.com/${FORK}/u-boot.git;protocol=https;branch=${BRANCH} \
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
    git://github.com/${FORK}/u-boot.git;protocol=https;branch=${BRANCH} \
    file://tftp-mmc-boot.txt \
    file://run_qemu_virt.dtb \
    file://jh8100-fpga.bin.normal.out \
    file://firmware.bin.normal.out \
    file://uboot.env \
    "

DEPENDS:append:starfive-dubhe = " u-boot-tools-native bmaptool-native opensbi"
DEPENDS:append:starfive-visionfive2 = " u-boot-tools-native starfive-tool-native"
DEPENDS:append:starfive-jh8100 = " u-boot-tools-native bmaptool-native opensbi spltool-native"

do_compile[depends] += "linux-starfive-dev:do_compile"

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

do_compile:prepend:starfive-dubhe() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
    cp ${DEPLOY_DIR_IMAGE}/kernel_fit/* ${S}
}

do_compile:prepend:starfive-jh8100() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
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
    install -m 644 ${B}/u-boot.itb ${DEPLOYDIR}/u-boot.itb
    install -m 644 ${B}/kernel.itb ${DEPLOYDIR}/kernel.itb
    install -m 644 ${WORKDIR}/run_qemu_virt.dtb ${DEPLOYDIR}/run_qemu_virt.dtb
}

do_deploy:append:starfive-jh8100() {
    install -m 644 ${WORKDIR}/uboot.env ${DEPLOYDIR}/uboot.env
    install -m 644 ${WORKDIR}/jh8100-fpga.bin.normal.out ${DEPLOYDIR}/jh8100-fpga.bin.normal.out
    install -m 644 ${WORKDIR}/firmware.bin.normal.out ${DEPLOYDIR}/firmware.bin.normal.out
    install -m 644 ${B}/u-boot.itb ${DEPLOYDIR}/u-boot.itb
    install -m 644 ${WORKDIR}/run_qemu_virt.dtb ${DEPLOYDIR}/run_qemu_virt.dtb

    mkbif ${DEPLOYDIR}/${SPL_BINARYNAME}

    dd if=${WORKDIR}/jh8100-fpga.bin.normal.out of=${DEPLOYDIR}/scp_raw.img count=1 bs=512k conv=sync
    dd if=${WORKDIR}/jh8100-fpga.bin.normal.out of=${DEPLOYDIR}/scp_raw.img seek=1 count=1 bs=512k conv=sync

    dd if=${WORKDIR}/firmware.bin.normal.out of=${DEPLOYDIR}/scp_raw.img seek=2 count=1 bs=512k conv=sync
    dd if=${WORKDIR}/firmware.bin.normal.out of=${DEPLOYDIR}/scp_raw.img seek=3 count=1 bs=512k conv=sync

    dd if=${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out of=${DEPLOYDIR}/scp_raw.img seek=4 count=1 bs=512k conv=sync
    dd if=${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out of=${DEPLOYDIR}/scp_raw.img seek=5 count=1 bs=512k conv=sync
}

TOOLCHAIN = "gcc"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-visionfive2|starfive-jh8100)"
