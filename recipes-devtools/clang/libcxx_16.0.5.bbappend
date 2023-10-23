#Disable compiler-rt from packages
PACKAGECONFIG:remove = "compiler-rt"

#Builds libcxx with libgcc
COMPILER_RT = "-rtlib=libgcc"
