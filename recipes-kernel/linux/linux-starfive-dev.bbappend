FORK = "starfive-tech"
BRANCH = "starfive-5.15-dubhe"
SRCREV = "0f9fd42752277456a1f324b773877ac28aa9290b"

LINUX_VERSION = "5.15.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_TMP_B = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
	   file://defconfig \
           file://nfs.patch \
	   "

SRC_TMP_A = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
           file://defconfig \
	   file://initramfs.patch \
           "

INITRAMFS_IMAGE_BUNDLE = "${@oe.utils.conditional('ENABLE_NFS','1','','1',d)}"
INITRAMFS_IMAGE = "${@oe.utils.conditional('ENABLE_NFS','1','','dubhe-image-initramfs',d)}"

SRC_URI = "${@oe.utils.conditional('ENABLE_NFS','1','${SRC_TMP_B}','${SRC_TMP_A}',d)}"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_compile[nostamp] = "1"
