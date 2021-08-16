SRCBRANCH = "sifive-rvv-1.0.x-zfh-rvb"

SRCREV = "75d2236ce26a3048f52bbd5186602e27bd635e2b"

BINUTILS_GIT_URI = "git://github.com/sifive/riscv-binutils-gdb.git;branch=${SRCBRANCH};protocol=git"

SRC_URI = "\
      ${BINUTILS_GIT_URI} \
      file://0005-Point-scripts-location-to-libdir.patch \
"

