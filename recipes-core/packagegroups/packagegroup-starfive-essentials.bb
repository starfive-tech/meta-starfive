SUMMARY = "Essential Packages for StarFive Images."

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
	helloworld \
	coremark \
	dhrystone \
	perf \
	gdb \
	gdbserver \
	util-linux \
	ethtool \
	openssh \
	"

