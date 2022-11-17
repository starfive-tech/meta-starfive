require binutils.inc
require binutils-${PV}.inc

DEPENDS += "zlib"

EXTRA_OECONF += "--with-sysroot=/ \
                --enable-install-libbfd \
                --enable-install-libiberty \
                --enable-shared \
                --with-system-zlib \
                "

EXTRA_OEMAKE:append:libc-musl = "\
                                 gt_cv_func_gnugettext1_libc=yes \
                                 gt_cv_func_gnugettext2_libc=yes \
                                "
EXTRA_OECONF:class-native = "--enable-targets=all \
                             --enable-64-bit-bfd \
                             --enable-install-libiberty \
                             --enable-install-libbfd \
                             --disable-gdb \
                             --disable-gdbserver \
                             --disable-libdecnumber \
                             --disable-readline \
                             --disable-sim \
                             --disable-werror"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'debuginfod', d)}"
PACKAGECONFIG[debuginfod] = "--with-debuginfod, --without-debuginfod, elfutils"
# gcc9.0 end up mis-compiling libbfd.so with O2 which then crashes on target
# So remove -O2 and use -Os as workaround
SELECTED_OPTIMIZATION:remove:mipsarch = "-O2"
SELECTED_OPTIMIZATION:append:mipsarch = " -Os"

do_install:class-native () {
	autotools_do_install

	# Install the libiberty header
	install -d ${D}${includedir}
	install -m 644 ${S}/include/ansidecl.h ${D}${includedir}
	install -m 644 ${S}/include/libiberty.h ${D}${includedir}

	# We only want libiberty, libbfd and libopcodes
	rm -rf ${D}${bindir}
	rm -rf ${D}${prefix}/${TARGET_SYS}
	rm -rf ${D}${prefix}/lib/ldscripts
	rm -rf ${D}${prefix}/share/info
	rm -rf ${D}${prefix}/share/locale
	rm -rf ${D}${prefix}/share/man
	rmdir ${D}${prefix}/share || :
	rmdir ${D}/${libdir}/gcc-lib || :
	rmdir ${D}/${libdir}64/gcc-lib || :
	rmdir ${D}/${libdir} || :
	rmdir ${D}/${libdir}64 || :
}

# libctf races with libbfd
PARALLEL_MAKEINST:class-target = ""
PARALLEL_MAKEINST:class-nativesdk = ""

# Split out libbfd-*.so and libopcodes-*.so so including perf doesn't include
# extra stuff
PACKAGE_BEFORE_PN += "libbfd libopcodes"
FILES:libbfd = "${libdir}/libbfd-*.so.* ${libdir}/libbfd-*.so"
FILES:libopcodes = "${libdir}/libopcodes-*.so.* ${libdir}/libopcodes-*.so"

SRC_URI:append:class-nativesdk =  " file://0003-binutils-nativesdk-Search-for-alternative-ld.so.conf.patch "

USE_ALTERNATIVES_FOR:class-nativesdk = ""
FILES:${PN}:append:class-nativesdk = " ${bindir}"

BBCLASSEXTEND = "native nativesdk"

FILESEXTRAPATHS:prepend := "${THISDIR}/binutils:"

SRCREV = "116a737f438d03a1bd6aa706b6ea0b4022f3b7e2"

BINUTILS_GIT_URI = "git://sourceware.org/git/binutils-gdb.git;branch=${SRCBRANCH};protocol=http"

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
