require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit uboot-extlinux-config

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

FORK = "sbc"
BRANCH = "vf2-devel"

SRC_URI = "git://git@192.168.110.45/${FORK}/u-boot.git;protocol=ssh;branch=${BRANCH} \
           file://vf2_uEnv.txt \
	   file://vf2_nvme_uEnv.txt \
	   file://tftp-mmc-boot.txt \
	   file://visionfive2-fit-image.its \
          "
SRCREV = "77781526125544acc873b51959f19bbdb50a4f2e"

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

