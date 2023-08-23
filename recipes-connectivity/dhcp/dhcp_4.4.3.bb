require dhcp.inc

SRC_URI += "file://0001-define-macro-_PATH_DHCPD_CONF-and-_PATH_DHCLIENT_CON.patch \
            file://0002-dhclient-dbus.patch \
            file://0003-link-with-lcrypto.patch \
            file://0004-Fix-out-of-tree-builds.patch \
            file://0005-dhcp-client-fix-invoke-dhclient-script-failed-on-Rea.patch \
            file://0007-Add-configure-argument-to-make-the-libxml2-dependenc.patch \
            file://0009-remove-dhclient-script-bash-dependency.patch \
            file://0012-dhcp-correct-the-intention-for-xml2-lib-search.patch \
            file://0013-fixup_use_libbind.patch \
            file://0001-workaround-busybox-limitation-in-linux-dhclient-script.patch \
"

SRC_URI[md5sum] = "9076af4cc1293dde5a7c6cae7de6ab45"
SRC_URI[sha256sum] = "0e3ec6b4c2a05ec0148874bcd999a66d05518378d77421f607fb0bc9d0135818"

LDFLAGS:append = " -pthread"

PACKAGECONFIG ?= "systemd"
PACKAGECONFIG[bind-httpstats] = "--with-libxml2,--without-libxml2,libxml2"
PACKAGECONFIG[systemd] = "--with-systemd,--without-systemd,systemd"

CFLAGS += "-fcommon"

python dhcp_split_compat() {
    do_split_packages(d,
                      root = '${libdir}',
                      file_regex = r'^lib(.*)\.so\..*',
                      output_pattern = '${PN}-lib%s',
                      description = 'dhcp %s library',
                      extra_depends = '',
                      allow_links = True)
}

PACKAGES:remove = "dhcp-libs"
PACKAGES_DYNAMIC = "^${PN}-lib.*"

PACKAGE_PREPROCESS_FUNCS += "dhcp_split_compat"
