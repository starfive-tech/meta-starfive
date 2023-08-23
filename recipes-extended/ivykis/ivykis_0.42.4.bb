DESCRIPTION = "ivykis is a library for asynchronous I/O readiness notification."
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

inherit autotools

SRC_URI = " \
	git://github.com/buytenh/ivykis.git;branch=master;protocol=https \
	file://Makefile \
	file://run-ptest \
	"

SRCREV = "f1b14555fb0b5d9acfbcfaf35b1313bb28d858c2"

S = "${WORKDIR}/git"

inherit ptest

do_install_ptest() {
	mkdir ${D}${PTEST_PATH}/test
	cp -r ${B}/test/.libs/* ${D}${PTEST_PATH}/test
	install ${B}/../Makefile ${D}${PTEST_PATH}/test
        install ${S}/test-driver ${D}${PTEST_PATH}
}

RDEPENDS:${PN}-ptest = " \
	bash \
	ivykis \
	"
