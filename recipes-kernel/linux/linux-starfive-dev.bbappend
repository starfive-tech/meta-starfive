FORK = "starfive-tech"
BRANCH = "starfive-6.1-dubhe"
SRCREV = "0328291313ba8ad2cd6ac94df9039f2f4a365b6f"

LINUX_VERSION = "6.1.20"
LINUX_VERSION_EXTENSTION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
	   file://cpio.cfg \
           "

INITRAMFS_IMAGE_BUNDLE = "${@oe.utils.conditional('ENABLE_INIT','1','1','',d)}"
INITRAMFS_IMAGE = "${@oe.utils.conditional('ENABLE_INIT','1','dubhe-image-initramfs','',d)}"

# Temporary remove the patch to update the kernel, will create new patches after that
SRC_URI:append = "${@oe.utils.conditional('ENABLE_EXT4','1','file://ext4.patch','',d)}"
SRC_URI:append = "${@oe.utils.conditional('ENABLE_UBI','1','file://ubi.patch','',d)}"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_compile[nostamp] = "1"
