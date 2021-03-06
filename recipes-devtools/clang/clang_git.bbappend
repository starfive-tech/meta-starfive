MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

LDFLAGS:remove:class-nativesdk = "-fuse-ld=lld"

FILES:${PN} += "${libdir}/*"

PACKAGECONFIG:append:class-native = " bootstrap"

EXTRA_OECMAKE:append:class-native = " \
			-DCLANG_VENDOR="StarFive" \
			-DCLANG_ENABLE_ARCMT=OFF \
			-DCLANG_ENABLE_STATIC_ANALYZER=OFF \
			-DCLANG_PLUGIN_SUPPORT=OFF \
			-DCMAKE_BUILD_TYPE=Release \
			-DLLVM_ENABLE_BINDINGS=OFF \
			-DLLVM_ENABLE_PLUGINS=ON \
			-DLLVM_ENABLE_OCAMLDOC=OFF \
			-DLLVM_ENABLE_TERMINFO=OFF \
			-DLLVM_INCLUDE_DOCS=OFF \
			-DLLVM_INCLUDE_EXAMPLES=OFF \
			-DLLVM_INCLUDE_TESTS=ON \
			-DLLVM_ENABLE_WARNINGS=OFF \
			-DLLVM_ENABLE_ASSERTIONS=TRUE \
			-DLLVM_INCLUDE_UTILS=ON \
			-DLLDB_PYTHON_RELATIVE_PATH="recipe-sysroot-native/usr/bin/python3-native/" \
			-DBOOTSTRAP_CMAKE_BUILD_TYPE=Release \
			-DBOOTSTRAP_LLVM_APPEND_VC_REV=OFF \
			-DBOOTSTRAP_LLVM_INSTALL_TOOLCHAIN_ONLY=ON \
			-DBOOTSTRAP_COMPILER_RT_USE_BUILTINS_LIBRARY=ON \
			-DBOOTSTRAP_LLVM_TARGETS_TO_BUILD="all" \
			-DBOOTSTRAP_LLVM_INCLUDE_UTILS=ON \
			-DBOOTSTRAP_LLVM_BUILD_TOOLS=ON \
			-DBOOTSTRAP_LLVM_ENABLE_PROJECTS="clang" \
			"

do_install:append:class-native () {
    install -Dm 0755 ${B}${BINPATHPREFIX}/bin/llvm-tblgen ${D}${bindir}/llvm-tblgen
}
