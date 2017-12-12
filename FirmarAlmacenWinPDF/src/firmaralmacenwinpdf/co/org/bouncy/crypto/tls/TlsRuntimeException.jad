// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsRuntimeException.java

package co.org.bouncy.crypto.tls;


public class TlsRuntimeException extends RuntimeException
{

    public TlsRuntimeException(String message, Throwable e)
    {
        super(message);
        this.e = e;
    }

    public TlsRuntimeException(String message)
    {
        super(message);
    }

    public Throwable getCause()
    {
        return e;
    }

    private static final long serialVersionUID = 0x1ac1b7258a020516L;
    Throwable e;
}
