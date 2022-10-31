#!/bin/bash
# Bootstrapper for buildbot slave

DIR="build"
MACHINE="starfive-dubhe"
CONFFILE="conf/auto.conf"
BITBAKEIMAGE="virtual/kernel"

# clean up the output dir
#echo "Cleaning build dir"
#rm -rf $DIR

# make sure sstate is there
#echo "Creating sstate directory"
#mkdir -p ~/sstate/$MACHINE

# fix permissions set by buildbot
#echo "Fixing permissions for buildbot"
#umask -S u=rwx,g=rx,o=rx
#chmod -R 755 .

# Reconfigure dash on debian-like systems
which aptitude > /dev/null 2>&1
ret=$?
if [ "$(readlink /bin/sh)" = "dash" -a "$ret" = "0" ]; then
  sudo aptitude install expect -y
  expect -c 'spawn sudo dpkg-reconfigure -freadline dash; send "n\n"; interact;'
elif [ "${0##*/}" = "dash" ]; then
  echo "dash as default shell is not supported"
  return
fi
# bootstrap OE
if [[ ":$PATH:" != *":$PWD/meta-starfive:"* ]]; then
  export PATH=$PATH:$PWD/meta-starfive
fi
echo "Init OE"
export BASH_SOURCE="openembedded-core/oe-init-build-env"
. ./openembedded-core/oe-init-build-env $DIR

# Symlink the cache
#echo "Setup symlink for sstate"
#ln -s ~/sstate/${MACHINE} sstate-cache

# add the missing layers
echo "Adding layers"
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-openembedded/meta-multimedia
bitbake-layers add-layer ../meta-openembedded/meta-filesystems
bitbake-layers add-layer ../meta-openembedded/meta-networking
bitbake-layers add-layer ../meta-riscv
bitbake-layers add-layer ../meta-starfive
bitbake-layers add-layer ../meta-clang

# fix the configuration
echo "Creating auto.conf"

if [ -e $CONFFILE ]; then
	rm -rf $CONFFILE
fi
cat <<EOF > $CONFFILE
MACHINE ?= "${MACHINE}"
#IMAGE_FEATURES += "tools-debug"
#IMAGE_FEATURES += "tools-tweaks"
#IMAGE_FEATURES += "dbg-pkgs"
# rootfs for debugging
#IMAGE_GEN_DEBUGFS = "1"
#IMAGE_FSTYPES_DEBUGFS = "tar.gz"
#EXTRA_IMAGE_FEATURES:append = " ssh-server-dropbear"
EXTRA_IMAGE_FEATURES:append = " package-management"
PACKAGECONFIG:append:pn-qemu-native = " sdl"
PACKAGECONFIG:append:pn-nativesdk-qemu = " sdl"
USER_CLASSES ?= "buildstats buildhistory buildstats-summary"

require conf/distro/include/no-static-libs.inc
require conf/distro/include/yocto-uninative.inc
require conf/distro/include/security_flags.inc

INHERIT += "uninative"

DISTRO_FEATURES:append = " largefile opengl ptest multiarch wayland pam  systemd "
#DISTRO_FEATURES:append = " largefile opengl ptest multiarch wayland pam "
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
##VIRTUAL-RUNTIME_initscripts = ""
##VIRTUAL-RUNTIME_syslog = ""
HOSTTOOLS_NONFATAL:append = " ssh"

DISTRO_NAME = "StarFive Linux SDK"
#DISTRO_VERSION = ""
#DISTRO_CODENAME = ""
EOF

echo "---------------------------------------------------"
echo "Example: MACHINE=${MACHINE} bitbake ${BITBAKEIMAGE}"
echo "---------------------------------------------------"
echo ""
echo "Buildable machine info"
echo "---------------------------------------------------"
echo "* qemuriscv64  : The 64-bit RISC-V machine"
echo "* starfive-dubhe  : The StarFive Dubhe machine"
echo "---------------------------------------------------"

# start build
#echo "Starting build"
#bitbake $BITBAKEIMAGE

# Launch build script
# Terminal Style
PURPLE='\E[1;35m'
YELLOW='\E[1;33m'
GREEN='\E[0;32m'
RED='\E[0;31m'
NC='\E[0m'
MARK="\033[0;32m\xE2\x9C\x94\033[0m"

