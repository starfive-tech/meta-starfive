SRC_URI = "git://github.com/starfive-tech/opensbi.git;protocol=https;branch=starfive-v1.0-dubhe"
SRCREV = "066e15f65ca1e71fecf531d0c7c9b49774d414e9"

INSANE_SKIP_${PN}-dev = "ldflags"
INSANE_SKIP_${PN} = "ldflags"
