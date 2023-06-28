LICENSE="CLOSED"

PROVIDES = "virtual/libgles2"
COMPATIBLE_MACHINE = "starfive-visionfive2"

inherit update-rc.d

S = "${WORKDIR}/git"

IMG_GPU_POWERVR_VERSION = "img-gpu-powervr-bin-1.17.6210866"

PACKAGES += " \
    ${PN}-firmware \
    ${PN}-tools \
"

do_install () {
    tar xz --no-same-owner -f ${THISDIR}/files/${IMG_GPU_POWERVR_VERSION}.tar.gz -C ${D}

    # Remove .a files from tar file
    rm ${D}/${IMG_GPU_POWERVR_VERSION}/target/usr/local/lib/*.a
    rm ${D}/${IMG_GPU_POWERVR_VERSION}/target/usr/local/lib/*/*.a

    # Remove files that has dependency on python
    rm ${D}/${IMG_GPU_POWERVR_VERSION}/target/usr/local/bin/pvrlogsplit
    rm ${D}/${IMG_GPU_POWERVR_VERSION}/target/usr/local/bin/hwperfjsonmerge.py

    # Create a copy of GPU firmware at the deploy directory
    cp -r ${D}/${IMG_GPU_POWERVR_VERSION}/target/lib ${DEPLOY_DIR}

    # provided via separate arch-independent firmware package
    mv ${D}/${IMG_GPU_POWERVR_VERSION}/target/* ${D}
    rm -rf ${D}/lib/firmware
    rmdir ${D}/lib

    # cleanup unused
    rm -rf ${D}/${IMG_GPU_POWERVR_VERSION}
}

INITSCRIPT_NAME = "rc.pvr"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/*.so \
    /usr/local/* \
    /usr/lib/* \
"

FILES:${PN}-tools = " \
    ${prefix}/local/bin/ \
"

FILES:${PN}-firmware = " \
    ${base_libdir}/firmware/ \
"

RDEPENDS:${PN} += " \
    bash \
    libdrm \
    glibc \
    libgcc \
    libstdc++ \
    libudev \
"

RDEPENDS:${PN}-tools += " \
    python3 \
"

# remove warning caused by wrong lib directory
INSANE_SKIP:${PN} += "libdir"
INSANE_SKIP:${PN}-dbg += "libdir"

INSANE_SKIP:${PN} += "already-stripped dev-so"
# ignore dependency check for python scripting
INSANE_SKIP:${PN}-tools += "already-stripped file-rdeps"
INSANE_SKIP:${PN}-firmware += "arch"

# ignore "multiple shlib providers" error
EXCLUDE_FROM_SHLIBS = "1"
