SRCBRANCH ?= "release/2.36/master"
PV = "2.36"
SRCREV_glibc ?= "b3736d1a3c60a3ec9959bf3b38794958546bf6a2"
SRCREV_localedef ?= "794da69788cbf9bf57b59a852f9f11307663fa87"

GLIBC_GIT_URI ?= "git://sourceware.org/git/glibc.git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.\d+(\.(?!90)\d+)*)"
