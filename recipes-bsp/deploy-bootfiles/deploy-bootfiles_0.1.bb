SUMMARY = "QSPI Image Creator"
DESCRIPTION = "Recipe to create a QSPI Image"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM = ""

SRC_URI = "file://bootcode.bin \
           file://bootjump.bin \
          "
          
S = "${WORKDIR}"

inherit deploy
do_deploy(){
	install -m 755 ${WORKDIR}/bootcode.bin ${DEPLOYDIR}/
	install -m 755 ${WORKDIR}/bootjump.bin ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install
