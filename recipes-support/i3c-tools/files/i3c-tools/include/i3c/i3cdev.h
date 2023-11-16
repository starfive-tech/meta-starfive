/* SPDX-License-Identifier: GPL-2.0 */
/*
 * Copyright (C) 2019 Synopsys, Inc. and/or its affiliates.
 *
 * Author: Vitor Soares <vitor.soares@synopsys.com>
 */

#ifndef _UAPI_I3C_DEV_H_
#define _UAPI_I3C_DEV_H_

#include <linux/types.h>
#include <linux/ioctl.h>

#define VERSION "0.2"

/* IOCTL commands */
#define I3C_DEV_IOC_MAGIC	0x07

enum i3c_error_code {
	I3C_ERROR_UNKNOWN = 0,
	I3C_ERROR_M0 = 1,
	I3C_ERROR_M1,
	I3C_ERROR_M2,
};

struct i3c_priv_xfer {
	char rnw;
	uint16_t len;
	union {
		void *in;
		const void *out;
	} data;
	enum i3c_error_code err;
};

struct i3c_ioc_priv_xfer {
	struct i3c_priv_xfer *xfers;	/* pointers to i3c_priv_xfer */
	__u32 nxfers;				/* number of i3c_priv_xfer */
};

#define I3C_IOC_PRIV_XFER	\
	_IOWR(I3C_DEV_IOC_MAGIC, 30, struct i3c_ioc_priv_xfer)

#define  I3C_IOC_PRIV_XFER_MAX_MSGS	42

#endif
