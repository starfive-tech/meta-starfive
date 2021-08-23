SUMMARY = "Coremark CPU benchmark"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

INHIBIT_PACKAGE_STRIP = "1"

SRC_URI = "git://github.com/sifive/benchmark-coremark.git \
           file://0001-Add-Compiler-Flags-and-remove-exe-from-output.patch \
           "
SRCREV = "4486de1f0afe9d6c1fa8dd63743e5751286f3d2f"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"
EXTRA_OEMAKE += "'CC=${CC}' PORT_DIR=linux64"

do_compile(){
	oe_runmake compile
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 coremark ${D}${bindir}
}

