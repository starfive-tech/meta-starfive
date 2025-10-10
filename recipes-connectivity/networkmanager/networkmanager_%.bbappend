do_install:append() {
    # Mask NetworkManager
    install -d ${D}${sysconfdir}/systemd/system
    ln -s /dev/null ${D}${sysconfdir}/systemd/system/NetworkManager.service
}
