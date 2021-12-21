MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

FILES:${PN} += "${libdir}/*"

do_install:append(){
	mkdir -p ${D}${nonarch_libdir}/clang/14.0.0/lib/linux
        mv ${D}${nonarch_libdir}/clang/14.0.0/lib/riscv64-oe-linux/libclang_rt.builtins.a ${D}${nonarch_libdir}/clang/14.0.0/lib/linux/libclang_rt.builtins-riscv64.a
        rm -rf ${D}${nonarch_libdir}/clang/14.0.0/lib/riscv64-oe-linux
}
