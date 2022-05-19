SRCBRANCH ?= "master"
PV = "2.35"
SRCREV_glibc ?= "1d3decee997ba2fc24af81803299b2f4f3c47063"
SRCREV_localedef ?= "95c0221703ad970a52445e9eaf91c4aff35eebef"

GLIBC_GIT_URI ?= "git://sourceware.org/git/glibc.git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.\d+(\.(?!90)\d+)*)"
