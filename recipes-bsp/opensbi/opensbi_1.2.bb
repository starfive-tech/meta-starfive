SUMMARY = "RISC-V Open Source Supervisor Binary Interface (OpenSBI)"
DESCRIPTION = "OpenSBI aims to provide an open-source and extensible implementation of the RISC-V SBI specification for a platform specific firmware (M-mode) and a general purpose OS, hypervisor or bootloader (S-mode or HS-mode). OpenSBI implementation can be easily extended by RISC-V platform or System-on-Chip vendors to fit a particular hadware configuration."
HOMEPAGE = "https://github.com/riscv/opensbi"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=42dd9555eb177f35150cf9aa240b61e5"

require opensbi-payloads.inc

inherit autotools-brokensep deploy

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-v1.2-dubhe"
SRCREV:starfive-dubhe = "a10e0837f215fb4ed25f195f2c511a41deb9c324"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "fpga-starfive-v1.2-jh8100"
SRCREV:starfive-jh8100 = "06c3d05b7f5f1e0aa24141e4de487313b620cea9"

SRC_URI = "\
	git://git@192.168.110.45/${FORK}/opensbi.git;protocol=ssh;branch=${BRANCH} \
	"

SRC_URI:remove:nezha = "\
	file://0001-lib-utils-fdt-Require-match-data-to-be-const.patch \
	file://0002-lib-utils-timer-Add-a-separate-compatible-for-the-D1.patch \
	"

S = "${WORKDIR}/git"

EXTRA_OEMAKE:starfive-dubhe = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_OPTIONS=0 FW_TEXT_START=0x80100000 PLATFORM_DEFCONFIG=starfive_defconfig"
EXTRA_OEMAKE:starfive-jh8100 = "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= FW_OPTIONS=0 FW_TEXT_START=0x40000000 PLATFORM_DEFCONFIG=starfive_defconfig"

# If RISCV_SBI_PAYLOAD is set then include it as a payload
EXTRA_OEMAKE:append = " ${@riscv_get_extra_oemake_image(d)}"
EXTRA_OEMAKE:append = " ${@riscv_get_extra_oemake_fdt(d)}"

# Required if specifying a custom payload
do_compile[depends] += "${@riscv_get_do_compile_depends(d)}"

do_install:append() {
	# In the future these might be required as a dependency for other packages.
	# At the moment just delete them to avoid warnings
	rm -r ${D}/include
	rm -r ${D}/lib*
	rm -r ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/payloads
}

do_deploy () {
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.* ${DEPLOYDIR}/
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.* ${DEPLOYDIR}/
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.* ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install

FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.*"

COMPATIBLE_HOST = "(riscv64|riscv32).*"
INHIBIT_PACKAGE_STRIP = "1"

SECURITY_CFLAGS = ""
