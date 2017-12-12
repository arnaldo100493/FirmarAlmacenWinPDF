// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPException.java

package co.com.pdf.xmp;


public class XMPException extends Exception
{

    public XMPException(String message, int errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public XMPException(String message, int errorCode, Throwable t)
    {
        super(message, t);
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    private int errorCode;
}
