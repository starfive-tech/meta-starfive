SRC_URI:append = " \
    file://0001-autosetup-cc-check-tools-check-only-the-name.patch \
    file://Dubhe_FPGA_openocd.patch \
    file://dubhe_olimex-openocd_s5.cfg \
    file://dubhe_olimex_flash_write.cfg \
    file://jh8100_0150_f1.cfg \
    file://jh8100_0150_f2.cfg \
    file://jh8100_jrc.cfg \
"

inherit deploy

do_deploy() {
	install -m 755 ${UNPACKDIR}/dubhe_olimex-openocd_s5.cfg ${DEPLOYDIR}/
	install -m 755 ${UNPACKDIR}/dubhe_olimex_flash_write.cfg ${DEPLOYDIR}/
	install -m 755 ${UNPACKDIR}/jh8100_0150_f1.cfg ${DEPLOYDIR}/
	install -m 755 ${UNPACKDIR}/jh8100_0150_f2.cfg ${DEPLOYDIR}/
	install -m 755 ${UNPACKDIR}/jh8100_jrc.cfg ${DEPLOYDIR}/
}
addtask deploy before do_build after do_install
