MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

LDFLAGS:remove:class-nativesdk = "-fuse-ld=lld"

FILES_${PN} += "${libdir}/*"
