/*
 * Copyright 2022 Garmin Ltd. or its subsidiaries
 *
 * SPDX-License-Identifier: GPL-2.0
 *
 * Attempts to find and exec the host qemu-bridge-helper program
 */

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void try_program(char const* path, char** args) {
    if (access(path, X_OK) == 0) {
        execv(path, args);
    }
}

int main(int argc, char** argv) {
    char* var;

    var = getenv("QEMU_BRIDGE_HELPER");
    if (var && var[0] != '\0') {
        execvp(var, argv);
        return 1;
    }

    try_program("/usr/libexec/qemu-bridge-helper", argv);
    try_program("/usr/lib/qemu/qemu-bridge-helper", argv);

    fprintf(stderr, "No bridge helper found\n");
    return 1;
}

