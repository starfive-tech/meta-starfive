# Image Root Filesystem Configuration

IMAGE_FSTYPES += "ext4 wic wic.gz wic.bmap"
#MKUBIFS_ARGS = "-m 4096 -e 262144 -c 4096"
#UBINIZE_ARGS = "-p 278528 -m 4096 -s 4096"

IMAGE_ROOTFS_SIZE ?= "8192"
#IMAGE_ROOTFS_EXTRA_SPACE = "0"

IMAGE_FEATURES += "allow-empty-password empty-root-password"
IMAGE_FEATURES:remove = "dbg-pkgs"

IMAGE_INSTALL += "mtd-utils helloworld coremark dhrystone perf util-linux btrfs-tools parted lrzsz devmem2 iperf3 e2fsprogs ethtool"
