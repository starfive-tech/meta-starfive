DESCRIPTION = "Starfive Visionfive2 Tools"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6dc25dc2418b8831c906d43809d8336"

SRC_URI = "git://github.com/starfive-tech/tools.git;protocol=https;nobranch=1"
SRCREV = "6067c32c4cc11749a503b0708d98d6d45022cc0c"

S = "${WORKDIR}/git/spl_tool"

do_compile() {
	oe_runmake -C ${S}
}

do_install() {
	install -Dm 0755 ${S}/spl_tool ${D}/${bindir}/spl_tool
}

BBCLASSEXTEND = "native"
