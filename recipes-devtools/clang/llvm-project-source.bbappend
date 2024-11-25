FILESEXTRAPATHS:prepend := "${THISDIR}/clang:"

SRCREV = "3b5b5c1ec4a3095ab096dd780e84d7ab81f3d7ff"
BRANCH = "release/18.x"

BASEURI ??= "${LLVM_GIT}/llvm-project.git;protocol=${LLVM_GIT_PROTOCOL};branch=${BRANCH}"
SRC_URI = "\
    ${BASEURI} \
    file://starfive/0001-Add-support-for-Starfive-private-pref-instruction.patch \
    file://starfive/0002-Add-support-for-Starfive-private-CSR-instructions.patch \
    file://starfive/0003-Support-type-conversion-between-fixed-vector-and-sca.patch \
    file://starfive/0004-Support-old-b-letter-represents-for-B-extension.patch \
    file://starfive/0005-Keep-old-register-definitions-because-Dubhe-follow-o.patch \
    file://starfive/0006-Align-bare-metal-multilib-directories-with-gcc.patch \
    file://starfive/0007-Align-default-march-string-with-GCC.patch \
    file://0001-libcxxabi-Find-libunwind-headers-when-LIBCXXABI_LIBU.patch \
    file://0002-compiler-rt-support-a-new-embedded-linux-target.patch \
    file://0003-compiler-rt-Simplify-cross-compilation.-Don-t-use-na.patch \
    file://0004-llvm-TargetLibraryInfo-Undefine-libc-functions-if-th.patch \
    file://0005-llvm-allow-env-override-of-exe-and-libdir-path.patch \
    file://0006-clang-driver-Check-sysroot-for-ldso-path.patch \
    file://0007-clang-Driver-tools.cpp-Add-lssp_nonshared-on-musl.patch \
    file://0008-clang-Prepend-trailing-to-sysroot.patch \
    file://0009-clang-Look-inside-the-target-sysroot-for-compiler-ru.patch \
    file://0010-clang-Define-releative-gcc-installation-dir.patch \
    file://0011-clang-Add-lpthread-and-ldl-along-with-lunwind-for-st.patch \
    file://0012-Pass-PYTHON_EXECUTABLE-when-cross-compiling-for-nati.patch \
    file://0013-Check-for-atomic-double-intrinsics.patch \
    file://0014-libcxx-Add-compiler-runtime-library-to-link-step-for.patch \
    file://0015-clang-llvm-cmake-Fix-configure-for-packages-using-fi.patch \
    file://0016-clang-Fix-resource-dir-location-for-cross-toolchains.patch \
    file://0017-clang-driver-Add-dyld-prefix-when-checking-sysroot-f.patch \
    file://0018-clang-Use-python3-in-python-scripts.patch \
    file://0019-For-x86_64-set-Yocto-based-GCC-install-search-path.patch \
    file://0020-llvm-Do-not-use-find_library-for-ncurses.patch \
    file://0021-llvm-Insert-anchor-for-adding-OE-distro-vendor-names.patch \
    file://0022-compiler-rt-Do-not-use-backtrace-APIs-on-non-glibc-l.patch \
    file://0023-clang-Fix-x86-triple-for-non-debian-multiarch-linux-.patch \
    file://0024-libunwind-Added-unw_backtrace-method.patch \
    file://0025-clang-Do-not-use-install-relative-libc-headers.patch \
    file://0027-Fix-lib-paths-for-OpenEmbedded-Host.patch \
    file://0028-Correct-library-search-path-for-OpenEmbedded-Host.patch \
    file://0029-lldb-Link-with-libatomic-on-x86.patch \
    file://0030-compiler-rt-Enable-__int128-for-ppc32.patch \
    file://0031-llvm-Do-not-use-cmake-infra-to-detect-libzstd.patch \
    file://0032-compiler-rt-Fix-stat-struct-s-size-for-O32-ABI.patch \
    file://0033-compiler-rt-Undef-_TIME_BITS-along-with-_FILE_OFFSET.patch \
    file://0034-ToolChains-Gnu.cpp-ARMLibDirs-search-also-in-lib32.patch \
    file://0035-compiler-rt-Fix-cmake-check-for-_Float16-and-__bf16.patch \
    file://0036-llvm-Add-libunwind.pc.in-and-llvm-config-scripts.patch \
    "
