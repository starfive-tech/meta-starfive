FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCREV = "2f67cde5a0aaf7cffd43a71f5c4b443698909f4a"

SRC_URI += " \
    git://github.com/fdintino/nginx-upload-module.git;branch=master;destsuffix=nginx-upload-module;protocol=ssh \
"

EXTRA_OECONF += "--add-module=${WORKDIR}/nginx-upload-module "
