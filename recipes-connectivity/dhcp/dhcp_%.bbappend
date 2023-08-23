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
