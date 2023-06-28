require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit uboot-extlinux-config

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

FORK = "starfive-tech"
BRANCH = "JH7110_VisionFive2_devel"
SRCREV = "b6e2b0e85c774a18ae668223a6e5f7d335895243"

SRC_URI = "git://github.com/${FORK}/u-boot.git;protocol=https;branch=${BRANCH} \
           file://vf2_uEnv.txt \
	   file://vf2_nvme_uEnv.txt \
	   file://tftp-mmc-boot.txt \
	   file://visionfive2-fit-image.its \
	   file://uboot_disable_logo.patch \
          "

DEPENDS:append = " u-boot-tools-native starfive-tool-native"

# Overwrite this for your server
TFTP_SERVER_IP ?= "127.0.0.1"

do_configure:prepend() {
    sed -i -e 's,@SERVERIP@,${TFTP_SERVER_IP},g' ${WORKDIR}/tftp-mmc-boot.txt
    mkimage -O linux -T script -C none -n "U-Boot boot script" \
        -d ${WORKDIR}/tftp-mmc-boot.txt ${WORKDIR}/${UBOOT_ENV_BINARY}
}

do_deploy:append:starfive-visionfive2() {
    install -m 644 ${WORKDIR}/vf2_nvme_uEnv.txt ${DEPLOYDIR}/vf2_nvme_uEnv.txt
    install -m 644 ${WORKDIR}/vf2_uEnv.txt ${DEPLOYDIR}/vf2_uEnv.txt
    install -m 644 ${WORKDIR}/visionfive2-fit-image.its ${DEPLOYDIR}/visionfive2-fit-image.its
    spl_tool -c -f ${DEPLOYDIR}/${SPL_IMAGE}
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_BINARYNAME}.normal.out
    ln -sf ${SPL_IMAGE}.normal.out ${DEPLOYDIR}/${SPL_SYMLINK}.normal.out
}

TOOLCHAIN = "gcc"

COMPATIBLE_MACHINE = "(starfive-visionfive2)"

