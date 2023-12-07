FORK = "bminor"
BRANCH = "gdb-13-branch"
SRCREV = "9987c0f86ef0a6c3957cdc500cbd9e8b62b3f445"

SRC_URI = " \
	git://github.com/bminor/binutils-gdb.git;branch=${BRANCH};protocol=https \
	0001-add-Custom-CSR-instructions.patch \
	0002-add-customer-pref-insn-support.patch \
	0003-add-intial-rvv-support-to-display-rvv-registers.patch \
	0004-fix-incorrect-short-size.patch \
	0005-add-print-parameter-dis_style_immediate.patch \
	0006-fix-gbbranch_tdep-func-call-error.patch \
	"

# Yocto upstream patches (rebased)
SRC_URI +=  " \
	file://0001-mips-linux-nat-Define-_ABIO32-if-not-defined.patch \
	file://0002-ppc-ptrace-Define-pt_regs-uapi_pt_regs-on-GLIBC-syst.patch \
	file://0003-Dont-disable-libreadline.a-when-using-disable-static.patch \
	file://0004-use-asm-sgidefs.h.patch \
	file://0005-Change-order-of-CFLAGS.patch \
	file://0006-resolve-restrict-keyword-conflict.patch \
	file://0007-Fix-invalid-sigprocmask-call.patch \
	file://0008-Define-alignof-using-_Alignof-when-using-C11-or-newe.patch \
	file://add-missing-ldflags.patch \
	"

S = "${WORKDIR}/git"
