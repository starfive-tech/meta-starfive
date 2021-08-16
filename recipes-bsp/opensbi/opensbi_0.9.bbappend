SRC_URI = "git://git@192.168.110.45/starfive-tech/opensbi.git;protocol=ssh;branch=starfive-dubhe"
SRCREV = "4ad25b455be55b31f38404e82225e6d757704e26"

INSANE_SKIP_${PN}-dev = "ldflags"
INSANE_SKIP_${PN} = "ldflags"
