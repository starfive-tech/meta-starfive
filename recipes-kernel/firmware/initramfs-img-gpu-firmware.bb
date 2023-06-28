SUMMARY = "IMG GPU firmware installation recipe"
DESCRIPTION = "Install IMG GPU firmware to the initramfs"
LICENSE = "CLOSED"

DEPENDS += "visionfive2-pvr-graphics"

FILES:${PN} += "/lib/*"

do_install:append() {
        install -d ${D}/lib/firmware
        install -m 0755 ${DEPLOY_DIR}/lib/firmware/* ${D}/lib/firmware
}

INSANE_SKIP:${PN} += "already-stripped arch"
