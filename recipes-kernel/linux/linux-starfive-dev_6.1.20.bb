require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "StarFive Dubhe kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

FORK:starfive-dubhe = "starfive-tech"
BRANCH:starfive-dubhe = "starfive-6.1-dubhe"
SRCREV:starfive-dubhe = "1e3b15b8f416e8d26af7bff467d8debc3f1f5fd9"

FORK:starfive-jh8100 = "starfive-tech"
BRANCH:starfive-jh8100 = "starfive-6.1-dev-jh8100-bmc"
SRCREV:starfive-jh8100 ="602adfee5440add362c13535b9f7d4d2647e2526"

LINUX_VERSION ?= "6.1.20"
LINUX_VERSION:starfive-dubhe = "6.1.20"
LINUX_VERSION:starfive-jh8100 = "6.1.20"

LINUX_VERSION_EXTENSTION:append:starfive-dubhe = "-starlight"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:starfive-dubhe = " \
        git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH} \
        file://cpio.cfg \
	file://add-dubhe-additional-dtb.patch \
        "
SRC_URI:starfive-jh8100 = "git://git@192.168.110.45/${FORK}/linux.git;protocol=ssh;branch=${BRANCH}"


INITRAMFS_IMAGE_BUNDLE:starfive-dubhe = "1"
INITRAMFS_IMAGE:starfive-dubhe = "dubhe-image-initramfs"

KBUILD_DEFCONFIG:starfive-dubhe = "starfive_dubhe_defconfig"
KBUILD_DEFCONFIG:starfive-jh8100 = "jh8100_defconfig"

COMPATIBLE_MACHINE = "(starfive-dubhe|starfive-jh8100)"

FILES:${KERNEL_PACKAGE_NAME}-base += "/usr/*"

BOOTARGS_EXT4 = "console=ttySIF0,115200 earlycon=sbi root=/dev/mmcblk0p2 rw rootfstype=ext4 rootwait ip=:::255.255.255.0::eth0:dhcp"
BOOTARGS_UBI = "console=ttySIF0,115200 earlycon=sbi ip=:::255.255.255.0::eth0:dhcp root=ubi0:starfive-dubhe-rootfs ubi.mtd=1 rw rootfstype=ubifs rootwait"
BOOTARGS_NFS = "console=ttySIF0,115200 earlycon=sbi root=/dev/nfs rw nfsroot=192.168.1.1:/filepath,rw,tcp,vers=3 ip=:::255.255.255.0::eth0:dhcp rootfstype=ext4 rootwait"

do_configure:append:starfive-dubhe() {
	cp ${S}/arch/riscv/boot/dts/starfive/dubhe80_fpga.dts ${S}/arch/riscv/boot/dts/starfive/dubhe80_fpga_ext4.dts
        cp ${S}/arch/riscv/boot/dts/starfive/dubhe80_fpga.dts ${S}/arch/riscv/boot/dts/starfive/dubhe80_fpga_ubi.dts
        cp ${S}/arch/riscv/boot/dts/starfive/dubhe80_fpga.dts ${S}/arch/riscv/boot/dts/starfive/dubhe80_fpga_nfs.dts

	cp ${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga.dts ${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga_ext4.dts
	cp ${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga.dts ${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga_ubi.dts
	cp ${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga.dts ${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga_nfs.dts

	for dts_file in ${S}/arch/riscv/boot/dts/starfive/dubhe*0_fpga_*.dts; do
		if [ "$dts_file" = "${S}/arch/riscv/boot/dts/starfive/dubhe90_fpga_dual.dts" ]; then
			continue
		fi
		echo "/ {" >> "$dts_file"
		echo "    chosen {" >> "$dts_file"
		echo "        bootargs" >> "$dts_file"
		echo "    };" >> "$dts_file"
		echo "};" >> "$dts_file"
	done

	sed -i "s+bootargs+bootargs = \"${BOOTARGS_EXT4}\";+g" ${S}/arch/riscv/boot/dts/starfive/dubhe*0_fpga_ext4.dts
	sed -i "s+bootargs+bootargs = \"${BOOTARGS_UBI}\";+g" ${S}/arch/riscv/boot/dts/starfive/dubhe*0_fpga_ubi.dts
	sed -i "s+bootargs+bootargs = \"${BOOTARGS_NFS}\";+g" ${S}/arch/riscv/boot/dts/starfive/dubhe*0_fpga_nfs.dts
}
