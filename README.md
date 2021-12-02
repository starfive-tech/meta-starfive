# Setting up meta-starfive
Guide to use meta-starfive using Yocto

## Dependencies
First, you need to download the essential Yocto dependencies:
```
$ sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat essential chrpath socat libsdl1.2-dev xterm libelf-dev
```

#### Repo
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

First, to use repo, you need to key in your Github email and username as follows:
```
$ git config –global user.email “your email”
$ git config -global user.name “your username”
```

### Create a workspace

```
$ mkdir starfive-yocto && cd starfive-yocto
$ repo init -u git://github.com/starfive-tech/meta-starfive -b starfive-hardknott -m tools/manifests/starfive.xml
$ repo sync
$ repo start work --all
```

### Update existing workspace
In order to bring all the layers up to date with upstream:
```
$ cd starfive-yocto
$ repo sync
$ repo rebase
```

### Bitbake Environment Setup

```
$ . ./meta-starfive/setup.sh
```

### Building Images
In meta-starfive, there are two different buildable machines that you can build for:

* qemuriscv64: The 64-bit RISC-V machine
* starfive-dubhe: The StarFive Dubhe machine

To build the image for starfive-dubhe machine:
```
$ MACHINE=starfive-dubhe bitbake qspi-image
```
_NOTE: For your first build, it may take several hours to build the image._

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
