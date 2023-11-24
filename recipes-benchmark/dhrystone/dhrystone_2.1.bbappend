FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Remove-prompt.patch"

# Best effort
CFLAGS = "-O2 -DTIME -Wno-implicit -fno-builtin-printf -fno-common -static -mcmodel=medany -ffast-math -fno-common -falign-jumps=8 -falign-loops=8 -falign-functions=8 -DDHRY_ITERS=20000000"

# Legal
#CFLAGS = "-O2 -DTIME -Wno-implicit -fno-builtin-printf -fno-common -static -mcmodel=medany -ffast-math -fno-common -falign-jumps=8 -falign-loops=8 -falign-functions=8 -fno-inline -DDHRY_ITERS=20000000"

do_install:append() {
	mv ${D}${bindir}/dhry ${D}${bindir}/dhrystone
}
