SUMMARY = "Deploy Boot files recipe"
DESCRIPTION = "Recipe to deploy boot files to the deploy directory"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM = ""

SRC_URI = "\
	file://bootcode.bin \
	file://bootcode_min.bin \
	file://bootjump.bin \
	"

S = "${UNPACKDIR}"

inherit deploy

do_deploy(){
	install -m 755 ${UNPACKDIR}/bootcode.bin ${DEPLOYDIR}
	install -m 755 ${UNPACKDIR}/bootcode_min.bin ${DEPLOYDIR}
	install -m 755 ${UNPACKDIR}/bootjump.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_install
