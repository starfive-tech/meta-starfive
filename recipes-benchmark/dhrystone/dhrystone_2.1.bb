SUMMARY = "Dhrystone CPU benchmark"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

INHIBIT_PACKAGE_STRIP = "1"

SRC_URI = "git://github.com/sifive/benchmark-dhrystone.git \
           file://0001-Edit-Makefile-to-add-compiler-flags.patch \
           "
SRCREV = "${AUTOREV}"

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

