require binutils.inc
require binutils-${PV}.inc

BPN = "binutils"

DEPENDS += "dejagnu-native expect-native"
DEPENDS += "binutils-native"

deltask do_compile
deltask do_install

inherit nopackages

do_configure[dirs] += "${B}/ld ${B}/bfd"
do_configure() {
    # create config.h, oe enables initfini-array by default
    echo "#define HAVE_INITFINI_ARRAY" > ${B}/ld/config.h
}

# target depends
DEPENDS += "virtual/${MLPREFIX}${TARGET_PREFIX}binutils"
DEPENDS += "virtual/${MLPREFIX}${TARGET_PREFIX}gcc"
DEPENDS += "virtual/${MLPREFIX}${TARGET_PREFIX}compilerlibs"
DEPENDS += "virtual/${MLPREFIX}libc"

python check_prepare() {
    def suffix_sys(sys):
        if sys.endswith("-linux"):
            return sys + "-gnu"
        return sys

    def generate_site_exp(d, suite):
        content = []
        content.append('set srcdir "{0}/{1}"'.format(d.getVar("S"), suite))
        content.append('set objdir "{0}/{1}"'.format(d.getVar("B"), suite))
        content.append('set build_alias "{0}"'.format(d.getVar("BUILD_SYS")))
        content.append('set build_triplet {0}'.format(d.getVar("BUILD_SYS")))
        # use BUILD here since HOST=TARGET
        content.append('set host_alias "{0}"'.format(d.getVar("BUILD_SYS")))
        content.append('set host_triplet {0}'.format(d.getVar("BUILD_SYS")))
        content.append('set target_alias "{0}"'.format(d.getVar("TARGET_SYS")))
        content.append('set target_triplet {0}'.format(suffix_sys(d.getVar("TARGET_SYS"))))
        content.append("set development true")
        content.append("set experimental false")

        content.append(d.expand('set CXXFILT "${TARGET_PREFIX}c++filt"'))
        content.append(d.expand('set CC "${TARGET_PREFIX}gcc --sysroot=${STAGING_DIR_TARGET} ${TUNE_CCARGS}"'))
        content.append(d.expand('set CXX "${TARGET_PREFIX}g++ --sysroot=${STAGING_DIR_TARGET} ${TUNE_CCARGS}"'))
        content.append(d.expand('set CFLAGS_FOR_TARGET "--sysroot=${STAGING_DIR_TARGET} ${TUNE_CCARGS}"'))

        if suite == "ld" and d.getVar("TUNE_ARCH") == "mips64":
            # oe patches binutils to have the default mips64 abi as 64bit, but
            # skips gas causing issues with the ld test suite (which uses gas)
            content.append('set ASFLAGS "-64"')

        return "\n".join(content)

    for i in ["binutils", "gas", "ld"]:
        builddir = os.path.join(d.getVar("B"), i)
        if not os.path.isdir(builddir):
            os.makedirs(builddir)
        with open(os.path.join(builddir, "site.exp"), "w") as f:
            f.write(generate_site_exp(d, i))
}

CHECK_TARGETS ??= "binutils gas ld"

do_check[dirs] = "${B} ${B}/binutils ${B}/gas ${B}/ld"
do_check[prefuncs] += "check_prepare"
do_check[nostamp] = "1"
do_check() {
    export LC_ALL=C
    for i in ${CHECK_TARGETS}; do
        (cd ${B}/$i; runtest \
            --tool $i \
            --srcdir ${S}/$i/testsuite \
            --ignore 'plugin.exp' \
            || true)
    done
}
addtask check after do_configure

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
"
