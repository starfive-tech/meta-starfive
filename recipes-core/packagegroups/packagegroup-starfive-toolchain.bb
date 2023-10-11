SUMMARY = "Packages required for StarFive toolchain"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
	glibc glibc-dev glibc-staticdev \
	libgomp libgomp-dev libgomp-staticdev \
	alsa-lib alsa-lib-dev \
	libgpiod libgpiod-dev \
	i2c-tools i2c-tools-dev i2c-tools-staticdev \
	libatomic-ops libatomic-ops-dev libatomic-ops-staticdev \
	"
