DESCRIPTION = "Starfive Visionfive2 Tools"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6dc25dc2418b8831c906d43809d8336"

SRC_URI = "git://git@192.168.110.45/sdk/soft_3rdpart;protocol=ssh;nobranch=1"
SRCREV = "10f543ca35e14809a9b4e9df814b798aa4362d14"

S = "${WORKDIR}/git/spl_tool"

do_compile() {
	oe_runmake -C ${S}
}

do_install() {
	install -Dm 0755 ${S}/spl_tool ${D}/${bindir}/spl_tool
}

BBCLASSEXTEND = "native"
