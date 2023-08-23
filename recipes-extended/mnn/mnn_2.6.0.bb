DESCRIPTION = "MNN is a blazing fast, lightweight deep learning framework, battle-tested by business-critical use cases in Alibaba"
HOMEPAGE = "www.mnn.zone"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.md;startline=124;endline=125;md5=49af4a1302656dc01a1c983ee77b8227"

SRC_URI = " \
    git://github.com/alibaba/MNN.git;branch=master;protocol=https \
    file://run-ptest \
"
SRCREV = "c293f9eeab0a2983aa260ca81c751b09a389b16d"
S = "${WORKDIR}/git"

inherit cmake ptest

EXTRA_OECMAKE = "-DMNN_BUILD_TEST=1"

FILES:${PN} += "${libdir}/MNN*"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

RDEPENDS:${PN} += "libstdc++"

do_install_ptest() {
        install ${B}/run_test.out ${D}${PTEST_PATH}
}
