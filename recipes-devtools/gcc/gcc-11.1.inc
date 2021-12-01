require gcc-common.inc

# Third digit in PV should be incremented after a minor release

PV = "11.1.0"

# BINV should be incremented to a revision after a minor gcc release

BINV = "11.1.0"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/gcc:${FILE_DIRNAME}/gcc/backport:"

DEPENDS =+ "mpfr gmp libmpc zlib flex-native"
NATIVEDEPS = "mpfr-native gmp-native libmpc-native zlib-native flex-native"

LICENSE = "GPL-3.0-with-GCC-exception & GPLv3"

LIC_FILES_CHKSUM = "\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
    file://COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
"

#RELEASE ?= "5b2ac9b40c325e9209c0bd55955db84aad4a0cc5"
#BASEURI ?= "https://github.com/gcc-mirror/gcc/archive/${RELEASE}.zip;downloadfilename=gcc-${PV}-${RELEASE}.zip"
BASEURI ?= "${GNU_MIRROR}/gcc/gcc-${PV}/gcc-${PV}.tar.xz"
SRC_URI = "\
           ${BASEURI} \
           file://0001-gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
           file://0002-gcc-poison-system-directories.patch \
           file://0003-gcc-4.3.3-SYSROOT_CFLAGS_FOR_TARGET.patch \
           file://0004-64-bit-multilib-hack.patch \
           file://0005-optional-libstdc.patch \
           file://0006-COLLECT_GCC_OPTIONS.patch \
           file://0007-Use-the-defaults.h-in-B-instead-of-S-and-t-oe-in-B.patch \
           file://0008-fortran-cross-compile-hack.patch \
           file://0009-cpp-honor-sysroot.patch \
           file://0010-MIPS64-Default-to-N64-ABI.patch \
           file://0011-Define-GLIBC_DYNAMIC_LINKER-and-UCLIBC_DYNAMIC_LINKE.patch \
           file://0012-gcc-Fix-argument-list-too-long-error.patch \
           file://0013-Disable-sdt.patch \
           file://0014-libtool.patch \
           file://0015-gcc-armv4-pass-fix-v4bx-to-linker-to-support-EABI.patch \
           file://0016-Use-the-multilib-config-files-from-B-instead-of-usin.patch \
           file://0017-Avoid-using-libdir-from-.la-which-usually-points-to-.patch \
           file://0018-export-CPP.patch \
           file://0019-Ensure-target-gcc-headers-can-be-included.patch \
           file://0020-Don-t-search-host-directory-during-relink-if-inst_pr.patch \
           file://0021-Use-SYSTEMLIBS_DIR-replacement-instead-of-hardcoding.patch \
           file://0022-aarch64-Add-support-for-musl-ldso.patch \
           file://0023-libcc1-fix-libcc1-s-install-path-and-rpath.patch \
           file://0024-handle-sysroot-support-for-nativesdk-gcc.patch \
           file://0025-Search-target-sysroot-gcc-version-specific-dirs-with.patch \
           file://0026-Fix-various-_FOR_BUILD-and-related-variables.patch \
           file://0027-nios2-Define-MUSL_DYNAMIC_LINKER.patch \
           file://0028-Add-ssp_nonshared-to-link-commandline-for-musl-targe.patch \
           file://0029-Link-libgcc-using-LDFLAGS-not-just-SHLIB_LDFLAGS.patch \
           file://0030-sync-gcc-stddef.h-with-musl.patch \
           file://0031-fix-segmentation-fault-in-precompiled-header-generat.patch \
           file://0032-Fix-for-testsuite-failure.patch \
           file://0033-Re-introduce-spe-commandline-options.patch \
           file://0034-libgcc_s-Use-alias-for-__cpu_indicator_init-instead-.patch \
           file://0035-gentypes-genmodes-Do-not-use-__LINE__-for-maintainin.patch \
           file://0036-mingw32-Enable-operation_not_supported.patch \
           file://0037-libatomic-Do-not-enforce-march-on-aarch64.patch \
           file://0001-testuite-Check-pthread-for-omp-module-testing.patch \
           file://0002-Fix-a-test-failure-in-g-.dg-modules-omp-1_c.C.patch \
           file://0003-testuite-Check-pthread-for-omp-module-testing.patch \
           file://0004-varasm-Two-SECTION_RETAIN-fixes-PR100130.patch \
           file://0006-testsuite-Add-ilp32-to-g-.dg-warn-Warray-bounds-20.C.patch \
           file://0007-merge-from-riscv-gcc-10.2.0-to-support-bitmanip.patch \
           file://0008-merge-from-riscv-gcc-10.2.0-to-support-bitmanip.patch \
           file://0009-update-B-version-from-0.93-to-0.94.patch \
           file://0010-Merge-B-instruction-from-0p94-to-1.0.patch \
           file://0011-remove-b-flag-and-use-zba_zbb_zbc_zbs-instead.patch \
           file://0012-replace-x_riscv_bitmanip_subext-with-x_riscv_bitmani.patch \
           file://0013-remove-b-gcc-options.patch \
           file://0014-fix-incorrect-subext-name.patch \
           file://0015-add-builtin-orc.b-clmul-clmulr-clmulh.patch \
           file://0016-merge-sifve-change-for-bitmanip.patch \
           file://0017-Implement-instruction-patterns-for-ZBA-extension.patch \
           file://0018-Cost-model-for-zba-extension.patch \
           file://0019-Implement-instruction-patterns-for-ZBB-extension.patch \
           file://0020-Cost-model-for-zbb-extension.patch \
           file://0021-Use-li-and-rori-to-load-constants.patch \
           file://0022-Implement-instruction-patterns-for-ZBS-extension.patch \
           file://0023-Cost-model-for-ZBS-extension.patch \
           file://0024-RISC-V-costs-handle-BSWAP.patch \
           file://0025-RISC-V-costs-support-shift-and-add-in-strength-reduc.patch \
           file://0026-RISC-V-bitmanip-fix-constant-loading-for-1ULL-31-in-.patch \
           file://0001-Yocto-patch-for-arch-canonicalize-to-use-python3.patch \
