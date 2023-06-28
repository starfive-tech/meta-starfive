require sd-image.inc

DEPENDS += " opensbi starfive-tool-native u-boot-tools-native dtc-native"

IMAGE_INSTALL += " \
	helloworld \
	coremark \
	dhrystone \
	perf \
	gdb \
	gdbserver \
	util-linux \
	ethtool \
	bmap-tools \
	systemd-analyze \
	e2fsprogs-resize2fs \
	mesa-pvr \
	visionfive2-pvr-graphics \
	glmark2 \
	ffmpeg \
	libsf-codaj12 \
	libsf-omxil \
	libsf-wave420l \
	libsf-wave511 \
	jpu-module \
	vdec-module \
	venc-module \
	openssh \
	"
