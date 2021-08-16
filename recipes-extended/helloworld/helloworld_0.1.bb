SUMMARY = "Hello World recipe"
DESCRIPTION = "Sample helloworld recipe"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM = ""

SRC_URI = "file://helloworld.c \
	"

S = "${WORKDIR}"

do_compile(){
	${CC} ${CFLAGS} ${LDFLAGS} helloworld.c -o helloworld
}

do_install(){
	install -d ${D}${bindir}
	install -m 0755 helloworld ${D}${bindir}
} 
