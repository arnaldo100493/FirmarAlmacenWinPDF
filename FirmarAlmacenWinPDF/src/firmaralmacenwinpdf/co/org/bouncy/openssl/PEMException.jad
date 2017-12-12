// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMException.java

package co.org.bouncy.openssl;

import java.io.IOException;

public class PEMException extends IOException
{

    public PEMException(String message)
    {
        super(message);
    }

    public PEMException(String message, Exception underlying)
    {
        super(message);
        this.underlying = underlying;
    }

    public Exception getUnderlyingException()
    {
        return underlying;
    }

    public Throwable getCause()
    {
        return underlying;
    }

    Exception underlying;
}
