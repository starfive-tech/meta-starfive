DEPENDS += " opensbi starfive-tool-native u-boot-tools-native dtc-native"

IMAGE_INSTALL += "helloworld coremark dhrystone perf gdb gdbserver util-linux ethtool bmap-tools systemd-analyze"

# Generate SD Card image and eMMC image
# - Using spl_tool to fix img header to boot from eMMC

do_vf2_sd (){
	cd ${IMGDEPLOYDIR}
	bmaptool copy core-image-minimal-starfive-visionfive2.wic.gz ${DEPLOY_DIR_IMAGE}/starfive-visionfive2-core-image-minimal.img
	spl_tool -i -f ${DEPLOY_DIR_IMAGE}/starfive-visionfive2-core-image-minimal.img
}

IMAGE_POSTPROCESS_COMMAND:starfive-visionfive2 += "do_vf2_sd;"
