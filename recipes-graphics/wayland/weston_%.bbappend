FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-backend-drm-disable-bo-geometry-out-of-bounds-messag.patch \
	file://0001-tests-Add-dependency-on-screenshooter-client-protocol.patch \
	file://0002-meson-fix-failure-to-find-libudev-when-linking-the-c.patch \
	file://0003-libweston-reduce-checks-for-dmabufs-with-DRM-modifie.patch \
	file://0004-tests-test-client-depends-on-libudev.patch \
	file://0005-backend-drm-allow-linear-framebuffers-if-no-KMS-modi.patch \
	file://0006-weston-set-primary-drm.patch \
	file://0007-support-mirror-mode.patch \
	file://0008-weston-support-hotplug.patch \
	file://0009-weston-add-input-event.patch \
	file://0010-weston-calibrate-touch-coordinate.patch \ 
"


