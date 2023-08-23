DESCRIPTION = "Lua CJSON is a fast JSON encoding/parsing module for Lua"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/mpx/lua-cjson"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1fee3afe4f4a4b26c13016123b2d08a"

DEPENDS += "luajit-native luajit-riscv"
RDEPENDS:${PN} += "luajit-riscv"

SRC_URI = "git://github.com/openresty/lua-cjson.git;branch=master;protocol=https \
	   file://run-ptest \
          "

SRCREV = "91c3363e7dc86263a379719e5508fc0979df84d3"
S = "${WORKDIR}/git"
LUA_VERSION = "5.1"

EXTRA_OEMAKE = "LUA_V=${LUA_VERSION} PREFIX=${D}/usr LUA_INCLUDE_DIR=${STAGING_INCDIR}/luajit-2.1"

do_compile() {
    cd ${S}
    oe_runmake all
}


do_install() {
    cd ${S}
    oe_runmake install
}

FILES:${PN} = " \
	${datadir}/lua/${LUA_VERSION}/ \
	${libdir}/lua/${LUA_VERSION}/ \
"

inherit ptest

do_compile_ptest() {
	cd ${S}/tests
	./genutf8.pl
}

do_install_ptest() {
	mkdir -p ${D}${PTEST_PATH}/tests
	install ${S}/tests/test.lua ${D}${PTEST_PATH}/tests
	install ${S}/tests/octets-escaped.dat ${D}${PTEST_PATH}/tests
	install ${S}/tests/utf8.dat ${D}${PTEST_PATH}/tests
	cp -r ${S}/lua/cjson ${D}${PTEST_PATH}/tests
}
