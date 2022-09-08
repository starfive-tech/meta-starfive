FORK = "starfive-tech"
BRANCH = "starfive-5.15-dubhe"
#SRCREV = "86f3ed476b1034914fe49f2fc83d60ff75003ccc"
SRCREV = "2450e7aab40813ceb06e1dfa3112cdf73c5aa519"

LINUX_VERSION = "5.15.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
	   file://defconfig \
           file://ext4.patch \
	   file://ext4_dual_bootargs.patch  \
	   "

INITRAMFS_IMAGE_BUNDLE = "${@oe.utils.conditional('ENABLE_EXT4','1','','1',d)}"
INITRAMFS_IMAGE = "${@oe.utils.conditional('ENABLE_EXT4','1','','dubhe-image-initramfs',d)}"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

#FILES:${PN} += "${exec_prefix} ${libdir} ${libdir}/debug ${libdir}/debug/boot ${libdir}/debug/boot/*.debug"
FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"
