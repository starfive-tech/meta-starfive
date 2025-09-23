SUMMARY = "Coremark CPU benchmark"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

INHIBIT_PACKAGE_STRIP = "1"

BRANCH = "master"
SRC_URI = "git://github.com/sifive/benchmark-coremark.git;protocol=ssh;branch=${BRANCH} \
           file://0001-Add-Compiler-Flags-and-remove-exe-from-output.patch \
           "
SRCREV = "4486de1f0afe9d6c1fa8dd63743e5751286f3d2f"

LDFLAGS += "-static"
TARGET_CC_ARCH += "${LDFLAGS} -march=rv64gc_zba_zbb_zbs_zbc"
EXTRA_OEMAKE += "'CC=${CC}' PORT_DIR=linux64 XCFLAGS='-march=rv64gc_zba_zbb_zbs_zbc'"

do_compile(){
	oe_runmake compile
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 coremark ${D}${bindir}
}

