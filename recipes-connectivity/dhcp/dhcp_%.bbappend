FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://dhcp-CLOEXEC.patch \
    file://0027-Add-missed-sd-notify-patch-to-manage-dhcpd-with-syst.patch \
    file://bind.tar.gz;unpack=0 \
"

do_configure:prepend () {
	#Replace bind.tar.gz without riscv64 with our patched one
	rm -f ${S}/bind/bind.tar.gz
	install ${S}/../bind.tar.gz ${S}/bind/
}

do_install:append() {
	rm ${D}/usr/include/irs/resconf.h
	rm -rf ${D}/usr/include/dns/
	rm -rf ${D}/usr/include/isc/
	rm -rf ${D}/usr/include/isccfg/
	rm -f ${D}/usr/include/dst/dst.h
	rm -f ${D}/usr/include/dst/gssapi.h
	rm -f ${D}/usr/lib/libdns.so
	rm -f ${D}/usr/lib/libirs.so
	rm -f ${D}/usr/lib/libisccfg.so
	rm -f ${D}/usr/lib/libisc.so
}
