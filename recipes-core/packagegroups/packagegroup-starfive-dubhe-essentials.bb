SUMMARY = "Packages required by StarFive Dubhe"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
	atftp \
	iperf3 \
	dhcpcd \
	kvmtool \
	tmux \
	"
