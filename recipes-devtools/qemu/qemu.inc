SUMMARY = "Fast open source processor emulator"
DESCRIPTION = "QEMU is a hosted virtual machine monitor: it emulates the \
machine's processor through dynamic binary translation and provides a set \
of different hardware and device models for the machine, enabling it to run \
a variety of guest operating systems"
HOMEPAGE = "http://qemu.org"
LICENSE = "GPLv2 & LGPLv2.1"

RDEPENDS_${PN}-ptest = "bash"

require qemu-targets.inc
inherit pkgconfig ptest

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=8c5efda6cf1e1b03dcfd0e6c0d271c7f"

SRC_URI = "https://download.qemu.org/${BPN}-${PV}.tar.xz \
           file://powerpc_rom.bin \
           file://run-ptest \
           file://0001-qemu-Add-missing-wacom-HID-descriptor.patch \
           file://0003-qemu-Add-addition-environment-space-to-boot-loader-q.patch \
           file://0004-qemu-disable-Valgrind.patch \
           file://0006-chardev-connect-socket-to-a-spawned-command.patch \
           file://0007-apic-fixup-fallthrough-to-PIC.patch \
           file://0010-configure-Add-pkg-config-handling-for-libgcrypt.patch \
           file://0001-Add-enable-disable-udev.patch \
           file://0001-qemu-Do-not-include-file-if-not-exists.patch \
           file://mmap2.patch \
           file://determinism.patch \
           file://0001-tests-meson.build-use-relative-path-to-refer-to-file.patch \
           file://0001-configure-fix-detection-of-gdbus-codegen.patch \
           file://0001-vhost-user-gpu-fix-memory-disclosure-in-virgl_cmd_ge.patch \
           file://0002-vhost-user-gpu-fix-resource-leak-in-vg_resource_crea.patch \
           file://0003-vhost-user-gpu-fix-memory-leak-in-vg_resource_attach.patch \
           file://0004-vhost-user-gpu-fix-memory-leak-while-calling-vg_reso.patch \
           file://0005-vhost-user-gpu-fix-memory-leak-in-virgl_cmd_resource.patch \
           file://0006-vhost-user-gpu-fix-memory-leak-in-virgl_resource_att.patch \
           file://0007-vhost-user-gpu-fix-OOB-write-in-virgl_cmd_get_capset.patch \
           file://0001-linux-user-Tag-vsx-with-ieee128-fpbits.patch \
           file://CVE-2021-3527-1.patch \
           file://CVE-2021-3527-2.patch \
           file://0001-softfloat-add-APIs-to-handle-alternative-sNaN-propag.patch \
           file://0002-target-riscv-change-the-api-for-single-double-fmin-f.patch \
           file://0003-target-riscv-support-x-Zfh-in-cpu-option.patch \
           file://0004-target-riscv-Implement-zfh-extension.patch \
           file://0005-target-riscv-fix-TB_FLAGS-bits-overlapping-bug-for-r.patch \
           file://0006-fpu-softfloat-set-invalid-excp-flag-for-RISC-V-mulad.patch \
           file://0007-target-riscv-Fixup-saturate-subtract-function.patch \
           file://0008-target-riscv-fix-vrgather-macro-index-variable-type-.patch \
           file://0009-target-riscv-drop-vector-0.7.1-and-add-1.0-support.patch \
           file://0010-target-riscv-Use-FIELD_EX32-to-extract-wd-field.patch \
           file://0011-target-riscv-rvv-1.0-add-mstatus-VS-field.patch \
           file://0012-target-riscv-rvv-1.0-add-sstatus-VS-field.patch \
           file://0013-target-riscv-rvv-1.0-introduce-writable-misa.v-field.patch \
           file://0014-target-riscv-rvv-1.0-add-translation-time-vector-con.patch \
           file://0015-target-riscv-rvv-1.0-remove-rvv-related-codes-from-f.patch \
           file://0016-target-riscv-rvv-1.0-add-vcsr-register.patch \
           file://0017-target-riscv-rvv-1.0-add-vlenb-register.patch \
           file://0018-target-riscv-rvv-1.0-check-MSTATUS_VS-when-accessing.patch \
           file://0019-target-riscv-rvv-1.0-remove-MLEN-calculations.patch \
           file://0020-target-riscv-rvv-1.0-add-fractional-LMUL.patch \
           file://0021-target-riscv-rvv-1.0-add-VMA-and-VTA.patch \
           file://0022-target-riscv-rvv-1.0-update-check-functions.patch \
           file://0023-target-riscv-introduce-more-imm-value-modes-in-trans.patch \
           file://0024-target-riscv-rvv-1.0-add-translation-time-nan-box-he.patch \
           file://0025-target-riscv-rvv-1.0-configure-instructions.patch \
           file://0026-target-riscv-rvv-1.0-stride-load-and-store-instructi.patch \
           file://0027-target-riscv-rvv-1.0-index-load-and-store-instructio.patch \
           file://0028-target-riscv-rvv-1.0-fix-address-index-overflow-bug-.patch \
           file://0029-target-riscv-rvv-1.0-fault-only-first-unit-stride-lo.patch \
           file://0030-target-riscv-rvv-1.0-amo-operations.patch \
           file://0031-target-riscv-rvv-1.0-load-store-whole-register-instr.patch \
           file://0032-target-riscv-rvv-1.0-update-vext_max_elems-for-load-.patch \
           file://0033-target-riscv-rvv-1.0-take-fractional-LMUL-into-vecto.patch \
           file://0034-target-riscv-rvv-1.0-floating-point-square-root-inst.patch \
           file://0035-target-riscv-rvv-1.0-floating-point-classify-instruc.patch \
           file://0036-target-riscv-rvv-1.0-mask-population-count-instructi.patch \
           file://0037-target-riscv-rvv-1.0-find-first-set-mask-bit-instruc.patch \
           file://0038-target-riscv-rvv-1.0-set-X-first-mask-bit-instructio.patch \
           file://0039-target-riscv-rvv-1.0-iota-instruction.patch \
           file://0040-target-riscv-rvv-1.0-element-index-instruction.patch \
           file://0041-target-riscv-rvv-1.0-allow-load-element-with-sign-ex.patch \
           file://0042-target-riscv-rvv-1.0-register-gather-instructions.patch \
           file://0043-target-riscv-rvv-1.0-integer-scalar-move-instruction.patch \
           file://0044-target-riscv-rvv-1.0-floating-point-move-instruction.patch \
           file://0045-target-riscv-rvv-1.0-floating-point-scalar-move-inst.patch \
           file://0046-target-riscv-rvv-1.0-whole-register-move-instruction.patch \
           file://0047-target-riscv-rvv-1.0-integer-extension-instructions.patch \
           file://0048-target-riscv-rvv-1.0-single-width-averaging-add-and-.patch \
           file://0049-target-riscv-rvv-1.0-single-width-bit-shift-instruct.patch \
           file://0050-target-riscv-rvv-1.0-integer-add-with-carry-subtract.patch \
           file://0051-target-riscv-rvv-1.0-narrowing-integer-right-shift-i.patch \
           file://0052-target-riscv-rvv-1.0-widening-integer-multiply-add-i.patch \
           file://0053-target-riscv-rvv-1.0-single-width-saturating-add-and.patch \
           file://0054-target-riscv-rvv-1.0-integer-comparison-instructions.patch \
           file://0055-target-riscv-rvv-1.0-floating-point-compare-instruct.patch \
           file://0056-target-riscv-rvv-1.0-mask-register-logical-instructi.patch \
           file://0057-target-riscv-rvv-1.0-slide-instructions.patch \
           file://0058-target-riscv-rvv-1.0-floating-point-slide-instructio.patch \
           file://0059-target-riscv-rvv-1.0-narrowing-fixed-point-clip-inst.patch \
           file://0060-target-riscv-rvv-1.0-single-width-floating-point-red.patch \
           file://0061-target-riscv-rvv-1.0-widening-floating-point-reducti.patch \
           file://0062-target-riscv-rvv-1.0-single-width-scaling-shift-inst.patch \
           file://0063-target-riscv-rvv-1.0-remove-widening-saturating-scal.patch \
           file://0064-target-riscv-rvv-1.0-remove-vmford.vv-and-vmford.vf.patch \
           file://0065-target-riscv-rvv-1.0-remove-integer-extract-instruct.patch \
           file://0066-target-riscv-rvv-1.0-floating-point-min-max-instruct.patch \
           file://0067-target-riscv-introduce-floating-point-rounding-mode-.patch \
           file://0068-target-riscv-rvv-1.0-floating-point-integer-type-con.patch \
           file://0069-target-riscv-rvv-1.0-widening-floating-point-integer.patch \
           file://0070-target-riscv-add-set-round-to-odd-rounding-mode-help.patch \
           file://0071-target-riscv-rvv-1.0-narrowing-floating-point-intege.patch \
           file://0072-target-riscv-rvv-1.0-relax-RV_VLEN_MAX-to-1024-bits.patch \
           file://0073-target-riscv-rvv-1.0-implement-vstart-CSR.patch \
           file://0074-target-riscv-rvv-1.0-trigger-illegal-instruction-exc.patch \
           file://0075-target-riscv-rvv-1.0-set-mstatus.SD-bit-when-writing.patch \
           file://0076-target-riscv-gdb-support-vector-registers-for-rv64-r.patch \
           file://0077-target-riscv-rvv-1.0-floating-point-reciprocal-squar.patch \
           file://0078-target-riscv-rvv-1.0-floating-point-reciprocal-estim.patch \
           file://0079-target-riscv-set-mstatus.SD-bit-when-writing-fp-CSRs.patch \
           file://0080-target-riscv-rvv-1.0-rename-r2_zimm-to-r2_zimm11.patch \
           file://0081-target-riscv-rvv-1.0-add-vsetivli-instruction.patch \
           file://0082-target-riscv-rvv-1.0-add-evl-parameter-to-vext_ldst_.patch \
           file://0083-target-riscv-rvv-1.0-add-vector-unit-stride-mask-loa.patch \
           file://0084-target-riscv-rvv-1.0-patch-floating-point-reduction-.patch \
           file://0085-target-riscv-reformat-sh-format-encoding-for-B-exten.patch \
           file://0086-target-riscv-rvb-count-leading-trailing-zeros.patch \
           file://0087-target-riscv-rvb-count-bits-set.patch \
           file://0088-target-riscv-rvb-logic-with-negate.patch \
           file://0089-target-riscv-rvb-pack-two-words-into-one-register.patch \
           file://0090-target-riscv-rvb-min-max-instructions.patch \
           file://0091-target-riscv-rvb-sign-extend-instructions.patch \
           file://0092-target-riscv-add-gen_shifti-and-gen_shiftiw-helper-f.patch \
           file://0093-target-riscv-rvb-single-bit-instructions.patch \
           file://0094-target-riscv-rvb-shift-ones.patch \
           file://0095-target-riscv-rvb-rotate-left-right.patch \
           file://0096-target-riscv-rvb-generalized-reverse.patch \
           file://0097-target-riscv-rvb-generalized-or-combine.patch \
           file://0098-target-riscv-rvb-address-calculation.patch \
           file://0099-target-riscv-rvb-add-shift-with-prefix-zero-extend.patch \
           file://0100-target-riscv-rvb-support-and-turn-on-B-extension-fro.patch \
           file://0101-target-riscv-rvb-add-b-ext-version-cpu-option.patch \
           file://0102-target-riscv-fix-REQUIRE_ZFH-macro-bug.patch \
           file://0103-linux-user-elfload-Implement-ELF_HWCAP-for-RISC-V.patch \
           file://0104-target-riscv-Pass-the-same-value-to-oprsz-and-maxsz.patch \
           file://0105-target-riscv-Backup-restore-mstatus.SD-bit-when-virt.patch \
           file://0106-target-riscv-Force-to-set-mstatus_hs.-SD-FS-bits-in-.patch \
           file://0107-target-riscv-Force-to-set-mstatus_hs.-SD-VS-bits-in-.patch \
           file://0001-merge-riscv-bitmapip-b0p94-version.patch \
           file://0002-Add-four-cache-csr-instruction.patch \
           "
