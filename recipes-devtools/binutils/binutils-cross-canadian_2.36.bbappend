FILESEXTRAPATHS_prepend := "${THISDIR}/binutils:"
SRCBRANCH = "riscv-binutils-2.36.1"

SRCREV = "f35674005e609660f5f45005a9e095541ca4c5fe"

BINUTILS_GIT_URI = "git://github.com/riscv/riscv-binutils-gdb.git;branch=${SRCBRANCH};protocol=git"

SRC_URI = "\
      ${BINUTILS_GIT_URI} \
      file://0001-resolve-bug-1-unsupported-zvmo-extension.patch \
      file://0002-Add-support-for-B-extension.patch \
      file://0003-merge-rvv1.0-branch.patch \
      file://0005-support-Dubhe-V-extension.patch \
      file://0005-Point-scripts-location-to-libdir.patch \ 
      file://0006-Add-pseudo-instructions-for-B-extension.patch \
      file://0007-Rename-bdep-to-bdecompress.patch \
      file://0008-RISCV-Add-support-for-bcompress.patch \
      file://0009-RISCV-Coding-bug-Fix-for-FSRW.patch \
      file://0010-RISCV-Coding-bug-Fix-for-FSRIW.patch \
      file://0011-RISCV-Fix-coding-for-un-zip-2-4-8-16-n-b-h-w.patch \
      file://0012-update-bitmap-version-number-from-0.93-to-0.94.patch \
      file://0013-Fix-gas-test-cases.patch \
      file://0014-resolve-crash-issue-when-objdump-encounter-unrecogni.patch \
      file://0015-Remove-MASK_SHAMT2.patch \
      file://0016-Fix-internal-error-undefined-modifier-r.patch \
      file://0017-Fix-coding-for-Bcompressw.patch \
      file://0018-Fix-zip2.b-testcase.patch \
      file://0019-Fix-orc2.n-testcase.patch \
      file://0020-Fix-rev2.n-test-case.patch \
      file://0021-Fix-testcase-for-zip2.b-and-rev2.n.patch \
      file://0022-Fix-r-operands-type-from-fpr-to-gpr.patch \
      file://0023-support-vid-vmv1r-vl1r-vs1r-instruction.patch \
      file://0024-add-vid-instruction-support.patch \
      file://0025-Fix-all-issues-in-b-ext-64.d.patch \
      file://0026-Apply-same-fix-on-b-ext.d.patch \
      file://0027-remove-redundant-symbol.patch \
      file://0028-solved-vector-insns-test-failed-n.patch \
"
