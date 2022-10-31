FORK = "starfive-tech"
BRANCH = "starfive-5.15-dubhe"
SRCREV = "2450e7aab40813ceb06e1dfa3112cdf73c5aa519"

LINUX_VERSION = "5.15.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_TMP_B = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
	   file://defconfig \
           file://ext4.patch \
	   file://ext4_dual_bootargs.patch \
	   "

SRC_TMP_A = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
           file://defconfig \
	   file://ubifs-single-core.patch \
	   file://ubifs-dual-core.patch \
           "

INITRAMFS_IMAGE_BUNDLE = "${@oe.utils.conditional('ENABLE_EXT4','1','','1',d)}"
INITRAMFS_IMAGE = "${@oe.utils.conditional('ENABLE_EXT4','1','','dubhe-image-initramfs',d)}"

SRC_URI = "${@oe.utils.conditional('ENABLE_UBI','1','${SRC_TMP_A}','${SRC_TMP_B}',d)}"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

#FILES:${PN} += "${exec_prefix} ${libdir} ${libdir}/debug ${libdir}/debug/boot ${libdir}/debug/boot/*.debug"
FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_compile[nostamp] = "1"
