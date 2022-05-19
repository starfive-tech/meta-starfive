MAJOR_VER = "14"
MINOR_VER = "0"
PATCH_VER = "0"

#Disable compiler-rt from packages
PACKAGECONFIG:remove = "compiler-rt"

#Builds libcxx with libgcc
COMPILER_RT = "-rtlib=libgcc"
