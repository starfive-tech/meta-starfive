# StarFive Linux Software Development Kit (SDK)

The StarFive Dubhe Linux SDK is based on Yocto Project. It enables the creation of Linux distributions for any embedded project, as well as easy migration from a different architecture platform. 
The meta-starfive layer is built on top of meta-riscv to provide additional modifications such as Linux Kernel, and toolchains like GCC and LLVM.


## Supported Features
* **FPGA Image Generation**
  * Able to generate an image to boot up on FPGA directly

<br>

* **Yocto extensible-SDK (eSDK)**
  * Shell-script installer
  * Shrink-wrapped distro maintainer environment
  * Updateable and Extensible

# Setting up meta-starfive
Guide to use meta-starfive using Yocto

## Dependencies
First, you need to download the essential Yocto dependencies, which can also be found on their [official Yocto Guide](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html#build-host-packages):
```
$ sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev zstd liblz4-tool
```

#### Installing Repo
Next, you will need to install repo from https://source.android.com/setup/develop#installing-repo.

Dependencies of repo include:
```
$ sudo apt install curl python python3-distutils
```

The steps to install repo are as below:
```
$ mkdir ~/bin
$ PATH=~/bin:$PATH
$ curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
$ chmod a+x ~/bin/repo
```


## Setup

### Create a workspace
Now, we will be creating a workspace and retrieve the latest layers needed for our image creation.

```
$ mkdir starfive-yocto && cd starfive-yocto
$ repo init -u git://github.com/starfive-tech/meta-starfive -b starfive-honister -m tools/manifests/starfive.xml
$ repo sync
$ repo start work --all
```


### Setup Bitbake Environment 
After having all the layers required,  you can now source into the build environment by running the `setup.sh` script in the meta-starfive layer. You should run the script in the starfive-yocto folder directory.

```
$ . ./meta-starfive/setup.sh
```

### Available Machines
In meta-starfive, there is currently only one buildable machine that you can build for:

* **starfive-dubhe**: The StarFive Dubhe machine

### Build Images

To build a initramfs image with QSPI-Image binary:
```
$ MACHINE=starfive-dubhe bitbake qspi-image
```

For the bitbake command, you can control the number of parallel tasks and the number of cores that Bitbake will use. You can either add the variables in the `<build_directory>/conf/local.conf` file, or add it in your shell environment command, for example:

```
$ PARALLEL_MAKE="-j 6" BB_NUMBER_THREADS=4 MACHINE=starfive-dubhe bitbake qspi-image
```

To populate the extensible eSDK shell script, you can use the `-c populate_sdk_ext` command:
```
$ MACHINE=starfive-dubhe bitbake qspi-image -c populate_sdk_ext
```
_NOTE: For your first build, it may take several hours to build the image._


## Running in QEMU
Run the 64-bit machine in QEMU using the following command:
```
MACHINE=starfive-dubhe runqemu nographic dubhe-image-initramfs
```
<br>

_Tip: Once QEMU starts, in nographic mode, the terminal is 'controlled' by the Linux console. Thus, to kill QEMU and get back to the terminal, you can:_
* ```CTRL-a x``` to kill QEMU and exit

## Running on FPGA
You will find image components such as openSBI (fw_payload.bin) and the Linux kernel in the bitbake build directory (_<SDK_path>/build/tmp-glibc/deploy/images/starfive-dubhe/_) after your bitbake process is done. You can also find the full QSPI binary image generated (QSPI-Image.bin), which includes the boot codes, openSBI and kernel in a single binary.

You can flash the QSPI binary into the FPGA via openOCD or other FPGA flashing tools.

<br>

## Modifying the Kernel
To modify the kernel, you can use the devtool command line:
```
$ MACHINE=starfive-dubhe devtool modify virtual/kernel
```

The devtool command will fetch the source code and unpack them in the _'build/workspace/sources/<kernel_name>'_ directory.

Now you can make your changes in your source code in the workspace directory.

### Building the Kernel
After you had done your changes, you can build only the kernel by:
```
$ MACHINE=starfive-dubhe devtool build <kernel_name>

or 

$ MACHINE=starfive-dubhe bitbake virtual/kernel
```

You can obtain the image generated in the images folder _build/tmp-glibc/deploy/images/starfive-dubhe/<Image_name>_

### Saving the Modification
Once you are happy with the changes, you can go to the source folder and commit your changes:
* Go to _workspace/sources/<kernel_name>_:
  * ```$ git status```
  * ```$ git add .```
  * ```$ git commit -m "Changes added"```
* Create a bbappend and patch file:
  * ```$ devtool finish <kernel_name> <layer_path> ```

### Rebuilding the Image
To rebuild your image, the steps include:
```
$ MACHINE=starfive-dubhe bitbake qspi-image
```
Rebuilding will take less time as the sstate-cache is saved during your first build.


## Extras
In case you accidentally deleted files in the _build/tmp/deploy/images/_ directory and some of the files are not automatically re-created when you build the image again, you can rebuild and recreate the kernel files by:
```
$ MACHINE=starfive-dubhe bitbake -c clean virtual/kernel
$ MACHINE=starfive-dubhe bitbake virtual/kernel
```


