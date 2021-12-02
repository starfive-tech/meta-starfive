FILESEXTRAPATHS_prepend := "${THISDIR}/gdb:"

SRCREV = "5da071ef0965b8054310d8dde9975037b0467311"
BRANCH = "fsf-gdb-10.1-with-sim"

S = "${WORKDIR}/git"

SRC_URI = " git://github.com/riscv/riscv-binutils-gdb.git;branch=${BRANCH} \
	file://0001-add-b-v-extension-support.patch \
	file://0001-make-man-install-relative-to-DESTDIR.patch \
	file://0002-mips-linux-nat-Define-_ABIO32-if-not-defined.patch \
	file://0003-ppc-ptrace-Define-pt_regs-uapi_pt_regs-on-GLIBC-syst.patch \
	file://0004-Add-support-for-Renesas-SH-sh4-architecture.patch \
	file://0005-Dont-disable-libreadline.a-when-using-disable-static.patch \
	file://0006-use-asm-sgidefs.h.patch \
	file://0008-Change-order-of-CFLAGS.patch \
	file://0009-resolve-restrict-keyword-conflict.patch \
	file://0010-Fix-invalid-sigprocmask-call.patch \
	file://0011-gdbserver-ctrl-c-handling.patch \
"
