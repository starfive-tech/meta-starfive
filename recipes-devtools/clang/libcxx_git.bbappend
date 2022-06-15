MAJOR_VER = "15"
MINOR_VER = "0"
PATCH_VER = "0"

#Disable compiler-rt from packages
PACKAGECONFIG:remove = "compiler-rt"

#Builds libcxx with libgcc
COMPILER_RT = "-rtlib=libgcc"
