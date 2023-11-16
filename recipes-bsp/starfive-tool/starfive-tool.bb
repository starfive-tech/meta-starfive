DESCRIPTION = "Starfive Tools"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

LICENSE:starfive-visionfive2 = "GPL-2.0"
LIC_FILES_CHKSUM:starfive-visionfive2 = "file://LICENSE;md5=e6dc25dc2418b8831c906d43809d8336"

LICENSE:starfive-jh8100 = "CLOSED"
LIC_FILES_CHKSUM:starfive-jh8100 = ""

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-visionfive2 = "git://git@192.168.110.45/sdk/soft_3rdpart;protocol=ssh;branch=jh7110-devel"
SRC_URI:starfive-jh8100 = "file://jh8100_cst.tar.gz \
			"

SRCREV:starfive-visionfive2 = "10f543ca35e14809a9b4e9df814b798aa4362d14"

S:starfive-visionfive2 = "${WORKDIR}/git/spl_tool"

do_compile:starfive-visionfive2() {
	oe_runmake -C ${S}
}

do_compile:starfive-jh8100() {
	oe_runmake -C ${WORKDIR}/jh8100_cst
}

do_install:starfive-visionfive2() {
	install -Dm 0755 ${S}/spl_tool ${D}/${bindir}/spl_tool
}

do_install:starfive-jh8100() {
	cp -r  ${WORKDIR}/jh8100_cst ${DEPLOY_DIR_IMAGE}/jh8100_cst
}

BBCLASSEXTEND = "native"