"
SRC_URI[sha256sum] = "4c4a6fb8a8396059241c2e674b85b351c26a5d678274007f076957afa1cc9ddf"

S = "${TMPDIR}/work-shared/gcc-${PV}-${PR}/gcc-${PV}"

# For dev release snapshotting
#S = "${TMPDIR}/work-shared/gcc-${PV}-${PR}/gcc-${RELEASE}"
#B = "${WORKDIR}/gcc-${PV}/build.${HOST_SYS}.${TARGET_SYS}"

# Language Overrides
FORTRAN = ""
JAVA = ""

SSP ?= "--disable-libssp"
SSP_mingw32 = "--enable-libssp"

EXTRA_OECONF_BASE = "\
    ${SSP} \
    --enable-libitm \
    --enable-lto \
    --disable-bootstrap \
    --with-system-zlib \
    ${@'--with-linker-hash-style=${LINKER_HASH_STYLE}' if '${LINKER_HASH_STYLE}' else ''} \
    --enable-linker-build-id \
    --with-ppl=no \
    --with-cloog=no \
    --enable-checking=release \
    --enable-cheaders=c_global \
    --without-isl \
"

EXTRA_OECONF_INITIAL = "\
    --disable-libgomp \
    --disable-libitm \
    --disable-libquadmath \
    --with-system-zlib \
    --disable-lto \
    --disable-plugin \
    --enable-linker-build-id \
    --enable-decimal-float=no \
    --without-isl \
    --disable-libssp \
"

EXTRA_OECONF_PATHS = "\
    --with-gxx-include-dir=/not/exist{target_includedir}/c++/${BINV} \
    --with-sysroot=/not/exist \
    --with-build-sysroot=${STAGING_DIR_TARGET} \
"
