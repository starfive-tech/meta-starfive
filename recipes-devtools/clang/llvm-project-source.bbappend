FILESEXTRAPATHS:prepend := "${THISDIR}/clang:"

MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

SRCREV = "afab3c488f0c86af87e262cc7454e04de18e3e6a"
BRANCH = "main"

SRC_URI = "\
    ${BASEURI} \
    file://llvm-config \
    file://libunwind.pc.in \
    file://0001-Merge-B-instruction-from-0p94-to-1.0.patch \
    file://0002-update-B-instruction-to-1.0-and-Merge-four-Custom-CS.patch \
    file://0003-add-pref-instruction.patch \
    file://0004-remove-v-b-experimental-version-check.patch \
    file://0005-fix-b-extension-version.patch \
    file://0006-fix-b-extension-feature.patch \
    file://0007-ignore-b-v-version-check-if-version-is-empty.patch \
    file://0008-add-multilib-v-b-support-for-baremetal-toolchain.patch \
    file://0009-fix-b-extension-feature-2.patch \
    file://0010-remove-zvlsseg-if-not-explicitly-enable-zvlsseg.patch \
    file://0011-set-default-march-to-rv64imafcd_zba_zbb_zbc_zbs-and-.patch \
    file://0012-fix-incorrect-v-version.patch \
    file://0013-fix-incorrect-v-extension-in-assembler.patch \
    file://0014-fix-redundant-judge-in-assert.patch \
    file://0015-spport-H-extension-in-MC-layer.patch \
    file://0016-fix-redundant-judge-in-assert-2.patch \
    file://0017-use-GPRMemZeroOffset-instead-of-zeroimm-for-hypervis.patch \
    file://0018-delete-no-supported-b-testcase.patch \
    file://0019-fix-v-zvamo-zvlsseg-version-information.patch \
    file://0020-update-b-v-arch-test.patch \
    file://0021-delete-no-supported-b-testcase-2.patch \
    file://0022-update-b-arch-test-2.patch \
    file://0023-update-b-v-arch-test-2.patch \
    file://0024-auto-enable-zvlsseg-if-v-is-enabled.patch \
    file://0025-2-2-Add-the-tail-policy-argument-to-builtins-intrins.patch \
    file://0026-Update-to-vlm.v-and-vsm.v-according-to-v1.0-rc1.patch \
    file://0027-Add-doxygen-comment-for-hasFp-in-RISCVFrameLowering..patch \
    file://0028-Handle-vector-of-pointer-in-getTgtMemIntrinsic-for-s.patch \
    file://0029-Correct-FileCheck-prefixes-in-rv32zbc-intrinsic.ll-a.patch \
    file://0030-Add-more-tests-of-add-mul-r-c0-c1.patch \
    file://0031-Require-tail-policy-argument-to-builtins-to-be-an-in.patch \
    file://0032-Add-more-tests-of-immediate-materialisation.patch \
    file://0033-Update-test-cases-through-update_cc_test_checks.py.patch \
    file://0034-Define-_m-intrinsics-as-builtins-instead-of-macros.patch \
    file://0035-Rename-assembler-mnemonic-of-unordered-floating-poin.patch \
    file://0036-Fix-RISCV-vector-header-comment.patch \
    file://0037-Revert-RISCV-Define-_m-intrinsics-as-builtins-instea.patch \
    file://0038-Revert-RISCV-2-2-Add-the-tail-policy-argument-to-bui.patch \
    file://0039-After-reverting-_mt-builtins-add-ta-argument-for-LLV.patch \
    file://0040-fixed-riscv-vector-test-error.patch \
    file://0001-lldb-Add-lxml2-to-linker-cmdline-of-xml-is-found.patch \
    file://0002-libcxxabi-Find-libunwind-headers-when.patch \
    file://0004-llvm-TargetLibraryInfo-Undefine-libc-functions-if-th.patch \
    file://0005-llvm-allow-env-override-of-exe-path.patch \
    file://0006-clang-driver-Check-sysroot-for-ldso-path.patch \
    file://0007-clang-Driver-tools.cpp-Add-lssp_nonshared-on-musl.patch \
    file://0008-clang-musl-ppc-does-not-support-128-bit-long-double.patch \
    file://0009-clang-Prepend-trailing-to-sysroot.patch \
    file://0010-clang-Look-inside-the-target-sysroot-for-compiler-ru.patch \
    file://0011-clang-Define-releative-gcc-installation-dir.patch \
    file://0012-clang-Add-lpthread-and-ldl-along-with-lunwind-for-st.patch \
    file://0013-Pass-PYTHON_EXECUTABLE-when-cross-compiling-for-nati.patch \
    file://0014-Check-for-atomic-double-intrinsics.patch \
    file://0015-libcxx-Add-compiler-runtime-library-to-link-step-for.patch \
    file://0016-clang-llvm-cmake-Fix-configure-for-packages-using.patch \
    file://0017-clang-Fix-resource-dir-location-for-cross-toolchains.patch \
    file://0018-fix-path-to-libffi.patch \
    file://0019-clang-driver-Add-dyld-prefix-when-checking-sysroot-f.patch \
    file://0020-clang-Use-python3-in-python-scripts.patch \
    file://0021-For-x86_64-set-Yocto-based-GCC-install-search-path.patch \
    file://0022-llvm-Do-not-use-find_library-for-ncurses.patch \
    file://0023-llvm-Insert-anchor-for-adding-OE-distro-vendor-names.patch \
    file://0024-compiler-rt-Use-mcr-based-barrier-on-armv6.patch \
    file://0025-clang-Switch-defaults-to-dwarf-5-debug-info-on-Linux.patch \
    file://0026-compiler-rt-Do-not-use-backtrace-APIs-on-non-glibc-l.patch \
    file://0027-clang-Fix-x86-triple-for-non-debian-multiarch-linux-.patch \
    file://0028-compiler-rt-Link-scudo-with-SANITIZER_CXX_ABI_LIBRAR.patch \
    file://0029-compiler-rt-Link-scudo-standalone-with-libatomic-on-.patch \
    file://0030-libunwind-Added-unw_backtrace-method.patch \
    file://0031-compiler-rt-Use-uintptr_t-instead-of-_Unwind_Word.patch \
    file://0032-compiler-rt-Do-not-force-thumb-mode-directive.patch \
    file://0033-clang-Do-not-use-install-relative-libc-headers.patch \
    file://0034-clang-Fix-how-driver-finds-GCC-installation-path-on.patch \
    file://0001-Revert-CMake-Enable-LLVM_ENABLE_PER_TARGET_RUN.patch \
    "
