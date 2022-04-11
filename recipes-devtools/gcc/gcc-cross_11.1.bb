require recipes-devtools/gcc/gcc-${PV}.inc
require gcc-cross.inc

EXTRA_OECONF:append:riscv64 = " --with-arch=rv64gc_zba_zbb_zbc_zbs --with-abi=lp64d CFLAGS_FOR_TARGET="-O2 -mcmodel=medany" CXXFLAGS_FOR_TARGET="-O2 -mcmodel=medany""
