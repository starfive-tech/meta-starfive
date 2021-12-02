require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-source.inc

EXCLUDE_FROM_WORLD = "1"

EXTRA_OECONF_append_riscv64 = " --with-arch=rv64gc_zba_zbb_zbc_zbs CFLAGS_FOR_TARGET="-O2 -mcmodel=medany" CXXFLAGS_FOR_TARGET="-O2 -mcmodel=medany""
