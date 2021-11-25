MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

FILES_${PN} += "${libdir}/*"

FILES_${PN}-staticdev += "${nonarch_libdir}/*/*.a"
FILES_${PN}-dev += "${nonarch_libdir}/*/*.so \"