UPSTREAM_CHECK_REGEX = "qemu-(?P<pver>\d+(\.\d+)+)\.tar"

SRC_URI[sha256sum] = "87bc1a471ca24b97e7005711066007d443423d19aacda3d442558ae032fa30b9"

SRC_URI_append_class-target = " file://cross.patch"
SRC_URI_append_class-nativesdk = " file://cross.patch"

# Applies against virglrender < 0.6.0 and not qemu itself
CVE_CHECK_WHITELIST += "CVE-2017-5957"

# The VNC server can expose host files uder some circumstances. We don't
# enable it by default.
CVE_CHECK_WHITELIST += "CVE-2007-0998"

# 'The issues identified by this CVE were determined to not constitute a vulnerability.'
# https://bugzilla.redhat.com/show_bug.cgi?id=1609015#c11
CVE_CHECK_WHITELIST += "CVE-2018-18438"

COMPATIBLE_HOST_mipsarchn32 = "null"
COMPATIBLE_HOST_mipsarchn64 = "null"

# Per https://lists.nongnu.org/archive/html/qemu-devel/2020-09/msg03873.html
# upstream states qemu doesn't work without optimization
DEBUG_BUILD = "0"

do_install_append() {
    # Prevent QA warnings about installed ${localstatedir}/run
    if [ -d ${D}${localstatedir}/run ]; then rmdir ${D}${localstatedir}/run; fi
}

