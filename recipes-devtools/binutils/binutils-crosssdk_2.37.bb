require binutils-cross_${PV}.bb

inherit crosssdk

PN = "binutils-crosssdk-${SDK_SYS}"

PROVIDES = "virtual/${TARGET_PREFIX}binutils-crosssdk"

SRC_URI += "file://0001-binutils-crosssdk-Generate-relocatable-SDKs.patch"

do_configure:prepend () {
	sed -i 's#/usr/local/lib /lib /usr/lib#${SDKPATHNATIVE}/lib ${SDKPATHNATIVE}/usr/lib /usr/local/lib /lib /usr/lib#' ${S}/ld/configure.tgt
}

FILESEXTRAPATHS:prepend := "${THISDIR}/binutils:"

SRCREV = "116a737f438d03a1bd6aa706b6ea0b4022f3b7e2"

BINUTILS_GIT_URI = "git://sourceware.org/git/binutils-gdb.git;branch=${SRCBRANCH};protocol=git"

