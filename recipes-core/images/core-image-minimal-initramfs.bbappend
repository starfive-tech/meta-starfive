# Install IMG GPU firmware into the initramfs
PACKAGE_INSTALL += "initramfs-img-gpu-firmware"

# Add riscv64-oe-linux as a compatible hosts
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*|riscv64.*)-(linux.*|freebsd.*)'

# Avoid circular dependencies
EXTRA_IMAGEDEPENDS = ""
