FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

FORK = "bminor"
BRANCH = "gdb-14-branch"
SRCREV = "02c10eaecb63e5dbb99cbfdd1c5385e53ed031ff"

SRC_URI = "\
  git://github.com/${FORK}/binutils-gdb.git;protocol=https;branch=${BRANCH} \
  file://starfive/0001-gas-riscv-Add-customer-pref-instruction.patch \
  file://starfive/0002-gas-riscv-Add-customer-CSR-instructions.patch \
  file://starfive/0003-add-xstcmo-option-to-manage-StarFive-Technology-proc.patch \
  file://0001-mips-linux-nat-Define-_ABIO32-if-not-defined.patch \
  file://0002-ppc-ptrace-Define-pt_regs-uapi_pt_regs-on-GLIBC-syst.patch \
  file://0003-Dont-disable-libreadline.a-when-using-disable-static.patch \
  file://0004-use-asm-sgidefs.h.patch \
  file://0005-Change-order-of-CFLAGS.patch \
  file://0007-Fix-invalid-sigprocmask-call.patch \
  file://0008-Define-alignof-using-_Alignof-when-using-C11-or-newe.patch \
  "

S = "${WORKDIR}/git"
