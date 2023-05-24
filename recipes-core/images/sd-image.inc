# Generate SD Card image and eMMC image
# - Using spl_tool to fix img header to boot from eMMC

do_vf2_sd (){
        cd ${IMGDEPLOYDIR}
        bmaptool copy ${PN}-${MACHINE}.wic.gz ${DEPLOY_DIR_IMAGE}/${MACHINE}-${PN}.img
        spl_tool -i -f ${DEPLOY_DIR_IMAGE}/${MACHINE}-${PN}.img
}

IMAGE_POSTPROCESS_COMMAND:starfive-visionfive2 += "do_vf2_sd;"
