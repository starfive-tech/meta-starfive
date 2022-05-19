require recipes-devtools/gcc/gcc-${PV}.inc
require gcc-cross-canadian.inc

EXTRA_OECONF:append:riscv64 = " --with-arch=rv64gcbv --with-abi=lp64d CFLAGS_FOR_TARGET="-O2 -mcmodel=medany" CXXFLAGS_FOR_TARGET="-O2 -mcmodel=medany""
