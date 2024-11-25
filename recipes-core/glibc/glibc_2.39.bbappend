FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV_glibc = "6ade91c21140d8c803c289932dbfc74537f65a1f"

SRC_URI:prepend = "\
  file://starfive/0001-riscv-Add-Linux-hwprobe-syscall-support.patch \
  file://starfive/0002-linux-Introduce-INTERNAL_VSYSCALL.patch \
  file://starfive/0003-riscv-Add-hwprobe-vdso-call-support.patch \
  file://starfive/0004-riscv-Add-__riscv_hwprobe-pointer-to-ifunc-calls.patch \
  file://starfive/0005-riscv-Enable-multi-arg-ifunc-resolvers.patch \
  file://starfive/0006-riscv-Add-ifunc-helper-method-to-hwprobe.h.patch \
  file://starfive/0007-riscv-Add-and-use-alignment-ignorant-memcpy.patch \
  file://starfive/0008-riscv-Fix-alignment-ignorant-memcpy-implementation.patch \
  file://starfive/0009-riscv-Add-vector-version-memcpy.patch \
  file://starfive/0010-riscv-Optimize-memchr-with-vector-b-extension.patch \
  file://starfive/0011-riscv-Optimize-memcmp-with-vector-extension.patch \
  file://starfive/0012-riscv-Optimize-memmove-with-vector-extension.patch \
  file://starfive/0013-riscv-Optimize-strlen-with-vector-extension.patch \
  file://starfive/0014-riscv-Optimize-strcmp-with-vector-extension.patch \
  file://starfive/0015-riscv-Optimize-memset-with-vector-extension.patch \
  file://starfive/0016-riscv-Prevent-from-generating-ifunc-code-when-static.patch \
  file://starfive/0017-Fix-bcmp-missing-in-so.patch \
  "

EXTRA_OEMAKE:class-target += "CFLAGS+=' -march=rv64gc_zba_zbb_zbc_zbs' ASFLAGS+=' -march=rv64gc_zba_zbb_zbc_zbs'"
