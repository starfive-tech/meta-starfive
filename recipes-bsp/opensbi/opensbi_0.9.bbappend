SRC_URI = "git://git@192.168.110.45/starfive-tech/opensbi;protocol=ssh;branch=starfive-dubhe"
SRCREV = "b44fd539c9ac9a53f248986a5cb4c9becf84e08d"

INSANE_SKIP_${PN}-dev = "ldflags"
INSANE_SKIP_${PN} = "ldflags"
