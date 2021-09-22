FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRCREV = "63a44e5923c859e99d3a8799fa8132b49a135241"
BRANCH = "fsf-gdb-10.1-with-sim"

S = "${WORKDIR}/git"

SRC_URI = " git://github.com/riscv/riscv-binutils-gdb.git;branch=${BRANCH} \
	file://0004-RISC-V-Minor-cleanup-and-testcases-improvement-for-a.patch \
	file://0005-RISC-V-Don-t-allow-any-uppercase-letter-in-the-arch-.patch \
	file://0006-RISC-V-Add-zifencei-and-prefixed-h-class-extensions.patch \
	file://0007-RISC-V-Remove-the-unimplemented-extensions.patch \
	file://0008-RISC-V-Improve-the-version-parsing-for-arch-string.patch \
	file://0009-RISC-V-Support-to-add-implicit-extensions.patch \
	file://0010-RISC-V-Support-to-add-implicit-extensions-for-G.patch \
	file://0011-RISC-V-Fix-the-order-checking-for-Z-extension.patch \
	file://0012-RISC-V-Support-RVV-according-to-vector-spec-v1.0-dra.patch \
	file://0013-Extend-the-VL-nf-R-and-VS-nf-R-instruction-when-nf-i.patch \
	file://0014-Remove-Vector-indexed-instruction-when-EEW-128.patch \
	file://0015-Add-assembly-pseudo-instructions-vncvt.x.x.v.patch \
	file://0016-Update-the-vector-spec-version-tag-Version-1.0-draft.patch \
	file://0017-Added-reciprocal-and-reciprocal-square-root-estimate.patch \
	file://0018-Added-element-width-hint-to-whole-register-loads-sto.patch \
	file://0019-Update-the-vector-spec-version-tag-Version-1.0-draft.patch \
	file://0020-Added-vrgatherei16-instruction.patch \
	file://0021-Make-vlmul-bits-contiguous-in-vtype.patch \
	file://0022-RISC-V-Clarify-the-supported-versions-for-the-unrati.patch \
	file://0023-RISC-V-Convert-CSR-dwarf-numbers-to-gdb-register-num.patch \
	file://0024-RISC-V-Update-the-vector-mask-constraints.patch \
	file://0025-RISC-V-Add-the-missing-constraints-for-VL-nf-R-and-V.patch \
	file://0026-RISC-V-Fix-the-constraints-for-vector-mask-and-compr.patch \
	file://0027-RISC-V-Report-rvv-assembler-constraint-errors-in-det.patch \
	file://0028-RISC-V-Support-Zfh-standard-extension-for-half-preci.patch \
	file://0029-RISC-V-Support-.float16-directive-for-assembler.patch \
	file://0030-Set-default-version-of-ZFH-to-0.1.patch \
	file://0031-RISC-V-Add-assembly-pseudoinstructions-vneg.v-and-vf.patch \
	file://0032-RISC-V-Added-ordered-unordered-vector-indexed-instru.patch \
	file://0033-RISC-V-Rename-vncvt.x.x.v-to-vncvt.x.x.w.patch \
	file://0034-SiFive-Support-SiFive-CLIC-CSRs.patch \
	file://0035-SiFive-Support-SiFive-specific-cache-control-instruc.patch \
	file://0036-Initial-support-for-RISC-V-Bitmanip-Spec-0.92.patch \
	file://0037-RISC-V-Support-sub-extensions-of-B-for-march-parser.patch \
	file://0038-RISC-V-Remove-ld-testcases-which-should-be-better-to.patch \
	file://0039-RISC-V-Added-Removed-Updated-instructions-to-the-ris.patch \
	file://0040-RISC-V-Add-missing-zext.-hw-pseudo-instructions.patch \
	file://0041-RISC-V-Re-define-zba-zbb-zbc-and-zbs-extensions.patch \
	file://0042-RISC-V-Define-pseudo-rev-orc-zip-unzip-as-alias-inst.patch \
	file://0043-RISC-V-Add-testcases-for-ZBA-and-ZBB-instructions.patch \
	file://0044-RISC-V-Add-sext.-bh-and-zext.-bhw-pseudo-instruction.patch \
	file://0045-Fix-sim-build-since-op-match_func-have-more-argument.patch \
	file://0046-SiFive-Add-RNMI-CSRs-and-MNRET-instruction.patch \
	file://0047-RISC-V-Freeze-ZBA-ZBB-ZBC-to-0.93.patch \
	file://0048-fix-incorrect-shfli-and-unshfli-opcode.patch \
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
