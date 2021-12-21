require recipes-devtools/gcc/gcc-cross_${PV}.bb
require gcc-crosssdk.inc

EXTRA_OECONF:append:riscv64 = " --with-arch=rv64gc_zba_zbb_zbc_zbs CFLAGS_FOR_TARGET="-O2 -mcmodel=medany" CXXFLAGS_FOR_TARGET="-O2 -mcmodel=medany""
