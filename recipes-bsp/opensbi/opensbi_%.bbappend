FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-v1.4-dubhe"
SRCREV:starfive-dubhe = "6e71d90592d81a41335c480f7704250ba9592fee"

FORK:starfive-visionfive2 = "starfive-tech"
BRANCH:starfive-visionfive2 = "master"
SRCREV:starfive-visionfive2 = "c6a092cd80112529cb2e92e180767ff5341b22a3"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "fpga-starfive-v1.2-jh8100"
SRCREV:starfive-jh8100 = "924640487c6815c63adcbf19fbf3283900988a6f"

SRC_URI:starfive-dubhe = "\
	git://github.com/${FORK}/opensbi.git;protocol=https;branch=${BRANCH} \
	"

SRC_URI:starfive-visionfive2 = "\
	git://github.com/${FORK}/opensbi.git;protocol=https;branch=${BRANCH} \
	file://visionfive2-uboot-fit-image.its \
	"

SRC_URI:starfive-jh8100 = "\
        git://github.com/${FORK}/opensbi.git;protocol=https;branch=${BRANCH} \
        "

SRC_URI:remove:nezha = "\
        file://0001-lib-utils-fdt-Require-match-data-to-be-const.patch \
        file://0002-lib-utils-timer-Add-a-separate-compatible-for-the-D1.patch \
        "

EXTRA_OEMAKE:starfive-dubhe = "PLATFORM=${RISCV_SBI_PLAT} I=${D} CLANG_TARGET= FW_OPTIONS=0 FW_TEXT_START=0x80100000"
EXTRA_OEMAKE:starfive-visionfive2 = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_TEXT_START=0x40000000"
EXTRA_OEMAKE:starfive-jh8100 = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_OPTIONS=0 FW_TEXT_START=0x40000000 PLATFORM_DEFCONFIG=starfive_defconfig"

DEPENDS:starfive-visionfive2 += "u-boot-tools-native dtc-native"

do_deploy:append:starfive-visionfive2() {
	install -m 0644 ${WORKDIR}/visionfive2-uboot-fit-image.its ${DEPLOYDIR}/visionfive2-uboot-fit-image.its
	cd ${DEPLOYDIR}
	mkimage -f visionfive2-uboot-fit-image.its -A riscv -O u-boot -T firmware visionfive2_fw_payload.img
}
