// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IOExceptionWithCause.java

package org.apache.commons.io;

import java.io.IOException;

public class IOExceptionWithCause extends IOException
{

    public IOExceptionWithCause(String message, Throwable cause)
    {
        super(message);
        initCause(cause);
    }

    public IOExceptionWithCause(Throwable cause)
    {
        super(cause != null ? cause.toString() : null);
        initCause(cause);
    }

    private static final long serialVersionUID = 1L;
}
