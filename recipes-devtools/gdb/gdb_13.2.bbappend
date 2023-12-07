FORK = "bminor"
BRANCH = "gdb-13-branch"
SRCREV = "c987953c1028fce9ea6080fdfbbc7d25192be69e"

# StarFive patches
SRC_URI = " \
	git://github.com/bminor/binutils-gdb.git;branch=${BRANCH};protocol=https \
	file://0001-add-Custom-CSR-instructions.patch \
	file://0002-add-customer-pref-insn-support.patch \
	file://0003-add-print-parameter-dis_style_immediate.patch \
	"

# Yocto upstream patches (rebase these if patch fail/fuzz)
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

CXXFLAGS += "-fPIC "
