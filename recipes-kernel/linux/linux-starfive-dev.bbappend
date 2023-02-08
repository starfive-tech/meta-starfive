FORK = "starfive-tech"
BRANCH = "starfive-5.15-dubhe"
SRCREV = "2eba892dd9f73866999c19339a320f5e98068aeb"

LINUX_VERSION = "5.15.0"
LINUX_VERSION_EXTENSION:append = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://git@192.168.110.45/starfive-tech/linux.git;protocol=ssh;branch=${BRANCH} \
           file://defconfig \
           "

INITRAMFS_IMAGE_BUNDLE = "${@oe.utils.conditional('ENABLE_NFS','1','','1',d)}"
INITRAMFS_IMAGE = "${@oe.utils.conditional('ENABLE_NFS','1','','dubhe-image-initramfs',d)}"

# Temporary remove the patch to update the kernel, will create new patches after that
#SRC_URI:append = "${@oe.utils.conditional('ENABLE_NFS','1','file://nfs.patch','file://initramfs.patch',d)}"

#KBUILD_DEFCONFIG_starfive = "starfive_dubhe_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

do_compile[nostamp] = "1"
