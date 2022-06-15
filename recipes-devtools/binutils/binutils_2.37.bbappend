FILESEXTRAPATHS:prepend := "${THISDIR}/binutils:"

SRCREV = "116a737f438d03a1bd6aa706b6ea0b4022f3b7e2"

BINUTILS_GIT_URI = "git://sourceware.org/git/binutils-gdb.git;branch=${SRCBRANCH};protocol=git"

SRC_URI += "\
     file://0001-merge-from-dubhe-pr-06-30-to-support-b0.94.patch \
     file://0002-merge-from-dubhe-pr-06-30-to-support-vector1.0.patch \
     file://0003-fix-unrecoginized-b-ext-issue.patch \
     file://0004-set-v-ext-version-to-1.0-draft.patch \
     file://0005-fix-incorrect-RCLASS_MAX.patch \
     file://0006-Merge-B-instruction-from-0p94-to-1.0.patch \
     file://0007-merge-Add-four-new-instruction-of-custom-CSRs.patch \
     file://0008-add-cache-prefetch-instruction.patch \
     file://0009-Delete-redundant-b0.94-instructions.patch \
     file://0010-change-vle1.v-vse1.v-to-vlm.v-vsm.v-according-rvv-1..patch \
     file://0011-enable-l2-cache-prefetch.patch \
     file://0012-RISC-V-Hypervisor-ext-drop-Privileged-Spec-1.9.1.patch \
     file://0013-RISC-V-Hypervisor-ext-support-Privileged-Spec-1.12.patch \
     file://0014-fixed-a-compiled-error-when-enable-gdb.patch \
     file://0015-fix-incorrect-pref-address-range.patch \
     file://0016-Rename-some-assembler-mnemonic-for-rvv1.0.patch \
     file://0017-add-miss-change-riscv-opc.h-for-rvv1.0.patch \
     file://0018-ignore-zvl-and-zve-extension-for-now.patch \
"
