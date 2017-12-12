// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidPdfException.java

package co.com.pdf.text.exceptions;

import java.io.IOException;

public class InvalidPdfException extends IOException
{

    public InvalidPdfException(String message)
    {
        this(message, null);
    }

    public InvalidPdfException(String message, Throwable cause)
    {
        super(message);
        this.cause = cause;
    }

    public Throwable getCause()
    {
        return cause;
    }

    private static final long serialVersionUID = 0xdfcf12871f541586L;
    private final Throwable cause;
}