#Function
runprog(){
read -p "Do you want to runqemu?[Y/n]:" RES

case $RES in
 [Yy])
   MACHINE=starfive-dubhe runqemu nographic $1;;
 [Nn])
   return;;
 *)
   echo "Invalid option $RES , [Y/n] only.";;
esac
}

# Menu script
echo ""
echo -e "${PURPLE}*******************************************************************"
echo "*                                                                 *"
echo "*                     Welcome to Starfive Yocto                   *"
echo "*                                                                 *"
echo -e "*******************************************************************${NC}"
echo "";
echo -e "${YELLOW}Description : ";
echo "";
echo "This build script consists of two types image.";
echo "";
echo "1) QSPI-Image";
echo "   - Initramfs has been bundled into qspi-image.";
echo "   - The image generated support both single and multicore vector.";
echo "   - Generated output : "; 
echo "     QSPI-Image.bin for single core vector."; 
echo -e "     QSPI-Image-Dual.bin for multicore vector.${NC}";
echo "";

PS3="Select your action : "
options=("Build qspi-image" "Quit")

select opt in "${options[@]}" 
do
    case $opt in 
        "Build qspi-image")
#            cd ../build || { echo "Run setup.sh before building images."; cd meta-starfive; break; };
            if ! grep -q "ENABLE_EXT4" ./conf/local.conf; then
                echo 'ENABLE_EXT4="0"' >> ./conf/local.conf;
            else sed -i 's/ENABLE_EXT4="1"/ENABLE_EXT4="0"/g'  ./conf/local.conf;
		 sed -i 's/ENABLE_UBI="1"/ENABLE_UBI="0"/g'  ./conf/local.conf;
            fi;
            cur_ter=$(tty);
            output=$(MACHINE=starfive-dubhe bitbake qspi-image | tee $cur_ter);
            if [[ $output != *"ERROR"* ]]; then
                echo -e "\U0002705 ${GREEN}Build Complete${NC}"
                runprog dubhe-image-initramfs;
            else echo -e "\U000274C ${RED}Build Failed${NC}"
            fi;;
        "Build dubhe-image-minimal")
 #           cd ../build || { echo "Run setup.sh before building images."; cd meta-starfive; break; };
            if ! grep -q "ENABLE_EXT4" ./conf/local.conf; then
                echo 'ENABLE_EXT4="1"' >> ./conf/local.conf;
            else sed -i 's/ENABLE_EXT4="0"/ENABLE_EXT4="1"/g'  ./conf/local.conf
		 sed -i 's/ENABLE_UBI="1"/ENABLE_UBI="0"/g'  ./conf/local.conf;
            fi;
            cur_ter=$(tty);
            output_min=$(MACHINE=starfive-dubhe bitbake dubhe-image-minimal | tee $cur_ter);
            if [[ $output_min != *"ERROR"* ]]; then
                echo -e "\U0002705 ${GREEN}Build Complete${NC}"
                runprog console-image-minimal;
            else echo -e "\U000274C ${RED}Build Failed${NC}"
            fi;;
	"Build qspi-ubifs-image")
#            cd ../build || { echo "Run setup.sh before building images."; cd meta-starfive; break; };
            if ! grep -q "ENABLE_EXT4" ./conf/local.conf; then
                echo 'ENABLE_EXT4="1"' >> ./conf/local.conf;
            else sed -i 's/ENABLE_EXT4="0"/ENABLE_EXT4="1"/g'  ./conf/local.conf;
            fi;
	    if ! grep -q "ENABLE_UBI" ./conf/local.conf; then
                echo 'ENABLE_UBI="1"' >> ./conf/local.conf;
            else sed -i 's/ENABLE_UBI="0"/ENABLE_UBI="1"/g'  ./conf/local.conf;
            fi;
            cur_ter=$(tty);
            output=$(MACHINE=starfive-dubhe bitbake qspi-ubifs-image | tee $cur_ter);
            if [[ $output != *"ERROR"* ]]; then
                echo -e "\U0002705 ${GREEN}Build Complete${NC}"
            else echo -e "\U000274C ${RED}Build Failed${NC}"
            fi;;
	"Quit")
            break;;
        *)
            echo "Invalid option $REPLY. Kindly select choice between menu range."
    esac
    REPLY=
done
