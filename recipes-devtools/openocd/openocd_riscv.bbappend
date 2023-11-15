require recipes-devtools/openocd/openocd_git.bb

#FILESEXTRAPATHS:prepend := "${THISDIR}/openocd:"

LIC_FILES_CHKSUM = "file://COPYING;md5=599d2d1ee7fc84c0467b3d19801db870"

PV = "riscv"

SRC_URI = " \
    git://github.com/riscv/riscv-openocd.git;protocol=https;branch=riscv;name=openocd \
    git://repo.or.cz/r/git2cl.git;protocol=http;destsuffix=tools/git2cl;name=git2cl;branch=master \
    git://github.com/msteveb/jimtcl.git;protocol=https;branch=master;name=jimtcl;destsuffix=git/jimtcl \
    git://repo.or.cz/r/libjaylink.git;protocol=http;destsuffix=git/src/jtag/drivers/libjaylink;name=libjaylink;branch=master \
    file://0001-autosetup-cc-check-tools-check-only-the-name.patch \
    file://Dubhe_FPGA_openocd.patch \
    file://dubhe_olimex-openocd_s5.cfg \
    file://dubhe_olimex_flash_write.cfg \
    file://jh8100_0150_f1.cfg \
    file://jh8100_0150_f2.cfg \
    file://jh8100_jrc.cfg \
"

SRCREV_openocd = "a037b20f2e015859327ab37588792386c4fc942f"
SRCREV_jimtcl = "70b007b63669a709b0e8aef34a22658047815cc2"

EXTRA_OECONF = "--enable-ftdi"

inherit deploy

do_deploy() {
	install -m 755 ${WORKDIR}/dubhe_olimex-openocd_s5.cfg ${DEPLOYDIR}/
	install -m 755 ${WORKDIR}/dubhe_olimex_flash_write.cfg ${DEPLOYDIR}/
	install -m 755 ${WORKDIR}/jh8100_0150_f1.cfg ${DEPLOYDIR}/
	install -m 755 ${WORKDIR}/jh8100_0150_f2.cfg ${DEPLOYDIR}/
	install -m 755 ${WORKDIR}/jh8100_jrc.cfg ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install

COMPATIBLE_HOST = "(x86_64.*|i.86.*|riscv32|riscv64).*-linux"
