SUMMARY = "Dhrystone CPU benchmark"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/PD;md5=b3597d12946881e13cb3b548d1173851"

INHIBIT_PACKAGE_STRIP = "1"

SRC_URI = "git://github.com/sifive/benchmark-dhrystone.git;protocol=https \
           file://0001-dhrystone-Edit-compiler-flags.patch \
           "
SRCREV = "0ddff533cc9052c524990d5ace4560372053314b"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"
EXTRA_OEMAKE += "'CC=${CC}'"

do_compile(){
	oe_runmake dhrystone
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 dhrystone ${D}${bindir}
}

