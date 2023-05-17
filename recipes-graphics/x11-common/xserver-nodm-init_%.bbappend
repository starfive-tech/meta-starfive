FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://xserver-nodm-local.conf.in \
	file://xserver-nodm-local.service.in \
	"

do_install:prepend() {
	# Replace the systemd service & config to change the LD_LIBRARY_PATH & binary location
	cp xserver-nodm-local.conf.in ${THISDIR}/${PN}/xserver-nodm.conf.in
	cp xserver-nodm-local.service.in ${THISDIR}/${PN}/xserver-nodm.service.in
}
