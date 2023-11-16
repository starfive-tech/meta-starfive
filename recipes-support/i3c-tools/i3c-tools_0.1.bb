DESCRIPTION = "Set of i3c tools"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://i3c-tools \
	"

S = "${WORKDIR}"

do_compile(){
	${CC} ${LDFLAGS} -I ${WORKDIR}/i3c-tools/include/i3c ${WORKDIR}/i3c-tools/i3ctransfer.c -o i3ctransfer
}

do_install(){
	install -d ${D}${bindir}
	install -m 0755 i3ctransfer ${D}${bindir}
}
