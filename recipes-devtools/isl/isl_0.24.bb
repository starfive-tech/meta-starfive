require isl.inc

DEPENDS = "gmp"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c7c9ea0d2ff040ba4a25afa0089624b"

SRC_URI = "https://gcc.gnu.org/pub/gcc/infrastructure/isl-${PV}.tar.bz2"

SRC_URI[sha256sum] = "fcf78dd9656c10eb8cf9fbd5f59a0b6b01386205fe1934b3b287a0a1898145c0"

S = "${WORKDIR}/isl-${PV}"

