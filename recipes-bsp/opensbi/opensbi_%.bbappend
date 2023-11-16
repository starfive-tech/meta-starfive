FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-v1.2-dubhe"
SRCREV:starfive-dubhe = "a10e0837f215fb4ed25f195f2c511a41deb9c324"

FORK:starfive-visionfive2 = "starfive-tech"
BRANCH:starfive-visionfive2 = "master"
SRCREV:starfive-visionfive2 = "c6a092cd80112529cb2e92e180767ff5341b22a3"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "fpga-starfive-v1.2-jh8100-bmc-30Nov"
SRCREV:starfive-jh8100 = "7cbdf359bb9d2f4c79c736fa92952a06a7740657"

SRC_URI:starfive-dubhe = "\
	git://github.com/${FORK}/opensbi.git;protocol=https;branch=${BRANCH} \
	"

SRC_URI:starfive-visionfive2 = "\
	git://github.com/${FORK}/opensbi.git;protocol=https;branch=${BRANCH} \
	file://visionfive2-uboot-fit-image.its \
	"

SRC_URI:starfive-jh8100 = "\
        git://git@192.168.110.45/${FORK}/opensbi.git;protocol=ssh;branch=${BRANCH} \
        "

SRC_URI:remove:nezha = "\
        file://0001-lib-utils-fdt-Require-match-data-to-be-const.patch \
        file://0002-lib-utils-timer-Add-a-separate-compatible-for-the-D1.patch \
        "

EXTRA_OEMAKE:starfive-dubhe = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_OPTIONS=0 FW_TEXT_START=0x80100000 PLATFORM_DEFCONFIG=starfive_defconfig"
EXTRA_OEMAKE:starfive-visionfive2 = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_TEXT_START=0x40000000"
EXTRA_OEMAKE:starfive-jh8100 = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_OPTIONS=0 FW_TEXT_START=0x40000000 PLATFORM_DEFCONFIG=starfive_defconfig"

DEPENDS:starfive-visionfive2 += "u-boot-tools-native dtc-native"

do_deploy:append:starfive-visionfive2() {
	install -m 0644 ${WORKDIR}/visionfive2-uboot-fit-image.its ${DEPLOYDIR}/visionfive2-uboot-fit-image.its
	cd ${DEPLOYDIR}
	mkimage -f visionfive2-uboot-fit-image.its -A riscv -O u-boot -T firmware visionfive2_fw_payload.img
}
