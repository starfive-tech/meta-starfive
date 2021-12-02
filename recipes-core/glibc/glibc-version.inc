SRCBRANCH ?= "master"
PV = "2.35"
SRCREV_glibc ?= "90f0ac10a74b2d43b5a65aab4be40565e359be43"
SRCREV_localedef ?= "95c0221703ad970a52445e9eaf91c4aff35eebef"

GLIBC_GIT_URI ?= "git://sourceware.org/git/glibc.git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.\d+(\.(?!90)\d+)*)"
