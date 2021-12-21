MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

FILES:${PN} += "${libdir}/*"

FILES:${PN}-staticdev += "${nonarch_libdir}/*/*.a"
FILES:${PN}-dev += "${nonarch_libdir}/*/*.so \"