do_install_ptest() {
	cp -rL ${B}/tests ${D}${PTEST_PATH}
	find ${D}${PTEST_PATH}/tests -type f -name "*.[Sshcodp]" | xargs -i rm -rf {}

	# Don't check the file genreated by configure
	sed -i -e "1s,#!/usr/bin/bash,#!${base_bindir}/bash," ${D}${PTEST_PATH}/tests/data/acpi/disassemle-aml.sh

	# Strip the paths from the QEMU variable, we can use PATH
	sed -i -e "s#^QEMU=.*/qemu-#QEMU=qemu-#g" ${D}${PTEST_PATH}/tests/tcg/*.mak
}

# QEMU_TARGETS is overridable variable
QEMU_TARGETS ?= "arm aarch64 i386 mips mipsel mips64 mips64el ppc ppc64 ppc64le riscv32 riscv64 sh4 x86_64"

EXTRA_OECONF = " \
    --prefix=${prefix} \
    --bindir=${bindir} \
    --includedir=${includedir} \
    --libdir=${libdir} \
    --mandir=${mandir} \
    --datadir=${datadir} \
    --docdir=${docdir}/${BPN} \
    --sysconfdir=${sysconfdir} \
    --libexecdir=${libexecdir} \
    --localstatedir=${localstatedir} \
    --with-suffix=${BPN} \
    --disable-strip \
    --disable-werror \
    --extra-cflags='${CFLAGS}' \
    --extra-ldflags='${LDFLAGS}' \
    --with-git=/bin/false \
    --with-git-submodules=ignore \
    --meson=meson \
    ${PACKAGECONFIG_CONFARGS} \
    "

export LIBTOOL="${HOST_SYS}-libtool"

B = "${WORKDIR}/build"

#EXTRA_OECONF_append = " --python=${HOSTTOOLS_DIR}/python3"

do_configure_prepend_class-native() {
	# Append build host pkg-config paths for native target since the host may provide sdl
	BHOST_PKGCONFIG_PATH=$(PATH=/usr/bin:/bin pkg-config --variable pc_path pkg-config || echo "")
	if [ ! -z "$BHOST_PKGCONFIG_PATH" ]; then
		export PKG_CONFIG_PATH=$PKG_CONFIG_PATH:$BHOST_PKGCONFIG_PATH
	fi
}

do_configure() {
    ${S}/configure ${EXTRA_OECONF}
}
do_configure[cleandirs] += "${B}"

do_install () {
	export STRIP=""
	oe_runmake 'DESTDIR=${D}' install
}

# The following fragment will create a wrapper for qemu-mips user emulation
# binary in order to work around a segmentation fault issue. Basically, by
# default, the reserved virtual address space for 32-on-64 bit is set to 4GB.
# This will trigger a MMU access fault in the virtual CPU. With this change,
# the qemu-mips works fine.
# IMPORTANT: This piece needs to be removed once the root cause is fixed!
do_install_append() {
	if [ -e "${D}/${bindir}/qemu-mips" ]; then
		create_wrapper ${D}/${bindir}/qemu-mips \
			QEMU_RESERVED_VA=0x0
	fi
}
# END of qemu-mips workaround

make_qemu_wrapper() {
        gdk_pixbuf_module_file=`pkg-config --variable=gdk_pixbuf_cache_file gdk-pixbuf-2.0`

        for tool in `ls ${D}${bindir}/qemu-system-*`; do
                create_wrapper $tool \
                        GDK_PIXBUF_MODULE_FILE=$gdk_pixbuf_module_file \
                        FONTCONFIG_PATH=/etc/fonts \
                        GTK_THEME=Adwaita
        done
}

# Disable kvm/virgl/mesa on targets that do not support it
PACKAGECONFIG_remove_darwin = "kvm virglrenderer glx gtk+"
PACKAGECONFIG_remove_mingw32 = "kvm virglrenderer glx gtk+"

PACKAGECONFIG[sdl] = "--enable-sdl,--disable-sdl,libsdl2"
PACKAGECONFIG[virtfs] = "--enable-virtfs --enable-attr --enable-cap-ng,--disable-virtfs,libcap-ng attr,"
PACKAGECONFIG[aio] = "--enable-linux-aio,--disable-linux-aio,libaio,"
PACKAGECONFIG[xfs] = "--enable-xfsctl,--disable-xfsctl,xfsprogs,"
PACKAGECONFIG[xen] = "--enable-xen,--disable-xen,xen-tools,xen-tools-libxenstore xen-tools-libxenctrl xen-tools-libxenguest"
PACKAGECONFIG[vnc-sasl] = "--enable-vnc --enable-vnc-sasl,--disable-vnc-sasl,cyrus-sasl,"
PACKAGECONFIG[vnc-jpeg] = "--enable-vnc --enable-vnc-jpeg,--disable-vnc-jpeg,jpeg,"
PACKAGECONFIG[vnc-png] = "--enable-vnc --enable-vnc-png,--disable-vnc-png,libpng,"
PACKAGECONFIG[libcurl] = "--enable-curl,--disable-curl,curl,"
PACKAGECONFIG[nss] = "--enable-smartcard,--disable-smartcard,nss,"
PACKAGECONFIG[curses] = "--enable-curses,--disable-curses,ncurses,"
PACKAGECONFIG[gtk+] = "--enable-gtk,--disable-gtk,gtk+3 gettext-native"
PACKAGECONFIG[vte] = "--enable-vte,--disable-vte,vte gettext-native"
PACKAGECONFIG[libcap-ng] = "--enable-cap-ng,--disable-cap-ng,libcap-ng,"
PACKAGECONFIG[ssh] = "--enable-libssh,--disable-libssh,libssh,"
PACKAGECONFIG[gcrypt] = "--enable-gcrypt,--disable-gcrypt,libgcrypt,"
PACKAGECONFIG[nettle] = "--enable-nettle,--disable-nettle,nettle"
PACKAGECONFIG[libusb] = "--enable-libusb,--disable-libusb,libusb1"
PACKAGECONFIG[fdt] = "--enable-fdt,--disable-fdt,dtc"
PACKAGECONFIG[alsa] = "--audio-drv-list='oss alsa',,alsa-lib"
PACKAGECONFIG[glx] = "--enable-opengl,--disable-opengl,virtual/libgl"
PACKAGECONFIG[lzo] = "--enable-lzo,--disable-lzo,lzo"
PACKAGECONFIG[numa] = "--enable-numa,--disable-numa,numactl"
PACKAGECONFIG[gnutls] = "--enable-gnutls,--disable-gnutls,gnutls"
PACKAGECONFIG[bzip2] = "--enable-bzip2,--disable-bzip2,bzip2"
PACKAGECONFIG[libiscsi] = "--enable-libiscsi,--disable-libiscsi"
PACKAGECONFIG[kvm] = "--enable-kvm,--disable-kvm"
PACKAGECONFIG[virglrenderer] = "--enable-virglrenderer,--disable-virglrenderer,virglrenderer"
# spice will be in meta-networking layer
PACKAGECONFIG[spice] = "--enable-spice,--disable-spice,spice"
# usbredir will be in meta-networking layer
PACKAGECONFIG[usb-redir] = "--enable-usb-redir,--disable-usb-redir,usbredir"
PACKAGECONFIG[snappy] = "--enable-snappy,--disable-snappy,snappy"
PACKAGECONFIG[glusterfs] = "--enable-glusterfs,--disable-glusterfs,glusterfs"
PACKAGECONFIG[xkbcommon] = "--enable-xkbcommon,--disable-xkbcommon,libxkbcommon"
PACKAGECONFIG[libudev] = "--enable-libudev,--disable-libudev,eudev"
PACKAGECONFIG[libxml2] = "--enable-libxml2,--disable-libxml2,libxml2"
PACKAGECONFIG[attr] = "--enable-attr,--disable-attr,attr,"
PACKAGECONFIG[rbd] = "--enable-rbd,--disable-rbd,ceph,ceph"
PACKAGECONFIG[vhost] = "--enable-vhost-net,--disable-vhost-net,,"
PACKAGECONFIG[ust] = "--enable-trace-backend=ust,--enable-trace-backend=nop,lttng-ust,"
PACKAGECONFIG[pie] = "--enable-pie,--disable-pie,,"
PACKAGECONFIG[seccomp] = "--enable-seccomp,--disable-seccomp,libseccomp"

INSANE_SKIP_${PN} = "arch"

FILES_${PN} += "${datadir}/icons"
